package com.nettlibrary.netty.until;

import com.nettlibrary.netty.NettyChatHelper;
import com.nettlibrary.netty.NettyDataCache;
import com.nettlibrary.netty.datas.NettyChatType;
import com.nettlibrary.netty.datas.NettyMess;
import com.nettlibrary.netty.listener.MessageSendListener;
import io.netty.channel.ChannelHandlerContext;


/**
 * Created by Administrator on 2018/1/5.
 */
public class NettUntil {

    public static void testSend(String key) {
        try {
            System.out.println("json" +"<testSend>" );
            NettyChatHelper chathelp = new NettyChatHelper();
            NettyMess mess = chathelp.createTxtSendMessage(NettyChatType.NettyChatType_SINGLE, "1233", "654321");
            chathelp.sendMessage(key,mess, new MessageSendListener() {
                @Override
                public void sendError(String mes, int code) {
                    System.out.println("json" + mes + "<>" + code);
                }

                @Override
                public void sendSuccess(NettyMess message) {
                    NettyDataCache.instanse().removeOut(mess.getMessId());
                    System.out.println("json" + "sendSuccess");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static String getIPString(ChannelHandlerContext ctx) {
        String ipString = "";
        String socketString = ctx.channel().remoteAddress().toString();
        int colonAt = socketString.indexOf(":");
        ipString = socketString.substring(1, colonAt);
        return ipString;
    }


    public static String getRemoteAddress(ChannelHandlerContext ctx) {
        String socketString = "";
        socketString = ctx.channel().remoteAddress().toString();
        return socketString;
    }

    public static String getKeyFromArray(byte[] addressDomain) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            sBuffer.append(addressDomain[i]);
        }
        return sBuffer.toString();
    }

    public static String to8BitString(String binaryString) {
        int len = binaryString.length();
        for (int i = 0; i < 8 - len; i++) {
            binaryString = "0" + binaryString;
        }
        return binaryString;
    }

    public static byte[] combine2Byte(byte[] bt1, byte[] bt2) {
        byte[] byteResult = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, byteResult, 0, bt1.length);
        System.arraycopy(bt2, 0, byteResult, bt1.length, bt2.length);
        return byteResult;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
            stringBuilder.append(' ');
        }
        return stringBuilder.toString();
    }


}
