package com.nettlibrary.netty.listener;

import com.nettlibrary.netty.NettyDataCache;
import com.nettlibrary.netty.datas.NettyChatStatus;
import com.nettlibrary.netty.datas.NettyMess;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 *
 *
 * @author
 * @date    2018/1/3
 * @version 1.0
 */
public abstract class NettyChannelFutureListener implements ChannelFutureListener {
    private NettyMess mess= null;
    private MessageSendListener sendListener = null;

   public NettyChannelFutureListener(NettyMess mess,MessageSendListener sendListener) {
        this.mess = mess;
        this.sendListener = sendListener;
    }

    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        try {
            NettyMess mes = NettyDataCache.instanse().getOut(mess.getMessId());
            if (mes != null) {
                mess.setChatStatus(NettyChatStatus.NettyMessageStatus_SUCCESS);
                if (sendListener!=null) {
                    sendListener.sendSuccess(mess);
                }
                NettyDataCache.instanse().removeOut(mess.getMessId());
                callBak(1);
            } else {
                mess.setChatStatus(NettyChatStatus.NettyMessageStatus_FAIL);
                if (sendListener!=null) {
                    sendListener.sendError("", 2);
                }
                callBak(2);
            }
        } catch (Exception e) {
            mess.setChatStatus(NettyChatStatus.NettyMessageStatus_FAIL);
            if (sendListener!=null) {
                sendListener.sendError(e.getMessage(), 1);
            }
            callBak(2);
        }

    }

    abstract public void callBak(int status);
}