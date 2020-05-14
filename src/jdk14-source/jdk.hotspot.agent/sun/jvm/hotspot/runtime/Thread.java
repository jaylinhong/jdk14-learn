/*
 * Copyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package sun.jvm.hotspot.runtime;

import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.types.*;

public class Thread extends VMObject {
  private static long tlabFieldOffset;

  private static CIntegerField suspendFlagsField;
  // Thread::SuspendFlags enum constants
  private static int EXTERNAL_SUSPEND;
  private static int EXT_SUSPENDED;
  private static int HAS_ASYNC_EXCEPTION;

  private static AddressField activeHandlesField;
  private static AddressField currentPendingMonitorField;
  private static AddressField currentWaitingMonitorField;

  private static JLongField allocatedBytesField;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("Thread");

    suspendFlagsField = type.getCIntegerField("_suspend_flags");
    EXTERNAL_SUSPEND = db.lookupIntConstant("Thread::_external_suspend").intValue();
    EXT_SUSPENDED = db.lookupIntConstant("Thread::_ext_suspended").intValue();
    HAS_ASYNC_EXCEPTION = db.lookupIntConstant("Thread::_has_async_exception").intValue();

    tlabFieldOffset    = type.getField("_tlab").getOffset();
    activeHandlesField = type.getAddressField("_active_handles");
    currentPendingMonitorField = type.getAddressField("_current_pending_monitor");
    currentWaitingMonitorField = type.getAddressField("_current_waiting_monitor");
    allocatedBytesField = type.getJLongField("_allocated_bytes");
  }

  public Thread(Address addr) {
    super(addr);
  }

  public int suspendFlags() {
    return (int) suspendFlagsField.getValue(addr);
  }

  public boolean isExternalSuspend() {
    return (suspendFlags() & EXTERNAL_SUSPEND) != 0;
  }

  public boolean isExtSuspended() {
    return (suspendFlags() & EXT_SUSPENDED) != 0;
  }

  public boolean isBeingExtSuspended() {
    return isExtSuspended() || isExternalSuspend();
  }

  // historical usage: checked for VM or external suspension
  public boolean isAnySuspended() {
    return isExtSuspended();
  }

  public boolean hasAsyncException() {
    return (suspendFlags() & HAS_ASYNC_EXCEPTION) != 0;
  }

  public ThreadLocalAllocBuffer tlab() {
    return new ThreadLocalAllocBuffer(addr.addOffsetTo(tlabFieldOffset));
  }

  public JNIHandleBlock activeHandles() {
    Address a = activeHandlesField.getAddress(addr);
    if (a == null) {
      return null;
    }
    return new JNIHandleBlock(a);
  }

  public long allocatedBytes() {
    return allocatedBytesField.getValue(addr);
  }

  public boolean   isVMThread()                  { return false; }
  public boolean   isJavaThread()                { return false; }
  public boolean   isCompilerThread()            { return false; }
  public boolean   isCodeCacheSweeperThread()    { return false; }
  public boolean   isHiddenFromExternalView()    { return false; }
  public boolean   isJvmtiAgentThread()          { return false; }
  public boolean   isWatcherThread()             { return false; }
  public boolean   isServiceThread()             { return false; }

  /** Memory operations */
  public void oopsDo(AddressVisitor oopVisitor) {
    // FIXME: Empty for now; will later traverse JNI handles and
    // pending exception
  }

  public ObjectMonitor getCurrentPendingMonitor() {
    Address monitorAddr = currentPendingMonitorField.getValue(addr);
    if (monitorAddr == null) {
      return null;
    }
    return new ObjectMonitor(monitorAddr);
  }

  public ObjectMonitor getCurrentWaitingMonitor() {
    Address monitorAddr = currentWaitingMonitorField.getValue(addr);
    if (monitorAddr == null) {
      return null;
    }
    return new ObjectMonitor(monitorAddr);
  }

  public boolean isLockOwned(Address lock) {
    if (isInStack(lock)) return true;
    return false;
  }

  public boolean isInStack(Address a) {
    // In the Serviceability Agent we need access to the thread's
    // stack pointer to be able to answer this question. Since it is
    // only a debugging system at the moment we need access to the
    // underlying thread, which is only present for Java threads; see
    // JavaThread.java.
    return false;
  }

  /** Assistance for ObjectMonitor implementation */
  Address threadObjectAddress() { return addr; }
}
