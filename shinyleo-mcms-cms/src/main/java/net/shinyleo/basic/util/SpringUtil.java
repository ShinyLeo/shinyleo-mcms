package net.shinyleo.basic.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by shinyleo on 17/7/20.
 */
public class SpringUtil {

    /**
     * 获取当前请求对象
     * @return
     */
    public static HttpServletRequest getRequest(){
        try{
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 通过spring的webapplicationcontext上下文对象读取bean对象
     *
     * @param sc
     *            上下文servletConext对象
     * @param beanName
     *            要读取的bean的名称
     * @return 返回获取到的对象。获取不到返回null
     */
    public static Object getBean(ServletContext sc, String beanName) {
        return WebApplicationContextUtils.getWebApplicationContext(sc).getBean(beanName);
    }

    /**
     * 通过spring的webapplicationcontext上下文对象读取bean对象
     *
     * @param sc
     *            上下文servletConext对象
     * @param beanName
     *            要读取的bean的名称
     * @return 返回获取到的对象。获取不到返回null
     */
    public static Object getBean(String beanName) {
        return WebApplicationContextUtils.getWebApplicationContext(SpringUtil.getRequest().getServletContext()).getBean(beanName);
    }

    /**
     * 通过spring的webapplicationcontext上下文对象读取bean对象
     *
     * @param sc
     *            上下文servletConext对象
     * @param class
     *            要读取的类名称
     * @return 返回获取到的对象。获取不到返回null
     */
    public static Object getBean(Class cls) {
        return WebApplicationContextUtils.getWebApplicationContext(SpringUtil.getRequest().getServletContext()).getBean(cls);
    }
}
