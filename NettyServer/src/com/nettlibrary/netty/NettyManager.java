package com.nettlibrary.netty;

import com.nettlibrary.netty.datas.NettyChatType;
import com.nettlibrary.netty.datas.NettyMess;
import com.nettlibrary.netty.datas.imp.IoNettySession;
import com.nettlibrary.netty.listener.MessageSendListener;
import com.nettlibrary.netty.listener.NettyDataListener;
import io.netty.channel.Channel;

import java.util.*;

/**
 * @author
 * @version 1.0
 * @date 2018/1/2
 */
public class NettyManager {
    private Map<String, NettyIoSession> mConnSet = null;
    private Map<Channel, String> mChannelSet = null;
    private Set<NettyDataListener> mDataset = null;
    private static NettyManager nettyManager = null;

    public static NettyManager instanse() {
        if (nettyManager == null) {
            nettyManager = new NettyManager();
        }
        return nettyManager;
    }

    public NettyManager() {
        mConnSet = new HashMap();
        mDataset = new HashSet<>();
        mChannelSet = new HashMap<>();
    }

    public void addDataCall(NettyDataListener listener) {
        mDataset.add(listener);
    }

    public void removeDataCall(NettyDataListener listener) {
        mDataset.remove(listener);
    }

    public void addConnet(String key, Channel channel) {
        if (!mConnSet.containsKey(key)) {
            NettyIoSession session = new IoNettySession(channel);
            mConnSet.put(key, session);
        }
        mChannelSet.put(channel, key);
    }

    public void addConnet(Channel channel) {
        String key = new Date().getTime() + "";
        NettyIoSession session = new IoNettySession(channel);
        mConnSet.put(key, session);
        mChannelSet.put(channel, key);
        System.out.println(key+"<>"+channel+"<>"+session);
    }

    public String getConnKey(Channel channel) {

        if (mChannelSet.containsKey(channel)) {
            return  mChannelSet.get(channel);
        }
        return null;
    }
    public NettyIoSession getSession(Channel channel) {
        if (!mChannelSet.containsKey(channel)) {
            String key=  mChannelSet.get(channel);
            return mConnSet.get(key);
        }
        return null;
    }

    public void removeConnet(String key) {
        if (mConnSet.containsKey(key)) {
            NettyIoSession channel = mConnSet.get(key);
            if (channel != null) {
                mConnSet.remove(key);
            }
        }
    }

    public void removeConnet(Channel channel) {
        if (mChannelSet.containsKey(channel)) {
            String key = mChannelSet.get(channel);
            removeConnet(key);
            mChannelSet.remove(channel);
        }
    }

    public NettyIoSession getSession(String key) {
        if (mConnSet.containsKey(key)) {
            NettyIoSession session = mConnSet.get(key);
            if (session != null) {
                return session;
            }
        }
        return null;
    }

    public void onDataCall(Channel channel, Object messes, Exception e) {
        Iterator<NettyDataListener> md = mDataset.iterator();
        while (md.hasNext()) {
            NettyDataListener li = md.next();
            if (messes != null) {
                li.onReceived(messes, channel);
            } else {
                li.onError(e, channel);
            }
        }
    }


}