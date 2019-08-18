package com.mmall.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     *设置带有效期
     * @param key
     * @param value
     * @param exTime:”s“
     * @return
     */
    public void setEx(String key,String value,int exTime){

        try {

            stringRedisTemplate.opsForValue().set(key,value,exTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("setEx key:{},value:{} error",key,value,e);
        }
    }

    /*
    *
            * 重新设置key有效期
         * @param key
         * @param exTime 秒
         * @return

    */

    public Boolean expire(String key,int exTime){
        Boolean result  = null;
        try {

            result = stringRedisTemplate.expire(key,exTime,TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("expire key:{},error",key,e);
        }
        return result;
    }

/*
*
        * 设置key value
     * @param key
     * @param value
     * @return
*/


    public void set(String key,String value){
        try {
            stringRedisTemplate.opsForValue().set(key,value);
        } catch (Exception e) {
            log.error("set key:{},value:{} error",key,value,e);
        }
    }
/*

*
        * 获得key的value
     * @param key
     * @return
*/


    public String get(String key){
        String result  = null;
        try {
            result = stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("get key:{} error",key,e);
        }
        return result;
    }

    /*
    *
            * 删除
         * @param key
         * @return
    
    */

    public  boolean del(String key){

      return  stringRedisTemplate.delete(key);

    }

    //scheduel+redis
    public  Boolean setnx(String key,String value){
       return stringRedisTemplate.opsForValue().setIfAbsent(key,value);
    }
    public  String getSet(String key,String value){
       return stringRedisTemplate.opsForValue().getAndSet(key,value);
    }
}
