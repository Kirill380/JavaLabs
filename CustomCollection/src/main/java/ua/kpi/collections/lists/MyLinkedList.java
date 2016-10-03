package ua.kpi.collections.lists;

import java.util.Collection;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements  MyList<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() {

    }

    public MyLinkedList(Collection<? extends T> c) {
        this();
        addAll(c);
    }


    @Override
    public void add(T e) {
        if (head == null) {
            head = new Node<>(e);
            tail = head;
        } else {
            Node<T> newNode = new Node<>(e);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        rangeCheckForAdd(index);
        if(index == size) {
            final Node<T> t = tail;
            final Node<T> newNode = new Node<>(element, null, t);
            tail = newNode;
            if (t == null) {// in case when collection is empty
                head = newNode;
            } else {
                t.next = newNode;
            }
        } else {
            final Node<T> node = getNode(index);
            final Node<T> prev = node.prev;
            Node<T> newNode = new Node<>(element, node, prev);
            if(prev == null) {
                head = newNode;
            } else {
                prev.next = newNode;
            }
            node.prev = newNode;
        }
        size++;
    }

    @Override
    public void addAll(Collection<? extends T> c) {
        addAll(size, c);
    }

    @Override
    public void addAll(int index, Collection<? extends T> c) {
        rangeCheckForAdd(index);
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return;

        Node<T> prev;
        Node<T> target;
        if (index == size) {
            target = null;
            prev = tail;
        } else {
            target = getNode(index);
            prev = target.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") T e = (T) o;
            Node<T> newNode = new Node<>(e, null, prev);
            if (prev == null) {
                head = newNode;
            } else {
                prev.next = newNode;
            }
            prev = newNode;
        }

        if (target == null) {
            tail = prev;
        } else {
            prev.next = target;
            target.prev = prev;
        }

        size += numNew;
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return getNode(index).value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        int counter = 0;
        Node<T> curr = this.head;
        while (curr != null) {
            if(counter == index) {
                unlink(curr);
            }
            curr = curr.next;
            counter++;
        }
        return null;
    }

    @Override
    public void set(int index, T element) {
        rangeCheck(index);
        int counter = 0;
        Node<T> curr = this.head;
        while (curr != null) {
            if(counter == index) {
                curr.value = element;
            }
            curr = curr.next;
            counter++;
        }
    }

    @Override
    public int indexOf(T o) {
        int counter = 0;
        Node<T> curr = this.head;
        if(o != null) {
            while (curr != null) {
                if (curr.value.equals(o)) {
                    return counter;
                }
                curr = curr.next;
                counter++;
            }
        } else {
            while (curr != null) {
                if (curr.value == null) {
                    return counter;
                }
                curr = curr.next;
                counter++;
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
        int counter = 0;
        Object[] array = new Object[size];
        for(Node<T> curr = head; curr != null; curr = curr.next) {
            array[counter++] = curr.value;
        }
        return array;
    }


    public T getFirst() {
        if (head == null)
            throw new NoSuchElementException();
        return head.value;
    }


    public T getLast() {
        if (tail == null)
            throw new NoSuchElementException();
        return tail.value;
    }


    private class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node<T> next, Node<T> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private void unlink(Node<T> node) {
        final Node<T> prev = node.prev;
        final Node<T> next = node.next;

        if (prev != null) {
            prev.next = next;
            node.prev = null;
        } else {
            head = next;
        }

        if (next != null) {
            next.prev = prev;
            node.next = null;
        } else {
            tail = prev;
        }

        node.value = null;
        size--;
    }


    private Node<T> getNode(int index) {
        if (index < (size >> 1)) {
            Node<T> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<T> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    // to not throw ArrayIndexOutOfBoundsException and encapsulate implementation of list
    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }


    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
