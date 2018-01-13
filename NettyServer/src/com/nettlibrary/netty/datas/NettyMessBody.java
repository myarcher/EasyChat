package com.nettlibrary.netty.datas;

/**
 *
 *
 * @author
 * @date    2017/12/29
 * @version 1.0
 */
public abstract class NettyMessBody<T> {

   public NettyMesBodyType netBodyType  = NettyMesBodyType.NettyMesBodyType_NONE;
    private NettyMessBody<T> mesBody = null;

    public  NettyMesBodyType getMesBodyType(){
        return netBodyType;
    }

    public void  setMesBodyType(NettyMesBodyType netBodyType) {
        this.netBodyType = netBodyType;
    }

   public T getMesBody(){
        return (T)mesBody;
    }

   
}