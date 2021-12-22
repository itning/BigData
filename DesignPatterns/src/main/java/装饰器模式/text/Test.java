package 装饰器模式.text;

/**
 * 对某个节点进行装饰 增加功能
 *
 * @author itning
 * @since 2021/12/22 10:31
 */
public class Test {
    public static void main(String[] args) {
        TextNode n1 = new SpanNode();
        n1.setText("aa");
        BoldDecorator boldDecorator = new BoldDecorator(n1);
        System.out.println(boldDecorator.getText());
    }
}
