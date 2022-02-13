package 桥接模式.coffee;

/**
 * @author itning
 * @since 2021/12/21 18:06
 */
public class Sugar implements ICoffeeAdditives {
    @Override
    public void addSomething() {
        System.out.println("加糖");
    }
}
