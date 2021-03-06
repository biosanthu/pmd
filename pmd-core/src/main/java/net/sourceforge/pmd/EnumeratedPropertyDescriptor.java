/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd;

import java.util.Map;

/**
 * Interface defining an enumerated property descriptor.
 *
 * @param <E> The type of the values
 * @param <T> The type of default values the descriptor can take (can be a List)
 *
 * @author Clément Fournier
 * @since 6.0.0
 */
public interface EnumeratedPropertyDescriptor<E, T> extends PropertyDescriptor<T> {

    /**
     * Returns an immutable map of the label - value mappings defined by this descriptor.
     *
     * @return an immutable map of the label - value mappings defined by this descriptor.
     */
    Map<String, E> mappings();

}
