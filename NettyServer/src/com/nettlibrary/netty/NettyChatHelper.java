package com.nettlibrary.netty;


import com.nettlibrary.netty.datas.NettyChatType;
import com.nettlibrary.netty.datas.NettyMess;
import com.nettlibrary.netty.datas.imp.NettyTextMessBody;
import com.nettlibrary.netty.listener.MessageSendListener;

/**
 *
 *
 * @author
 * @date    2018/1/2
 * @version 1.0
 */
public class NettyChatHelper {

    public static NettyMess createTxtSendMessage(NettyChatType ChatType, String content , String conId){
        NettyTextMessBody textBody =new NettyTextMessBody(content);
        String from = "654321";
        NettyMess message =new NettyMess(ChatType, conId, from);
        message.setChatMesBody(textBody);
        return message;
    }

    public static void sendMessage(String key,NettyMess mes ,MessageSendListener sendListener) {
        try {
            NettyDataCache.instanse().addOut(mes);
            System.out.println("json" +"<testSend>" +key);
            System.out.println("json" +"<testSend>" +NettyManager.instanse().getSession(key));
            if (NettyManager.instanse().getSession(key) != null) {
                NettyManager.instanse().getSession(key).write(mes, sendListener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}