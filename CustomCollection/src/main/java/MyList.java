import java.util.Collection;

public interface MyList<T> {

    void add(T e);

    void add(int index, T element);

    void addAll(Collection<? extends T> c);

    void addAll(int index, Collection<? extends T> c);

    T get(int index);

    T remove(int index);

    void set(int index, T element);

    int indexOf(T o);

    int size();

    Object[] toArray();

}
