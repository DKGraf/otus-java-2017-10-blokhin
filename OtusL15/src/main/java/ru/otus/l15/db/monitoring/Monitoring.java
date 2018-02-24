package ru.otus.l15.db.monitoring;

import ru.otus.l15.db.cache.CacheEngine;
import ru.otus.l15.db.datasets.UserDataSet;
import ru.otus.l15.db.dbservice.DBServiceHibernateImpl;

import java.util.HashMap;
import java.util.Map;

public class Monitoring {
    private CacheEngine<Long, UserDataSet> cache;

    public Monitoring(DBServiceHibernateImpl dbService) {
        this.cache = dbService.getCache();
    }

    public Map<String, Object> getCacheState() {
        int currentCacheSize = cache.getCacheSize();
        int maxCacheSize = cache.getMaxElements();
        int cacheHitCount = cache.getHitCount();
        int cacheMissCount = cache.getMissCount();
        long lifeTime = cache.getLifeTimeMs();

        Map<String, Object> cacheState = new HashMap<>();
        cacheState.put("currentCacheSize", currentCacheSize);
        cacheState.put("maxCacheSize", maxCacheSize);
        cacheState.put("cacheHitCount", cacheHitCount);
        cacheState.put("cacheMissCount", cacheMissCount);
        cacheState.put("lifeTime", lifeTime);

        return cacheState;
    }
}