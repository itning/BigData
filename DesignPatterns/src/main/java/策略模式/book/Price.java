package 策略模式.book;

/**
 * @author itning
 * @since 2021/12/22 15:09
 */
public class Price {
    //持有一个具体的策略对象
    private final MemberStrategy strategy;

    /**
     * 构造函数，传入一个具体的策略对象
     *
     * @param strategy 具体的策略对象
     */
    public Price(MemberStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 计算图书的价格
     *
     * @param booksPrice 图书的原价
     * @return 计算出打折后的价格
     */
    public double quote(double booksPrice) {
        return this.strategy.calcPrice(booksPrice);
    }
}
