package 装饰器模式.text;

/**
 * @author itning
 * @since 2021/12/22 10:31
 */
public abstract class NodeDecorator implements TextNode {
    protected final TextNode target;

    protected NodeDecorator(TextNode target) {
        this.target = target;
    }

    @Override
    public void setText(String text) {
        this.target.setText(text);
    }
}