package proxy.cglib;

/**
 * 动态代理 主方法
 *
 * @author wangn
 */
public class ProxyMain {
    public static void main(String[] args) {
        ProxyObject cglib = new ProxyObject();
        OriginalObjectImpl originalObject = cglib.getInstance(new OriginalObjectImpl());
        System.out.println(originalObject.getAge());
    }
}
