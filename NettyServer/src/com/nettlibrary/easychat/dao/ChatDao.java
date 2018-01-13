package com.nettlibrary.easychat.dao;

import com.nettlibrary.easychat.untils.DBConnectUntil;

/**
 * Created by Administrator on 2018/1/5.
 */
public class ChatDao {
    private DBConnectUntil dbConUntil;
    public ChatDao(){
        dbConUntil=DBConnectUntil.getConnUntil();
    }


}
