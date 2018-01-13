package com.nettlibrary.netty.handler;

import com.nettlibrary.netty.NettyManager;
import com.nettlibrary.netty.datas.NettyMess;
import com.nettlibrary.netty.until.NettUntil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;


/**
 * Created by Administrator on 2018/1/2.
 */
public class ServersHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("CLIENT" +NettUntil.getRemoteAddress(ctx) + " 接入连接");
        //往channel map中添加channel信息
        System.out.println("CLIENT" +NettUntil.getIPString(ctx) + " 接入连接");
        NettyManager.instanse().addConnet(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //删除Channel Map中的失效Client
        System.out.println("CLIENT" + NettUntil.getIPString(ctx) + " 移除连接");
        NettyManager.instanse().removeConnet(ctx.channel());
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object messes){
        System.out.println("mess123");
        System.out.println("mess"+messes.toString());
        NettUntil.testSend(NettyManager.instanse().getConnKey(ctx.channel()));
        try {
            NettyManager.instanse().onDataCall(ctx.channel(), messes,null);
        }catch (Exception e){
            NettyManager.instanse().onDataCall(ctx.channel(), null ,e);
        }

       /* if (messes != null) {
            if(messes instanceof NettyMess){
                NettyMess me= (NettyMess) messes;
                System.out.println("mess"+me.getMesFrom()+"");
                String key=NettyManager.instanse().getSession();
                NettUntil.testSend();
            }else{

            }

        }*/
    }


    private void getDevice(Object messes){
     //   System.out.println("来自设备的信息：" + NettUntil.bytesToHexString(messes));
      //  byte byteA3 = msg[11];
        byte[] addressDomain = new byte[5];
       // System.arraycopy(msg, 7, addressDomain, 0, 5);
        String str1 = NettUntil.getKeyFromArray(addressDomain); //生成key
        System.out.println("根据地址域生成的Key为：" + str1);

     //   if (byteA3 == 0) {

       // } else {
            System.out.println("上述消息是从设备采集到的消息！");
         //   NettUntil.getMessageMap().put("1", msg);
     //   }
    }
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        String socketString = ctx.channel().remoteAddress().toString();

        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                System.out.println("Client: " + socketString + " READER_IDLE 读超时");
                ctx.disconnect();
            } else if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println("Client: " + socketString + " WRITER_IDLE 写超时");
                ctx.disconnect();
            } else if (event.state() == IdleState.ALL_IDLE) {
                System.out.println("Client: " + socketString + " ALL_IDLE 总超时");
                ctx.disconnect();
            }
        }
    }



}