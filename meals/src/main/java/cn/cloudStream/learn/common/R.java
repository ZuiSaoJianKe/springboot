package cn.cloudStream.learn.common;

import lombok.Data;

@Data
public class R<T> {
    public int code;
    public String msg;
    public T data;
    //第一种形式
    public  R(int code, String msg){
        this.code=code;
        this.msg=msg;
    }
    public  R(int code, T data){
        this.code=code;
        this.data=data;
    }
    public  R(int code,String msg,T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public R() {

    }
// 第二种形式
    public static<T> R<T> success(T data){
        R<T> r=new R<T>();
        r.code=1;
        r.msg="成功";
        r.data=data;
        return r;
    }
    public static<T> R<T> error(String msg){
        R<T> r=new R<T>();
        r.code=0;
        r.msg=msg;
        return r;
    }
}
