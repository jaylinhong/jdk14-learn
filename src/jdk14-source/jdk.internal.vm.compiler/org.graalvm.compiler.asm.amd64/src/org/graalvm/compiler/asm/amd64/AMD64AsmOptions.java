/*
 * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.asm.amd64;

public class AMD64AsmOptions {
    public static final boolean UseNormalNop = false;
    public static final boolean UseAddressNop = true;
    public static final boolean UseIntelNops = true;
    public static final boolean UseIncDec = true;
    public static final boolean UseXmmLoadAndClearUpper = true;
    public static final boolean UseXmmRegToRegMoveAll = true;
}
