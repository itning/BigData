package 桥接模式.coffee;

/**
 * @author itning
 * @since 2021/12/21 18:07
 */
public class LargeCoffee extends RefinedCoffee {

    public LargeCoffee(ICoffeeAdditives iCoffeeAdditives) {
        super(iCoffeeAdditives);
    }

    @Override
    public void orderCoffee(int count) {
        additives.addSomething();
        System.out.println("大杯咖啡" + count + "杯");
    }
}
