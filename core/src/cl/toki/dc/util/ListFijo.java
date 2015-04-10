package cl.toki.dc.util;

import java.util.ArrayList;

/**
 *
 * @author Francisco Garcia
 * @param <T>
 */
public class ListFijo<T> extends ArrayList<T> {
    private final int maxSize;
    public ListFijo(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(T t) {
        if (size() >= maxSize) {
            remove(0);
        }
        return super.add(t);
    }

    // implementation of remaining add methods....
}
