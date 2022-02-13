package 策略模式.book;

/**
 * @author itning
 * @since 2021/12/22 15:08
 */
public interface MemberStrategy {
    /**
     * 计算图书的价格
     *
     * @param booksPrice 图书的原价
     * @return 计算出打折后的价格
     */
    double calcPrice(double booksPrice);
}
