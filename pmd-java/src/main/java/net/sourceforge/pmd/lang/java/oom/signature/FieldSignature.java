/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.oom.signature;

import java.util.HashMap;
import java.util.Map;

import net.sourceforge.pmd.lang.java.ast.ASTFieldDeclaration;

/**
 * Signature for a field.
 *
 * @author Clément Fournier
 */
public final class FieldSignature extends Signature {

    private static final Map<Integer, FieldSignature> POOL = new HashMap<>();

    public final boolean isStatic;
    public final boolean isFinal;


    private FieldSignature(Visibility visibility, boolean isStatic, boolean isFinal) {
        super(visibility);
        this.isStatic = isStatic;
        this.isFinal = isFinal;
    }


    @Override
    public boolean equals(Object o) {
        return this == o;
    }


    @Override
    public int hashCode() {
        return code(visibility, isStatic, isFinal);
    }


    /** Used internally by the pooler. */
    private static int code(Visibility visibility, boolean isStatic, boolean isFinal) {
        return visibility.hashCode() * 31 + (isStatic ? 1 : 0) * 2 + (isFinal ? 1 : 0);
    }


    /**
     * Builds a field signature from its AST node.
     *
     * @param node The AST node of the field
     *
     * @return The signature of the field
     */
    public static FieldSignature buildFor(ASTFieldDeclaration node) {
        int code = code(Visibility.get(node), node.isStatic(), node.isFinal());
        if (!POOL.containsKey(code)) {
            POOL.put(code, new FieldSignature(Visibility.get(node), node.isStatic(), node.isFinal()));
        }
        return POOL.get(code);
    }
}
