package proxy.cglib;

/**
 * 原来对象
 *
 * @author itning
 */
public class OriginalObjectImpl implements OriginalObject {
    /**
     * 普通业务方法
     *
     * @return 返回年龄信息
     */
    @Override
    public int getAge() {
        return 15;
    }
}
