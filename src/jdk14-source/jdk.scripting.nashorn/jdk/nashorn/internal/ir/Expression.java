/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
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

package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.codegen.types.Type;
import jdk.nashorn.internal.runtime.UnwarrantedOptimismException;

/**
 * Common superclass for all expression nodes. Expression nodes can have
 * an associated symbol as well as a type.
 *
 */
public abstract class Expression extends Node {
    private static final long serialVersionUID = 1L;

    static final String OPT_IDENTIFIER = "%";

    protected Expression(final long token, final int start, final int finish) {
        super(token, start, finish);
    }

    Expression(final long token, final int finish) {
        super(token, finish);
    }

    Expression(final Expression expr) {
        super(expr);
    }

    /**
     * Returns the type of the expression.
     *
     * @return the type of the expression.
     */
    public abstract Type getType();

    /**
     * Returns {@code true} if this expression depends exclusively on state that is constant
     * or local to the currently running function and thus inaccessible to other functions.
     * This implies that a local expression must not call any other functions (neither directly
     * nor implicitly through a getter, setter, or object-to-primitive type conversion).
     *
     * @return true if this expression does not depend on state shared with other functions.
     */
    public boolean isLocal() {
        return false;
    }

    /**
     * Is this a self modifying assignment?
     * @return true if self modifying, e.g. a++, or a*= 17
     */
    public boolean isSelfModifying() {
        return false;
    }

    /**
     * Returns widest operation type of this operation.
     *
     * @return the widest type for this operation
     */
    public Type getWidestOperationType() {
        return Type.OBJECT;
    }

    /**
     * Returns true if the type of this expression is narrower than its widest operation type (thus, it is
     * optimistically typed).
     * @return true if this expression is optimistically typed.
     */
    public final boolean isOptimistic() {
        return getType().narrowerThan(getWidestOperationType());
    }

    void optimisticTypeToString(final StringBuilder sb) {
        optimisticTypeToString(sb, isOptimistic());
    }

    void optimisticTypeToString(final StringBuilder sb, final boolean optimistic) {
        sb.append('{');
        final Type type = getType();
        final String desc = type == Type.UNDEFINED ? "U" : type.getDescriptor();

        sb.append(desc.charAt(desc.length() - 1) == ';' ? "O" : desc);
        if (isOptimistic() && optimistic) {
            sb.append(OPT_IDENTIFIER);
            final int pp = ((Optimistic)this).getProgramPoint();
            if (UnwarrantedOptimismException.isValid(pp)) {
                sb.append('_').append(pp);
            }
        }
        sb.append('}');
    }

    /**
     * Returns true if the runtime value of this expression is always false when converted to boolean as per ECMAScript
     * ToBoolean conversion. Used in control flow calculations.
     * @return true if this expression's runtime value converted to boolean is always false.
     */
    public boolean isAlwaysFalse() {
        return false;
    }

    /**
     * Returns true if the runtime value of this expression is always true when converted to boolean as per ECMAScript
     * ToBoolean conversion. Used in control flow calculations.
     * @return true if this expression's runtime value converted to boolean is always true.
     */
    public boolean isAlwaysTrue() {
        return false;
    }

    /**
     * Returns true if the expression is not null and {@link #isAlwaysFalse()}.
     * @param test a test expression used as a predicate of a branch or a loop.
     * @return true if the expression is not null and {@link #isAlwaysFalse()}.
     */
    public static boolean isAlwaysFalse(final Expression test) {
        return test != null && test.isAlwaysFalse();
    }


    /**
     * Returns true if the expression is null or {@link #isAlwaysTrue()}. Null is considered to be always true as a
     * for loop with no test is equivalent to a for loop with always-true test.
     * @param test a test expression used as a predicate of a branch or a loop.
     * @return true if the expression is null or {@link #isAlwaysFalse()}.
     */
    public static boolean isAlwaysTrue(final Expression test) {
        return test == null || test.isAlwaysTrue();
    }
}
