package cn.mykine.ad.advice;

import cn.mykine.ad.exception.AdException;
import cn.mykine.ad.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jo@mykine
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest req,
                                                     AdException ex) {
        CommonResponse<String> response = new CommonResponse<>(-1,
                "business error");
        response.setData(ex.getMessage());
        log.error("req={},errorMsg:",req,ex);
        return response;
    }
}
