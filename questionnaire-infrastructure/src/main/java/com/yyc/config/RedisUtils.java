package com.yyc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redis封装
 *
 * @author yuchengyao
 */
@Component
public final class RedisUtils {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOps;
    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;
    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOps;
    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zsetOps;
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOps;

    public void setValue(String key, Object value) {
        valueOps.set(key, value);
    }

    public void setString(String key, String value, Long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public Object getValue(String key) {
        return valueOps.get(key);
    }

    /**
     * 返回值为设置成功的value数
     *
     * @param key
     * @param value
     * @return
     */
    public Long setSet(String key, String... value) {
        return setOps.add(key, value);
    }

    /**
     * 返回值为redis中键值为key的value的Set集合
     *
     * @param key
     * @return
     */
    public Set<String> getSetMembers(String key) {
        return setOps.members(key);
    }

    public Boolean setZSet(String key, String value, double score) {
        return zsetOps.add(key, value, score);
    }

    public Double getZSetScore(String key, String value) {
        return zsetOps.score(key, value);
    }

    public void expire(String key, Long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    public boolean isExistKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * GET key
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * SET key value
     *
     * @param key
     * @param value
     */
    public void setString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setString(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * INCRBY key delta
     * 注： 确保key下的值是数字
     *
     * @param key
     * @param delta
     * @return
     */
    public Long incString(String key, Long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * HSET key field value
     *
     * @param key
     * @param field
     * @param value
     */
    public void setHashField(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * HSET key [field value, ..]
     *
     * @param key
     * @param fieldValues
     */
    public void setHashFieldsByMap(String key, Map<String, String> fieldValues) {
        redisTemplate.opsForHash().putAll(key, fieldValues);
    }

    public Long removeHashFields(String key, Object[] fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * HEXISTS key field == 1
     *
     * @param key
     * @param field
     * @return
     */
    public boolean isExistFieldInHash(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * HGET key field
     * 注： return String
     *
     * @param key
     * @param field
     * @return
     */
    public String getFieldValueInHash(String key, String field) {
        return (String) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * HGETALL key
     *
     * @param key
     * @return
     */
    public Map<Object, Object> getHashMapInHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * KEYS key
     *
     * @param keyPattern
     * @return
     */
    public Set<String> getKeys(String keyPattern) {
        return redisTemplate.keys(keyPattern);
    }

    /**
     * DEL key
     *
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * DEL keys
     *
     * @param keys
     */
    public void deleteKeys(Collection<String> keys) {
        Set<String> newKeys = new HashSet<>();
        for (String key : keys) {
            key = key;
            newKeys.add(key);
        }
        redisTemplate.delete(newKeys);
    }

    /**
     * 设置list
     *
     * @param key     redis kye
     * @param value   redis value
     * @param timeout redis中的过期时间
     * @param unit    时间单位
     */
    public void setListExpire(String key, String value, Long timeout, TimeUnit unit) {
        redisTemplate.opsForList().leftPush(key, value);
        redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 取list
     *
     * @param key redis kye
     * @return
     */
    public List<String> getListRemove(String key) {
        List<String> range = redisTemplate.opsForList().range(key, 0, -1);
        redisTemplate.delete(key);
        return range;
    }
}
