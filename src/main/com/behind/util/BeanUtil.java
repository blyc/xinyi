package behind.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

public class BeanUtil {
    public static Object RequestToBeanUtil(HttpServletRequest request, Object bean) {
        //取出request中的所有属性
        Enumeration<String> e = request.getParameterNames();
        //遍历
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            String value = request.getParameter(name);
            //设置到bean中
            try {
                org.apache.commons.beanutils.BeanUtils.setProperty(bean, name, value);
            } catch (IllegalAccessException | InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
        return bean;
    }
    public static Object RequestToBeanUtil2(HttpServletRequest request, Object bean) {
        //取出request中的所有属性
        Enumeration<String> e = request.getAttributeNames();
        //遍历
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            String value = request.getAttribute(name).toString();
            //设置到bean中
            try {
                org.apache.commons.beanutils.BeanUtils.setProperty(bean, name, value);
            } catch (IllegalAccessException | InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
        return bean;
    }
}
