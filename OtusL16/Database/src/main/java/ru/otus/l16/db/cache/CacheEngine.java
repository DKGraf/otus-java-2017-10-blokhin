package ru.otus.l16.db.cache;

public interface CacheEngine<K, V> {
    void put(CacheElement<K, V> element);

    CacheElement<K, V> get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();

    int getCacheSize();

    int getMaxElements();

    int getHit();

    int getMiss();

    long getLifeTimeMs();
}
