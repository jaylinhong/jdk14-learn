/*
 * Copyright (c) 2013, 2019, Oracle and/or its affiliates. All rights reserved.
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
 *
 */

package jdk.jfr.internal.instrument;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import jdk.jfr.events.SocketWriteEvent;

/**
 * See {@link JITracer} for an explanation of this code.
 */
@JIInstrumentationTarget("java.net.Socket$SocketOutputStream")
final class SocketOutputStreamInstrumentor {

    private SocketOutputStreamInstrumentor() {
    }

    @SuppressWarnings("deprecation")
    @JIInstrumentationMethod
    public void write(byte b[], int off, int len) throws IOException {
        SocketWriteEvent event = SocketWriteEvent.EVENT.get();
        if (!event.isEnabled()) {
            write(b, off, len);
            return;
        }
        int bytesWritten = 0;
        try {
            event.begin();
            write(b, off, len);
            bytesWritten = len;
        } finally {
            event.end() ;
            if (event.shouldCommit()) {
                InetAddress remote = parent.getInetAddress();
                event.host = remote.getHostName();
                event.address = remote.getHostAddress();
                event.port = parent.getPort();
                event.bytesWritten = bytesWritten;

                event.commit();
                event.reset();
            }
        }
    }

    // private field in java.net.Socket$SocketOutputStream
    private Socket parent;
}
