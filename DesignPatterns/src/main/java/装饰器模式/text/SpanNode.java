package 装饰器模式.text;

/**
 * @author itning
 * @since 2021/12/22 10:30
 */
public class SpanNode implements TextNode {
    private String text;

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return "<span>" + text + "</span>";
    }
}