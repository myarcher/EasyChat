package com.nettlibrary.netty.datas.imp;



import com.nettlibrary.netty.datas.NettyMesBodyType;
import com.nettlibrary.netty.datas.NettyMessBody;

import java.io.Serializable;

/**
 *
 *
 * @author
 * @date    2017/12/29
 * @version 1.0
 */
public class NettyTextMessBody extends NettyMessBody<String> implements Serializable {
    private String textMes = "";

    private static final long serialVersionUID=7981560250804078637l;
   public NettyTextMessBody(String textMes){
        this.textMes = textMes;
        netBodyType = NettyMesBodyType.NettyMesBodyType_TEXT;
    }


    @Override
    public String getMesBody() {
        return textMes.toString();
    }



}