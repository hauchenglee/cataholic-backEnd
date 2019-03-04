package com.web.exception.controller;

import com.web.exception.model.RestResult;
import com.web.exception.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

// 此處為第一種異常處理
// 其他方法的異常處理：
// 1.https://www.jianshu.com/p/12e1a752974d
// 2.https://blog.csdn.net/kinginblue/article/details/70186586

// 第三種是使用annotation -> @ResponseStatus
// 就資料來看，這種適合特異性的異常處理
// 此處有他人的建議：
// 不要在@Controller中自己进行异常处理逻辑。即使它只是一个Controller相关的特定异常，在@Controller中添加一个@ExceptionHandler方法处理。
// 对于自定义的异常，可以考虑对其加上@ResponseStatus注解
// 使用@ControllerAdvice处理通用异常（例如资源不存在、资源存在冲突等）
// 相關資料參考：
// http://xiaoqiangge.com/aritcle/1497859085696.html
// https://zhuanlan.zhihu.com/p/20774921
// https://blog.csdn.net/yalishadaa/article/details/71480694
// https://www.cnblogs.com/hupengcool/p/4586910.html
// https://elim.iteye.com/blog/2426037

// 1. 這裡@ControllerAdvice註解標註，@ControllerAdvice是@Controller的增強版，一般與@ExceptionHandler搭配使用。
// 2. 如果標註@Controller，異常處理只會在當前controller類中的方法起作用，但是使用@ControllerAdvice，則全域性有效。
// 3. @ExceptionHandler註解裡面填寫想要捕獲的異常類class物件
@ControllerAdvice
public class ExceptionHandlerController {
    private final ResultGenerator generator;

    @Autowired
    public ExceptionHandlerController(ResultGenerator generator) {
        this.generator = generator;
    }

    /**
     * 为参数验证添加异常处理器
     * @param cve
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public RestResult handleConstraintViolationException(ConstraintViolationException cve) {
        //cve.getConstraintViolations 会得到所有错误信息的迭代，可以酌情处理
        String errorMessage = cve.getConstraintViolations().iterator().next().getMessage();
        return generator.getFailResult("ConstraintViolationException" + errorMessage);
    }

    /**
     * 主键/唯一约束违反异常
     * @param e
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody // @RestController = @Controller + @ResponseBody
    public RestResult handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        //如果注册两个相同的用户名到报这个异常
        return generator.getFailResult("DataIntegrityViolationException: " + e.getMessage());
    }

    /**
     * Deal with all exception
     * 此處的ExceptionHandler會把前面的ConstraintViolationException的結果覆蓋
     * 由此處來處理異常，卻不會覆蓋DataIntegrityViolationException的處理程序
     * 原因不明。
     * @param e
     * @return
     */
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public RestResult exceptionHandler(Exception e) {
//        return generator.getFailResult("Exception: " + e.getMessage());
//    }
}
