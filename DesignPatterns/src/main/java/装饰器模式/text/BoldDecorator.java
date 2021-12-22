package 装饰器模式.text;

/**
 * @author itning
 * @since 2021/12/22 10:31
 */
public class BoldDecorator extends NodeDecorator{
    protected BoldDecorator(TextNode target) {
        super(target);
    }

    @Override
    public String getText() {
        return "<b>" + target.getText() + "</b>";
    }
}
