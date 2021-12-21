package 桥接模式.coffee;

/**
 * @author itning
 * @since 2021/12/21 18:06
 */
public class Test {
    public static void main(String[] args) {
        //点两杯加奶的大杯咖啡
        RefinedCoffee largeWithMilk = new LargeCoffee(new Milk());
        largeWithMilk.orderCoffee(2);
        largeWithMilk.checkQuality();

        RefinedCoffee s = new LargeCoffee(new Sugar());
        s.orderCoffee(1);
    }
}
