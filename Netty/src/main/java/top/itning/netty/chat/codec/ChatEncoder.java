package top.itning.netty.chat.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.commons.lang3.SerializationUtils;
import top.itning.netty.chat.entity.ChatMessage;

/**
 * 编码器
 *
 * @author itning
 */
public class ChatEncoder extends MessageToByteEncoder<ChatMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ChatMessage msg, ByteBuf out) throws Exception {
        out.writeBytes(SerializationUtils.serialize(msg));
    }
}
