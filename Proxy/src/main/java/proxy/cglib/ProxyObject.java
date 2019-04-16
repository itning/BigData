package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * JDK的动态代理机制只能代理实现了接口的类，
 * 而不能实现接口的类就不能实现JDK的动态代理，
 * cglib是针对类来实现代理的，
 * 他的原理是对指定的目标类生成一个子类，
 * 并覆盖其中方法实现增强，
 * 但因为采用的是继承，
 * 所以不能对final修饰的类进行代理。
 *
 * @author itning
 * @date 2019/4/16 14:48
 */
public class ProxyObject implements MethodInterceptor {
    /**
     * 创建代理对象
     *
     * @param t 要代理的类
     * @return 代理对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getInstance(T t) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(t.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象(无参)
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("事物开始");
        Object invokeSuper = methodProxy.invokeSuper(o, objects);
        System.out.println("事物结束");
        return (int) invokeSuper + 10;
    }
}
