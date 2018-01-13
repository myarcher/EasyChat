package com.nettlibrary.netty.listener;


import com.nettlibrary.netty.NettyManager;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * @author
 * @version 1.0
 * @date 2017/12/29
 */
public class ConnChannelFutureListener implements ChannelFutureListener {
    public ConnChannelFutureListener() {
    }

    @Override
    public void operationComplete(ChannelFuture future) {
        System.out.println("连接");
    }

}