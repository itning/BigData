package top.itning.netty.chat.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.commons.lang3.SerializationUtils;
import top.itning.netty.chat.entity.ChatMessage;

import java.util.List;

/**
 * 解码器
 *
 * @author itning
 */
public class ChatDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        ChatMessage chatMessage = SerializationUtils.deserialize(bytes);
        out.add(chatMessage);
    }
}
