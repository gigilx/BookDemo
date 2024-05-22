package com.example.bookdemo.mapper;

import com.example.bookdemo.pojo.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    //增加书籍
    @Insert("insert into book (name, author, content) VALUES "+"(#{name},#{author},#{content})")
    int addBook(Book book);


    //删除
    @Delete("delete from book  where id = #{id}")
    int delete(Long id);

    //根据书名查询
    @Select("select * from book where name = #{name}")
    List<Book> findByName(String name);

    //根据作者查询
    @Select("select * from book where author = #{author}")
    List<Book> findByAuthor(String author);

    //更新书籍内容
    @Update("update book set content = #{content} where name = #{name} and author = #{author}")
    int update(Book book);
}
