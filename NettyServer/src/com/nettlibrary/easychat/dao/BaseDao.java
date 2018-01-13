package com.nettlibrary.easychat.dao;

import com.nettlibrary.easychat.untils.DBConnectUntil;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Administrator on 2018/1/5.
 */
public class BaseDao {
    public DBConnectUntil dbConUntil;
    public BaseDao(){
        dbConUntil=DBConnectUntil.getConnUntil();
    }
    public <T>T create(String sql,T t,T def) {
        try {
            Statement statement = dbConUntil.cons.createStatement();
            int result = statement.executeUpdate(sql);
            if (result == 1) {
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public ResultSet query(String sql) {
        try {
            Statement statement = dbConUntil.cons.createStatement();
            ResultSet result = statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
