package com.ioewvsau.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {
    private Integer code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public static R ok() {
        return new R(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    public static R ok(Object data) {
        return new R(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static R ok(Integer status, Object data) {
        return new R(status, null, data);
    }

    public static R fail(String message) {
        return new R(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }

    public static R fail(Integer status, String message) {
        return new R(status, "fail", null);
    }
}
