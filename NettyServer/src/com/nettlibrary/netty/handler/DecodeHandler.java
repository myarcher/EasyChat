package com.nettlibrary.netty.handler;


import com.nettlibrary.netty.NettyChatHelper;
import com.nettlibrary.netty.datas.NettyChatType;
import com.nettlibrary.netty.datas.NettyMess;
import com.nettlibrary.netty.listener.MessageSendListener;
import com.nettlibrary.netty.until.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;


/**
 * 接收解析器
 *
 * @time: 16/10/8 12:14.<br></br>
 * @author: Created by moo<br></br>
 */

public class DecodeHandler extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        System.out.println("decode");
        try {
            ByteObjConverter read = new ByteObjConverter();
            Object obj = read.byteToObject(read.read(byteBuf));
            list.add(obj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
