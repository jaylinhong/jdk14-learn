/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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
 */


package jdk.internal.vm.compiler.libgraal;

import jdk.vm.ci.hotspot.HotSpotJVMCIRuntime;

/**
 * Scope for calling CEntryPoints in libgraal. {@linkplain #LibGraalScope(HotSpotJVMCIRuntime)
 * Opening} a scope attaches the current thread to libgraal and {@linkplain #close() closing} it
 * detaches the current thread.
 */
public final class LibGraalScope implements AutoCloseable {

    static final ThreadLocal<LibGraalScope> currentScope = new ThreadLocal<>();

    private final LibGraalScope parent;
    private final boolean topLevel;
    private final HotSpotJVMCIRuntime runtime;
    private final long isolateThread;

    /**
     * Gets the isolate thread associated with the current thread. The current thread must be in an
     * {@linkplain #LibGraalScope(HotSpotJVMCIRuntime) opened} scope.
     *
     * @returns a value that can be used for the IsolateThreadContext argument of a {@code native}
     *          method {@link LibGraal#registerNativeMethods linked} to a CEntryPoint function in
     *          libgraal
     * @throws IllegalStateException if not the current thread is not attached to libgraal
     */
    public static long getIsolateThread() {
        LibGraalScope scope = currentScope.get();
        if (scope == null) {
            throw new IllegalStateException("Cannot get isolate thread outside of a " + LibGraalScope.class.getSimpleName());
        }
        return scope.isolateThread;
    }

    /**
     * Enters a scope for making calls into libgraal. If there is no existing libgraal scope for the
     * current thread, the current thread is attached to libgraal. When the outer most scope is
     * closed, the current thread is detached from libgraal.
     *
     * This must be used in a try-with-resources statement.
     *
     * This cannot be called from {@linkplain LibGraal#inLibGraal() within} libgraal.
     *
     * @throws IllegalStateException if libgraal is {@linkplain LibGraal#isAvailable() unavailable}
     *             or {@link LibGraal#inLibGraal()} returns true
     */
    public LibGraalScope(HotSpotJVMCIRuntime runtime) {
        if (LibGraal.inLibGraal() || !LibGraal.isAvailable()) {
            throw new IllegalStateException();
        }
        this.runtime = runtime;
        parent = currentScope.get();
        boolean top = false;
        if (parent == null) {
            top = LibGraal.attachCurrentThread(runtime, false);
            isolateThread = LibGraal.getCurrentIsolateThread(LibGraal.isolate);
        } else {
            isolateThread = parent.isolateThread;
        }
        topLevel = top;
        currentScope.set(this);
    }

    @Override
    public void close() {
        if (topLevel) {
            LibGraal.detachCurrentThread(runtime);
        }
        currentScope.set(parent);
    }
}
