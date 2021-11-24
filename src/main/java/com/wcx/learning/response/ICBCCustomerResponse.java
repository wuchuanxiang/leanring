package com.wcx.learning.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author wuchuanxiang
 * @date 2021/9/8
 */
@Data
public class ICBCCustomerResponse<T> {

    @JsonAlias({"Code","code"})
    private String code;
    @JsonAlias({"Message","message"})
    private String msg;
    @JsonAlias({"Data","data"})
    private T data;
}
