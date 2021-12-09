package top.itning.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author itning
 * @since 2021/12/9 10:55
 */
@Slf4j
@Component
public class HttpServer implements InitializingBean {

    private final BusinessHandler businessHandler;

    @Autowired
    public HttpServer(BusinessHandler businessHandler) {
        this.businessHandler = businessHandler;
    }

    public void initNetty() throws InterruptedException {
        log.info("启动Netty服务");
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            new ServerBootstrap()
                    .channel(NioServerSocketChannel.class)
                    .group(bossGroup, workerGroup)
                    .handler(new LoggingHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler());
                            ch.pipeline().addLast(new HttpServerCodec());
                            ch.pipeline().addLast(new HttpObjectAggregator(1024 * 1024));
                            ch.pipeline().addLast(businessHandler);
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .bind(8080)
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(), new DefaultThreadFactory("nettyInit"));
        executorService.execute(() -> {
            try {
                initNetty();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
