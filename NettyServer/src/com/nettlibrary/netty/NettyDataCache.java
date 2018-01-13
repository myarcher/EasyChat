package com.nettlibrary.netty;



import com.nettlibrary.netty.datas.NettyMess;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2018/1/2
 */
public class NettyDataCache {
    private Map<Long, NettyMess> outMess = null;
    private Map<Long, NettyMess> inMess = null;
    private static NettyDataCache nettyCache = null;

    public static NettyDataCache instanse() {
        if (nettyCache == null) {
            nettyCache = new NettyDataCache();
        }
        return nettyCache;
    }


    private NettyDataCache() {
        outMess = new HashMap();
        inMess = new HashMap();
    }

    public void addOut(NettyMess outMes) {
        outMess.put(outMes.getMessId(), outMes);
    }

    public void removeOut(long messId) {
        outMess.remove(messId);
    }

    public void addIn(NettyMess inMes) {
        inMess.put(inMes.getMessId(), inMes);
    }

    public void removeIn(long messId) {
        inMess.remove(messId);
    }

    public NettyMess getIn(long currentReadMesId) {
        return inMess.get(currentReadMesId);
    }

    public NettyMess getOut(long currentWriteMesId) {
        return outMess.get(currentWriteMesId);
    }
}