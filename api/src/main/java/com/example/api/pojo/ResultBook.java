package com.example.api.pojo;

import com.example.api.common.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //自动实现get、set方法
@AllArgsConstructor //包含全部参数的构造函数
@NoArgsConstructor  //无参构造函数

//返回给前端的Book类
public class ResultBook {
    private StatusCode code;
    private Book book;

    public ResultBook(StatusCode code){
        this.code = code;
        this.book = null;
    }

}
