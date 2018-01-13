package com.nettlibrary.netty.listener;

/**
 *
 *
 * @author
 * @date    2017/12/29
 * @version 1.0
 */
public interface NettyConnectListener {
    public void onConnected();
    public void onDisconnected(int status);
}