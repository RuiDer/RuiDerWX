package com.ruider.handle;

import com.ruider.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by mahede on 2018/12/19.
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandle {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandle.class);


    @ExceptionHandler(value = Exception.class)
    public Result defaultExceptionHandle() {
        LOGGER.error("发生异常了！");
        Result result = new Result();
        result.setMessage("发生异常");
        result.setCode(Result.ERROR_CODE);

        return result;
    }
}
