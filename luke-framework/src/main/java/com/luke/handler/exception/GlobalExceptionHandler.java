package com.luke.handler.exception;

import com.luke.domain.ResponseResult;
import com.luke.enums.AppHttpCodeEnum;
import com.luke.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.log.SystemLogHandler;
import org.springframework.web.bind.annotation.*;


//@ControllerAdvice
//@ResponseBody

@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //打印异常信息
        log.error("出现了异常！ {}",e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        //打印异常信息
        log.error("出现了异常！ {}",e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,e.getMessage());
    }



}

