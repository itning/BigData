package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理对象实现
 *
 * @author wangn
 */
public class ProxyObject {
    /**
     * 获取代理实例
     *
     * @return OriginalObjectImpl
     */
    public static OriginalObject getProxyObject() {
        //newProxyInstance 第一个参数 代理对象; 第二个参数 接口列表
        Object instance = Proxy.newProxyInstance(OriginalObjectImpl.class.getClassLoader(), new Class[]{OriginalObject.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //method:原实现类的方法 args:原实现类的参数列表 result:原实现类方法返回值
                Object result = method.invoke(new OriginalObjectImpl(), args);
                return (int) result + 10;
            }
        });
        return (OriginalObject) instance;
    }
}
