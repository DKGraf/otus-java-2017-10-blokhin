package ru.otus.l15.db.cache;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class CacheEngineImpl<K, V> implements CacheEngine<K, V> {
    private final int maxElements;
    private final long lifeTimeMs;
    private final Map<K, CacheElement<K, V>> elements = new ConcurrentHashMap<>();
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;

    public CacheEngineImpl(int maxElements, long lifeTimeMs) {
        this.maxElements = maxElements;
        this.lifeTimeMs = lifeTimeMs;
    }

    @Override
    public synchronized void put(CacheElement<K, V> element) {
        if (elements.size() == maxElements) {
            K firstKey = elements.keySet().iterator().next();
            elements.remove(firstKey);
        }

        K key = element.getKey();
        elements.put(key, element);

        if (lifeTimeMs != 0) {
            TimerTask lifeTimerTask = getTimerTask(key, lifeElement -> lifeElement.getCreationTime() + lifeTimeMs);
            timer.schedule(lifeTimerTask, lifeTimeMs);
        }
    }

    @Override
    public synchronized CacheElement<K, V> get(K key) {
        CacheElement<K, V> element = elements.get(key);
        if (element != null) {
            hit++;
            element.setAccessed();
        } else {
            miss++;
        }
        return element;
    }

    @Override
    public synchronized int getHitCount() {
        return hit;
    }

    @Override
    public synchronized int getMissCount() {
        return miss;
    }

    @Override
    public synchronized void dispose() {
        timer.cancel();
    }

    private synchronized TimerTask getTimerTask(final K key, Function<CacheElement<K, V>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                CacheElement<K, V> element = elements.get(key);
                if (element == null || isT1BeforeT2(timeFunction.apply(element), System.currentTimeMillis())) {
                    elements.remove(key);
                    this.cancel();
                }
            }
        };
    }

    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2;
    }

    public synchronized int getCacheSize() {
        return elements.size();
    }

    public long getLifeTimeMs() {
        return lifeTimeMs;
    }

    public int getMaxElements() {
        return maxElements;
    }

    public int getHit() {
        return hit;
    }

    public int getMiss() {
        return miss;
    }
}
