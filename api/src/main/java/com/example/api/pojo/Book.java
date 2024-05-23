package com.example.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data   //自动实现get、set方法
@AllArgsConstructor //包含全部参数的构造函数
@NoArgsConstructor  //无参构造函数
public class Book implements Serializable {
    private Long id;
    private String name;
    private String author;
    private String content;
}

