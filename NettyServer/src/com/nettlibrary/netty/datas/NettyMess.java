package com.nettlibrary.netty.datas;


import com.nettlibrary.netty.helper.GsonHelper;
import com.nettlibrary.netty.listener.NettyStatusChange;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author
 * @version 1.0
 * @date 2017/12/29
 */

public class NettyMess implements Serializable {
    private static final long serialVersionUID=7981560250804078637l;
    private long mesId = 0;
    private String messFrom= "";
    private String mesTo = "";
    private NettyChatType chat_type = NettyChatType.NettyChatType_SINGLE;
    private NettyChatStatus mess_status = NettyChatStatus.NettyMessageStatus_NONE;
    private MettyChatDirection mess_directio = MettyChatDirection.None;
    private List<NettyMessBody> mesBody = null;
    private long create_time = 0;
    private boolean isRead = false;
    private Map<String, Object> extra = null;
    private Set<NettyStatusChange> statusChangs= null;


   public NettyMess () {
        extra =new HashMap();
        statusChangs = new HashSet();
    }

    public NettyMess(NettyChatType chat_type ,String mesTo ,String mesFrom ) {
        this.chat_type = chat_type;
        this.mesTo = mesTo;
        this.messFrom = mesFrom;
        create_time =new Date().getTime();
        mesId = create_time;
        mess_directio = MettyChatDirection.SEND;
        mess_status = NettyChatStatus.NettyMessageStatus_NEW;
        extra =new HashMap();
        statusChangs = new HashSet();
    }

    public long getMessId() {
        return mesId;
    }

    public void setMessId(Long mesid ) {
        this.mesId = mesid;
    }

    public String getMesFrom() {
        return messFrom;
    }

    public void setMesFrom(String mesFrom ) {
        this.messFrom = mesFrom;
    }

    public String getMessTo() {
        return mesTo;
    }

    public void setMessTo(String mesTo) {
        this.mesTo = mesTo;
    }

    public NettyChatType getChatType() {
        return chat_type;
    }

    public void setChatType(NettyChatType chat_type) {
        this.chat_type = chat_type;
    }

    public NettyChatStatus getChatStatus() {
        return mess_status;
    }

    public void setChatStatus(NettyChatStatus mess_status) {
        this.mess_status = mess_status;
        if(mess_status==NettyChatStatus.NettyMessageStatus_SUCCESS){
            onSuccess();
        }else{
            onFaild();
        }
    }

    public MettyChatDirection getChatDirectio(){
        return mess_directio;
    }

    public void setChatDirectio(MettyChatDirection mess_directio) {
        this.mess_directio = mess_directio;
    }

    public List<NettyMessBody> getChatMesBody(){
        return mesBody;
    }

    public void setChatMesBody(List<NettyMessBody> mesBody) {
        this.mesBody = mesBody;
        if (this.mesBody == null) {
            this.mesBody =new ArrayList();
        }
    }

    public void setChatMesBody(NettyMessBody mesBody) {
        if (this.mesBody == null) {
            this.mesBody =new  ArrayList();
        }
        this.mesBody.add(mesBody);
    }

    public long getCreateTime() {
        return create_time;
    }

    public void setCreateTime(long create_time) {
        this.create_time = create_time;
    }

    public boolean isReaded(){
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public void setAttribute(String key,Object value) {
        if (extra == null) {
            extra = new HashMap();
        }
        extra.put(key, value);
    }

    public void setAttributes(Map<String,Object> extras) {
        extra = extras;
    }

    public Object getAttribute(String key ) {
        return extra.get(key);
    }

    @Override
    public String toString() {
      return   GsonHelper.newInstances().toJson(this);
    }



    public byte[] read() {
        return GsonHelper.newInstances().toJson(this).getBytes();
    }


    public NettyMess write (byte []srtbyte){
        String message = null;
        try {
            message = new String(srtbyte,"UTF-8");
            return GsonHelper.newInstances().fromJson(message, this.getClass());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
     return null;
    }


    public void addStatusChange(NettyStatusChange statusChange) {
        statusChangs.add(statusChange);
    }

    public void onSuccess() {
         Iterator<NettyStatusChange> ite = statusChangs.iterator();
        while (ite.hasNext()) {
            NettyStatusChange statusChang = ite.next();
            statusChang.onSuccess();
        }
        if(mess_status!=NettyChatStatus.NettyMessageStatus_SUCCESS){
            mess_status=NettyChatStatus.NettyMessageStatus_SUCCESS;
        }
    }

     public void onFaild() {
         Iterator<NettyStatusChange> ite = statusChangs.iterator();
        while (ite.hasNext()) {
            NettyStatusChange statusChang = ite.next();
            statusChang.onFaild();
        }
        if(mess_status==NettyChatStatus.NettyMessageStatus_NEW){
            mess_status=NettyChatStatus.NettyMessageStatus_FAIL;
        }else if( mess_status==NettyChatStatus.NettyMessageStatus_FAIL){
            mess_status=NettyChatStatus.NettyMessageStatus_DELIVERING;
        }
    }
}
