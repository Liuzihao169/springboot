package com.kuake.springbootHelloworld.component;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * @author hao
 * @create 2019-06-02 ${TIM}
 */
@Component
//当我们自定义DefaultErrorAttributes，自动配置就不会 帮我们配置该对象
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {

        Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);

        //requestAttributes这是一个包装了的request,取出我们转发过来的 ext
        Map<String,Object> ext = (Map<String, Object>) requestAttributes.getAttribute("ext", 0);
        errorAttributes.put("ext",ext);

        //添加额外的方法之后，返回
        return errorAttributes;
    }
}
