package com.nettlibrary.netty;

import com.nettlibrary.netty.handler.EncodeHandler;
import com.nettlibrary.netty.handler.ServersHandler;
import com.nettlibrary.netty.listener.ConnChannelFutureListener;
import com.nettlibrary.netty.handler.DecodeHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Administrator on 2018/1/2.
 */
public class NettyChatServer {
    private int PORT_NUMBER = 8888;
    private ChannelFuture channelFuture;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    /**
     * 用于分配处理业务线程的线程组个数
     */
    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2; //默认

    /**
     * 业务出现线程大小
     */
    protected static final int BIZTHREADSIZE = 4;

    /**
     * NioEventLoopGroup实际上就是个线程池,
     * NioEventLoopGroup在后台启动了n个NioEventLoop来处理Channel事件,
     * 每一个NioEventLoop负责处理m个Channel,
     * NioEventLoopGroup从NioEventLoop数组里挨个取出NioEventLoop来处理Channel
     */

    private static NettyChatServer nettyChatServer = null;

    public static NettyChatServer getNettyChatServer() {
        if (nettyChatServer == null) {
            nettyChatServer = new NettyChatServer();
        }
        return nettyChatServer;
    }

    private NettyChatServer() {
    }

    public void init() {
        try {
            bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
            workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    //   pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    // pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                    pipeline.addLast("encoder", new EncodeHandler());
                    pipeline.addLast("decoder", new DecodeHandler());
                    pipeline.addLast(new ServersHandler());
                }
            });
            channelFuture = b.bind(PORT_NUMBER).sync();
            channelFuture.addListener(new ConnChannelFutureListener());

        } catch (Exception e) {

        } finally {
           /* try {
                if (channelFuture != null) {
                    channelFuture.channel().closeFuture().sync();
                }
              //  shutDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }


    public void shutDown() {
        if (channelFuture != null && channelFuture.isSuccess()) {
            channelFuture.channel().closeFuture();
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
