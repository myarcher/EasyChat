package com.nettlibrary.netty.listener;


import com.nettlibrary.netty.datas.NettyMess;

/**
 *
 *
 * @author
 * @date    2018/1/3
 * @version 1.0
 */ 
public interface MessageSendListener {
    public void sendSuccess(NettyMess message);

    public void  sendError(String mes,int code);
}