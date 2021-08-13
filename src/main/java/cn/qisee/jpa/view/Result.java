package cn.qisee.jpa.view;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result<T> {

    private Date timestamp;

    private int status;

    private String message;

    private T data;

    public Result(ResultEnum status, T data){
        this.status = status.getCode();
        this.message = status.getMessage();
        this.data = data;
        timestamp = new Date();
    }
    public Result(int status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
        timestamp = new Date();
    }

    public Result(ResultEnum status){
        this.status = status.getCode();
        this.message = status.getMessage();
        timestamp = new Date();
    }

    public Result(int status, String message){
        this.status = status;
        this.message = message;
        timestamp = new Date();
    }
    public static <T> Result<T> ok(T data){
        return new Result<>(ResultEnum.SUCCESS, data);
    }

    public static <T> Result<T> error(T data){
        return new Result<>(ResultEnum.ERROR, data);
    }

    public static <T> Result<T> error(int status, String message, T data){
        return new Result<>(status, message, data);
    }

    public static <T> Result<T> error(ResultEnum status, T data){
        return new Result<>(status, data);
    }
}
