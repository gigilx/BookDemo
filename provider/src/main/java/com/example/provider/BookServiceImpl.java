package com.example.provider;

import com.example.api.common.StatusCode;
import com.example.api.service.BookService;
import com.example.api.util.JSONUtil;
import com.example.provider.dao.RedisDao;
import com.example.provider.mapper.BookMapper;
import com.example.api.pojo.Book;
import com.example.api.pojo.ResultBook;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisConnectionDetails;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Component
@DubboService
@Slf4j
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private RedisDao redisDao;


    @Override
    public ResultBook addBook(Book book) {
        if (book == null) {
            return new ResultBook(StatusCode.PARAM_EMPTY);
        }
        try {
            bookMapper.addBook(book);
            redisDao.addBookRedis(book);
            return new ResultBook(StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBook(StatusCode.FAIL);
        }
    }

    @Override
    public ResultBook delete(String name) {
        if (name == null || name.length() == 0) {
            return new ResultBook(StatusCode.PARAM_EMPTY);
        }
        try {
            redisDao.delete(name);
            bookMapper.delete(name);
            redisDao.delete(name);
            return new ResultBook(StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBook(StatusCode.FAIL);
        }
    }

    @Override
    public ResultBook findByName(String name) {

        if (name == null || name.isEmpty()) {
            return new ResultBook(StatusCode.PARAM_EMPTY);
        }
        try {
            ResultBook res = redisDao.findByNameRedis(name);
            if(res.getCode() == StatusCode.SUCCESS)
                return res;
            List<Book> list = bookMapper.findByName(name);
            if (!list.isEmpty()) {
                ResultBook resBook = new ResultBook(StatusCode.SUCCESS);
                resBook.setBook(list.get(0));
                redisDao.addBookRedis(list.get(0));
                return resBook;
            } else {
                return new ResultBook(StatusCode.FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBook(StatusCode.FAIL);
        }
    }

    @Override
    public ResultBook findByAuthor(String author) {
        if (author == null || author.isEmpty()) {
            return new ResultBook(StatusCode.PARAM_EMPTY);
        }
        try {
            List<Book> list = bookMapper.findByAuthor(author);
            ResultBook resBook = new ResultBook(StatusCode.SUCCESS);
            resBook.setBook(list.get(0));
            return resBook;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBook(StatusCode.FAIL);
        }
    }

    @Override
    public ResultBook update(Book book) {
        if (book == null) {
            return new ResultBook(StatusCode.PARAM_EMPTY);
        }
        try {
            bookMapper.update(book);
            redisDao.addBookRedis(book);
            return new ResultBook(StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBook(StatusCode.FAIL);
        }
    }

//    @Override
//    public ResultBook addBookRedis(Book book) {
//        if (book == null) {
//            return new ResultBook(StatusCode.PARAM_EMPTY);
//        }
//        try {
//            redisDao.addBookRedis(book);
//            return new ResultBook(StatusCode.SUCCESS);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResultBook(StatusCode.FAIL);
//        }
//    }
//
//    @Override
//    public ResultBook findByNameRedis(String name) {
//        try {
//            return redisDao.findByNameRedis(name);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResultBook(StatusCode.FAIL);
//        }
//    }
}
