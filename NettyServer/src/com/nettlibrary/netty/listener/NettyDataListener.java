package com.nettlibrary.netty.listener;


import com.nettlibrary.netty.datas.NettyMess;
import com.nettlibrary.netty.NettyIoSession;
import io.netty.channel.Channel;

import java.util.List;

/**
 *
 *
 * @author
 * @date    2017/12/29
 * @version 1.0
 */ 
public interface NettyDataListener {

    public void onReceived(Object message, Channel channel);

     public  void onError(Exception e, Channel channel);
}