package ru.otus.l12.base.monitoring;

import ru.otus.l12.base.DBServiceHibernateImpl;
import ru.otus.l12.base.cache.CacheEngine;
import ru.otus.l12.base.datasets.UserDataSet;

import java.util.HashMap;
import java.util.Map;

public class Monitoring {
    private Map<String, Object> cacheState = new HashMap<>();
    private CacheEngine<Long, UserDataSet> cache;

    public Monitoring(DBServiceHibernateImpl dbService) {
        this.cache = dbService.getCache();
        updateCacheState();
    }

    private void updateCacheState() {
        int currentCacheSize = cache.getCacheSize();
        int maxCacheSize = cache.getMaxElements();
        int cacheHitCount = cache.getHitCount();
        int cacheMissCount = cache.getMissCount();
        long lifeTime = cache.getLifeTimeMs();

        cacheState.put("currentCacheSize", currentCacheSize);
        cacheState.put("maxCacheSize", maxCacheSize);
        cacheState.put("cacheHitCount", cacheHitCount);
        cacheState.put("cacheMissCount", cacheMissCount);
        cacheState.put("lifeTime", lifeTime);
    }

    public Map<String, Object> getCacheState() {
        updateCacheState();
        return cacheState;
    }
}
