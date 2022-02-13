package 桥接模式.coffee;

/**
 * @author itning
 * @since 2021/12/21 18:04
 */
public abstract class Coffee {
    protected ICoffeeAdditives additives;

    public Coffee(ICoffeeAdditives additives) {
        this.additives = additives;
    }

    public abstract void orderCoffee(int count);
}
