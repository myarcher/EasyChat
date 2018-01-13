package com.nettlibrary.netty.datas.imp;

import com.nettlibrary.netty.listener.MessageSendListener;
import com.nettlibrary.netty.listener.NettyChannelFutureListener;
import com.nettlibrary.netty.datas.NettyMess;
import com.nettlibrary.netty.NettyDataCache;
import com.nettlibrary.netty.NettyIoSession;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.net.SocketAddress;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @date 2017/12/29
 */
public class IoNettySession implements NettyIoSession {

    private long id = 0;
    private long cid = 0;
    private long lastWriteTime = 0;
    private long lastReadTime = 0;
    private long lastTime = 0;
    private long createTime = 0;
    private Channel channel = null;
    private long currentReadMesId = 0;
    private long currentWriteMesId = 0;
    private SocketAddress serveraddress = null;
    private boolean isConnected = false;

    public IoNettySession() {

    }

    public IoNettySession(Channel channel) {
        this.channel = channel;
        init();
    }

    private void init() {
        long ctime = new Date().getTime();
        id = ctime;
        createTime = ctime;
    }


    @Override
    public long getId() {
        return id;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel=channel;
    }

    @Override
    public void read() {
        lastReadTime = new Date().getTime();
        channel.read();
    }

    @Override
    public void write(NettyMess mes) {
        write(mes, null);
    }

    @Override
    public void write(NettyMess mes, MessageSendListener sendListener) {
        lastWriteTime = new Date().getTime();
        currentWriteMesId = mes.getMessId();
        ChannelFuture future = channel.writeAndFlush(mes);
        future.addListener(new NettyChannelFutureListener(mes, sendListener) {
            @Override
            public void callBak(int status) {
                future.removeListener(this);
            }
        });
    }

    @Override
    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public boolean isActive() {
        return   channel.isActive();
    }

    @Override
    public boolean isClosing() {
        return !channel.isOpen();
    }

    @Override
    public void setServiceAddress(SocketAddress address) {
        this.serveraddress = address;
    }

    @Override
    public SocketAddress getServiceAddress() {
        return serveraddress;
    }

    @Override
    public NettyMess getCurrentReadMessage() {
        NettyMess mes = NettyDataCache.instanse().getIn(currentReadMesId);
        return mes;
    }

    @Override
    public NettyMess getCurrentWriteMessage() {
        NettyMess mes = NettyDataCache.instanse().getIn(currentWriteMesId);
        return mes;
    }

    @Override
    public long getCreationTime() {
        return createTime;
    }

    @Override
    public void setCreationTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public long getLastIoTime() {
        return lastTime;
    }


    @Override
    public void setLastIoTime(long lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public long getLastReadTime() {
        return lastReadTime;
    }

    @Override
    public void setLastReadTime(long lastReadTime) {
        this.lastReadTime = lastReadTime;
    }

    @Override
    public long getLastWriteTime() {
        return lastWriteTime;
    }

    @Override
    public void setLastWriteTime(long lastWriteTime) {
        this.lastWriteTime = lastWriteTime;
    }

    @Override
    public long getCId() {
        return cid;
    }

    @Override
    public void setCId(long cid) {
        this.cid = cid;
    }


}