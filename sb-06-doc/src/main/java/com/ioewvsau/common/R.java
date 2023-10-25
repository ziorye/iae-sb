package com.ioewvsau.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "统一返回结果")
public class R {
    @Schema(description = "状态码")
    private Integer code;

    @Schema(description = "描述消息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    @Schema(description = "具体数据")
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
