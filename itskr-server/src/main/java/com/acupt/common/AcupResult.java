package com.acupt.common;

import lombok.Data;

import java.util.function.Function;

/**
 * @author liujie
 */
@Data
public class AcupResult<T> {

    private int code;
    private String msg;
    private T data;

    public boolean isSuccess() {
        return code == 0;
    }

    public AcupResult() {
    }

    private AcupResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private AcupResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public <R> AcupResult<R> transform() {
        return transform(null);
    }

    public <R> AcupResult<R> transform(Function<T, R> function) {
        AcupResult<R> result = new AcupResult<>();
        result.setCode(this.code);
        result.setMsg(this.msg);
        if (isSuccess() && function != null) {
            result.setData(function.apply(this.data));
        }
        return result;
    }

    public static <T> AcupResult<T> success() {
        return new AcupResult<T>();
    }

    public static <T> AcupResult<T> success(T data) {
        return new AcupResult<T>(0, null, data);
    }

    public static <T> AcupResult<T> error() {
        return new AcupResult<>(500, "");
    }

    public static <T> AcupResult<T> error(String msg) {
        return new AcupResult<>(500, msg);
    }
}
