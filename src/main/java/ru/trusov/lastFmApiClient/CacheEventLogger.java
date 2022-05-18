package ru.trusov.lastFmApiClient;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheEventLogger implements CacheEventListener<Object, Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheEventLogger.class);

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        LOGGER.info(String.format("-------Cache (key=%s, oldCache=%s, newCache=%s): ",
                cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue())
        );
    }

}
