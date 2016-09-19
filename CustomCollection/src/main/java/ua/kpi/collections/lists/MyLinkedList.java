package ua.kpi.collections.lists;

import java.util.Collection;

public class MyLinkedList<T> implements  MyList<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() {

    }

    public MyLinkedList(Collection<? extends T> c) {
        for (T t : c) {
            add(t);
        }
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

    }

    @Override
    public void addAll(Collection<? extends T> c) {

    }

    @Override
    public void addAll(int index, Collection<? extends T> c) {

    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        int counter = 0;
        Node<T> curr = this.head;
        while (curr != null) {
            if(counter == index) {
                return curr.value;
            }
            curr = curr.next;
            counter++;
        }
        return null;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        int counter = 0;
        Node<T> curr = this.head;
        while (curr != null) {
            if(counter == index) {
                Node<T> prev = curr.prev;
                Node<T> next = curr.next;
                prev.next = next;
                next.prev = prev;
                curr.next = curr.prev = null; // for GC
                return curr.value;
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
        return new Object[0];
    }


    private class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(T value) {
            this.value = value;
        }
    }


    // to not throw ArrayIndexOutOfBoundsException and encapsulate implementation of list
    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

}
