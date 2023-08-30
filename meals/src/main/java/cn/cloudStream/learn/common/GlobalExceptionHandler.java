package cn.cloudStream.learn.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

//@ControllerAdvice(annotations = {RestController.class})
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /**
     *
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R sqlExceptionHandler(SQLIntegrityConstraintViolationException sqlExcp){
        log.error("The exception catched is: {}",sqlExcp.getMessage());
        if (sqlExcp.getMessage().contains("Duplicate entry")) {
            String split[] = sqlExcp.getMessage().split(" ");
//            for (String msg:split){
//                System.out.println(msg);
//            }
            return new R(0,"新增失败,"+split[2]+"已存在");
        }
        return R.error("新增失败，用户已存在或非法添加");
    }

    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex){
        log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    public R<String> exceptionHandler(Exception ex) {
//        log.error(ex.getMessage());
//        return R.error("内部错误");
                                                                                                                                                                                                        //    }
}
