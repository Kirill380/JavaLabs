import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

public class MyArrayList<T> implements MyList<T>, RandomAccess {
    private final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(Collection<? extends T> c) {
        Object[] array = c.toArray();
        this.elements = array;
        this.size = array.length;
    }

    @Override
    public void add(T e) {
        ensureCapacity(size + 1);
        elements[size++] = e;
    }

    @Override
    public void add(int index, T element) {
        rangeCheck(index);
        ensureCapacity(size + 1);
        int numToShift = size - index;
        System.arraycopy(elements, index, elements, index + 1, numToShift); // right shift
        elements[index] = element;
        size++;
    }

    @Override
    public void addAll(Collection<? extends T> c) {
        Object[] a = c.toArray();
        int newElements = a.length;
        ensureCapacity(size + newElements);
        System.arraycopy(a, 0, elements, size, newElements);
        size += newElements;
    }

    @Override
    public void addAll(int index, Collection<? extends T> c) {
        rangeCheck(index);
        Object[] a = c.toArray();
        int newElements = a.length;
        ensureCapacity(size + newElements);
        int numToShift = size - index ;
        System.arraycopy(elements, index, elements, index + newElements, numToShift); // right shift
        System.arraycopy(a, 0, elements, index, newElements);
        size += newElements;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        rangeCheck(index);
        return (T) elements[index];
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
        int numToCopy = size - index - 1;
        if (numToCopy > 0) {
            System.arraycopy(elements, index + 1, elements, index, numToCopy); // left shift
        }
        elements[--size] = null; // let GC take care of it
        return oldValue;
    }

    @Override
    public void set(int index, T element) {
        rangeCheck(index);
        elements[index] = element;
    }

    @Override
    public int indexOf(T o) {
        if(o == null) {
            for (int i = 0; i < size; i++) {
                if(elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if(o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    private void ensureCapacity(int newSize) {
        if (newSize > elements.length) {
            increaseCapacity(newSize);
        }
    }

    private void increaseCapacity(int newSize) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1); //oldCapacity * 1,5
        if (newCapacity < 0) // overflow
            throw new OutOfMemoryError();
        if (newSize < 0) // overflow
            throw new OutOfMemoryError();
        if (newCapacity - newSize < 0)
            newCapacity = newSize;

        elements = Arrays.copyOf(elements, newCapacity);
    }

    // to not throw ArrayIndexOutOfBoundsException and encapsulate implementation of list
    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
