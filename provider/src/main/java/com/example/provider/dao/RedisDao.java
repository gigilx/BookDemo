package com.example.provider.dao;

import com.example.api.common.StatusCode;
import com.example.api.pojo.Book;
import com.example.api.pojo.ResultBook;
import com.example.api.util.JSONUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisDao {
    @Resource(name = "redisTemplate")
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ValueOperations<String, String> operations;

    //在缓存中添加/更新数据
    public void addBookRedis(Book book){
        String key = book.getName();
        String value = JSONUtil.toJSONString(book);
        operations.set(key, value , 60 ,  TimeUnit.SECONDS);
    }

    //从缓存中删除
    public void delete(String name) {
        operations.getAndDelete(name);
    }

    //从缓存中查找
    public ResultBook findByNameRedis(String name){
        String value = operations.get(name);
        if(value != null) {
            Book book = JSONUtil.parseObject(value, Book.class);
            return new ResultBook(StatusCode.SUCCESS , book);
        }
        return new ResultBook(StatusCode.FAIL , null);
    }
}
