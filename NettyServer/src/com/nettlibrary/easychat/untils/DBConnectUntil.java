package com.nettlibrary.easychat.untils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Administrator on 2018/1/5.
 */
public class DBConnectUntil {
    private String dbUrl = "jdbc:mysql://localhost:3306/easychat";
    private String dbuserName = "root";
    private String dbpassword = "123456";
    private String jdbcName = "com.mysql.jdbc.Driver";
    private static DBConnectUntil dbConnectUntil;
    public Connection cons;
    private void getCon() {
        try {
            Class.forName(jdbcName);
            cons = DriverManager.getConnection(dbUrl, dbuserName, dbpassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeCon(Connection con) throws Exception {
        if (con != null) {
            con.close();
        }
    }


    public static DBConnectUntil getConnUntil() {
        if (dbConnectUntil == null) {
            dbConnectUntil =new DBConnectUntil();
        }
        return dbConnectUntil;
    }

    private DBConnectUntil(){
        getCon();
    }
}
