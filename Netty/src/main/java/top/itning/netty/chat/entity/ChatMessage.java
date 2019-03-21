package top.itning.netty.chat.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 聊天消息
 *
 * @author itning
 */
public class ChatMessage implements Serializable {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private long currentTimeMillis;
    private String nickName;
    private String message;

    public ChatMessage() {
        this("");
    }

    public ChatMessage(String message) {
        this(UUID.randomUUID().toString(), message);
    }

    public ChatMessage(String nickName, String message) {
        this(System.currentTimeMillis(), nickName, message);
    }

    public ChatMessage(long currentTimeMillis, String nickName, String message) {
        this.currentTimeMillis = currentTimeMillis;
        this.nickName = nickName;
        this.message = message;
    }

    public long getCurrentTimeMillis() {
        return currentTimeMillis;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringBuilder(20)
                .append(SIMPLE_DATE_FORMAT.format(new Date(currentTimeMillis)))
                .append("\t")
                .append(nickName)
                .append("\t")
                .append(message)
                .toString();
    }
}
