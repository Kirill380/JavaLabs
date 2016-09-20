package ua.kpi.collections.cache;


import java.util.HashMap;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class LRUCache<K, V> implements Cache<K, V> {
    private int capacity;
    private HashMap<K, Node<K, V>> map = new HashMap<>();
    private Node<K, V> head;
    private Node<K, V> tail;


    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public V put(K key, V value) {
        checkNotNull(key);
        checkNotNull(value);
        if (map.containsKey(key)) {
            Node<K, V> n = map.get(key);
            V oldValue = n.value;
            n.value = value;
            unlink(n);
            addToHead(n);
            return oldValue;
        } else {
            Node<K, V> created = new Node<>(key, value);
            if (map.size() >= capacity) {
                evict(tail.key);
                addToHead(created);
            } else {
                addToHead(created);
            }

            map.put(key, created);
            return value;
        }
    }

    @Override
    public V get(K key) {
        checkNotNull(key);
        if (map.containsKey(key)) {
            Node<K, V> n = map.get(key);
            unlink(n);
            addToHead(n);
            return n.value;
        }
        return null;
    }




    @Override
    public V evict(K key) {
        checkNotNull(key);
        Node<K, V> n = map.get(key);
        final V value = n.value;
        unlink(n);
        n.value = null;
        map.remove(key);
        return value;
    }


    @Override
    public void evictAll() {
        Set<K> keys = map.keySet();
        for (K key : keys) {
            evict(key);
        }
    }

    private void unlink(Node<K, V> n) {
        final Node<K, V> prev = n.prev;
        final Node<K, V> next = n.next;

        if (prev != null) {
            prev.next = next;
            n.prev = null;
        } else {
            head = next;
        }

        if (next != null) {
            next.prev = prev;
            n.next = null;
        } else {
            tail = prev;
        }
    }


    private void addToHead(Node<K, V> n) {
        n.next = head;
        n.prev = null;

        if (head != null)
            head.prev = n;

        head = n;

        if (tail == null)
            tail = head;
    }


    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
