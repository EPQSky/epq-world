package icu.epq.common.exception;

import icu.epq.common.api.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义异常处理
 *
 * @author EPQ
 */
@RestControllerAdvice
public class WorldExceptionHandler {

    @ExceptionHandler(WorldException.class)
    public R<?> respResult(WorldException e) {
        if (e != null) {
            return e.getResp();
        }

        return R.fail("操作失败");
    }

}
