package proxy.jdk;

/**
 * 动态代理 主方法
 *
 * @author wangn
 */
public class ProxyMain {
    public static void main(String[] args) {
        OriginalObject o = ProxyObject.getProxyObject();
        System.out.println(o.getAge());
    }
}
