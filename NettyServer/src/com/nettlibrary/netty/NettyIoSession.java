package com.nettlibrary.netty;

import com.nettlibrary.netty.datas.NettyMess;
import com.nettlibrary.netty.listener.MessageSendListener;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import java.net.SocketAddress;

/**
 *
 *
 * @author
 * @date    2017/12/29
 * @version 1.0
 */
public interface NettyIoSession {
    public long getId();

    public Channel getChannel();

    public void setChannel(Channel channel);
    public void read();

    public void write(NettyMess var1);
    public void write(NettyMess var1,MessageSendListener sendListener);

    public void setConnected(boolean isConnected);
    public boolean isConnected();

    public boolean isActive();

    public boolean isClosing();


    public void setServiceAddress(SocketAddress address);

    public SocketAddress getServiceAddress();


    public Object getCurrentReadMessage();


    public Object getCurrentWriteMessage();


    public long getCreationTime();
    public void setCreationTime(long createTime);

    public long getLastIoTime();
    public void setLastIoTime(long lastTime);
    public long getLastReadTime();
    public void setLastReadTime(long lastReadTime);
    public long getLastWriteTime();
    public void setLastWriteTime(long lastWriteTime);
    public long getCId();
    public void setCId(long cid);
}