package com.zero.system.webSocket;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


/**
 *  在websocket里面 直接通过@Autowire注解 调用service会报空指针
 *  所以我们要通过spring上下文的方式(ApplicationContext)拿到service层
 */

@Component
@Lazy(false)
public class ApplicationContextRegister implements ApplicationContextAware {


    private static ApplicationContext APPLICATION_CONTEXT;

    /**
     * 设置spring上下文  *  * @param applicationContext spring上下文  * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

}
