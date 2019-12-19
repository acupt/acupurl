package com.acupt.itskr.service;

import com.acupt.itskr.domain.constant.CounterEnum;
import com.acupt.itskr.persistence.model.CounterDO;
import com.acupt.itskr.persistence.repository.CounterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liujie
 */
@Slf4j
@Service
public class IdService {

    private static long INCR = 100;

    @Resource
    private CounterRepository counterRepository;

    private Map<String, IdCache> idCacheMap = new ConcurrentHashMap<>();

    private IdCache defaultCache = new IdCache(0, 0);

    @PostConstruct
    public void init() {
        log.info("counter init start");
        for (CounterEnum e : CounterEnum.values()) {
            CounterDO counterDO = counterRepository.getByName(e.getName());
            if (counterDO != null) {
                log.info("counter exist {}", counterDO);
                continue;
            }
            counterDO = new CounterDO();
            counterDO.setName(e.getName());
            counterDO.setCount(e.getInitValue());
            try {
                counterRepository.insert(counterDO);
            } catch (Exception ex) {
                log.info("insert counter failed {}", e.getName(), ex);
            }
        }
        log.info("counter init finish");
    }

    public long createId(String name) {
        IdCache idCache = idCacheMap.getOrDefault(name, defaultCache);
        long id = idCache.incr();
        if (id > 0) {
            return id;
        }
        synchronized (this) {
            idCache = idCacheMap.getOrDefault(name, defaultCache);
            id = idCache.incr();
            if (id > 0) {
                return id;
            }
            idCache = new IdCache(counterRepository.getAdd(name, INCR), INCR);
            idCacheMap.put(name, idCache);
            return idCache.incr();
        }
    }

    private static class IdCache {
        AtomicLong cur;
        long max;

        IdCache(long cur, long n) {
            this.cur = new AtomicLong(cur + 1);
            this.max = cur + n;
        }

        long incr() {
            long id = cur.incrementAndGet();
            if (id > max) {
                return 0;
            }
            return id;
        }
    }
}
