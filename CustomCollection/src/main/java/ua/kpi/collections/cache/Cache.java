package ua.kpi.collections.cache;


public interface Cache<K, V> {

    V put(K key, V value);

    V get(K key);

    V evict(K key);

    void evictAll();

}
