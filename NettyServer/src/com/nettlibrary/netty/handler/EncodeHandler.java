/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.nettlibrary.netty.handler;

import com.nettlibrary.netty.datas.NettyMess;
import com.nettlibrary.netty.until.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 发送编码器
 *
 * @time: 16/10/8 12:14.<br></br>
 * @author: Created by moo<br></br>
 */

public class EncodeHandler extends MessageToByteEncoder<NettyMess> {

    @Override
    public void encode(ChannelHandlerContext ctx, NettyMess msg, ByteBuf out) {
        System.out.println("encode");
        try {
            byte[]  datas = ByteObjConverter.objectToByte(msg);
                out.writeBytes(datas);
                ctx.flush();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
