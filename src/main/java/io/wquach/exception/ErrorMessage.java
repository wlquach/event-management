package io.wquach.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by wquach on 6/4/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    public String errorMessage;
    public String detail;
}
