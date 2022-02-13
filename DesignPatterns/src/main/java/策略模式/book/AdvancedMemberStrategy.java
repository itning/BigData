package 策略模式.book;

/**
 * @author itning
 * @since 2021/12/22 15:09
 */
public class AdvancedMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {

        System.out.println("对于高级会员的折扣为20%");
        return booksPrice * 0.8;
    }
}