/*
 * Copyright (c) 2009, 2019, Oracle and/or its affiliates. All rights reserved.
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

package java.util.zip;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;

import sun.nio.cs.UTF_8;

/**
 * Utility class for zipfile name and comment decoding and encoding
 */

class ZipCoder {

    private static final jdk.internal.access.JavaLangAccess JLA =
        jdk.internal.access.SharedSecrets.getJavaLangAccess();

    static final class UTF8 extends ZipCoder {

        UTF8(Charset utf8) {
            super(utf8);
        }

        @Override
        boolean isUTF8() {
            return true;
        }

        @Override
        String toString(byte[] ba, int off, int length) {
            return JLA.newStringUTF8NoRepl(ba, off, length);
        }

        @Override
        byte[] getBytes(String s) {
            return JLA.getBytesUTF8NoRepl(s);
        }
    }

    // UTF_8.ArrayEn/Decoder is stateless, so make it singleton.
    private static ZipCoder utf8 = new UTF8(UTF_8.INSTANCE);

    public static ZipCoder get(Charset charset) {
        if (charset == UTF_8.INSTANCE)
            return utf8;
        return new ZipCoder(charset);
    }

    String toString(byte[] ba, int off, int length) {
        try {
            return decoder().decode(ByteBuffer.wrap(ba, off, length)).toString();
        } catch (CharacterCodingException x) {
            throw new IllegalArgumentException(x);
        }
    }

    String toString(byte[] ba, int length) {
        return toString(ba, 0, length);
    }

    String toString(byte[] ba) {
        return toString(ba, 0, ba.length);
    }

    byte[] getBytes(String s) {
        try {
            ByteBuffer bb = encoder().encode(CharBuffer.wrap(s));
            int pos = bb.position();
            int limit = bb.limit();
            if (bb.hasArray() && pos == 0 && limit == bb.capacity()) {
                return bb.array();
            }
            byte[] bytes = new byte[bb.limit() - bb.position()];
            bb.get(bytes);
            return bytes;
        } catch (CharacterCodingException x) {
            throw new IllegalArgumentException(x);
        }
    }

    // assume invoked only if "this" is not utf8
    byte[] getBytesUTF8(String s) {
        return utf8.getBytes(s);
    }

    String toStringUTF8(byte[] ba, int len) {
        return utf8.toString(ba, 0, len);
    }

    String toStringUTF8(byte[] ba, int off, int len) {
        return utf8.toString(ba, off, len);
    }

    boolean isUTF8() {
        return false;
    }

    private Charset cs;
    private CharsetDecoder dec;
    private CharsetEncoder enc;

    private ZipCoder(Charset cs) {
        this.cs = cs;
    }

    protected CharsetDecoder decoder() {
        if (dec == null) {
            dec = cs.newDecoder()
              .onMalformedInput(CodingErrorAction.REPORT)
              .onUnmappableCharacter(CodingErrorAction.REPORT);
        }
        return dec;
    }

    protected CharsetEncoder encoder() {
        if (enc == null) {
            enc = cs.newEncoder()
              .onMalformedInput(CodingErrorAction.REPORT)
              .onUnmappableCharacter(CodingErrorAction.REPORT);
        }
        return enc;
    }
}
