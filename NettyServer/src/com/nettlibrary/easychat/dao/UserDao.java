package com.nettlibrary.easychat.dao;

import com.nettlibrary.easychat.bean.UserInfo;
import com.nettlibrary.easychat.untils.BaseUntil;
import java.sql.ResultSet;

/**
 * Created by Administrator on 2018/1/5.
 */
public class UserDao extends BaseDao {
   public UserDao(){
        super();
    }
    public UserInfo getUser(String account, String pwd) {
        long uid = createAccount(account, pwd);
        if (uid != -1) {
            UserInfo info = createUser(uid);
            return info;
        }
        return null;
    }

    public UserInfo createUser(long uid) {
        UserInfo info = new UserInfo(uid);
        String sql = "insert into db_userinfo (_id,uid,uname,uphone,level,sex) values(?,'" + uid + "','" + info.getUname() + "','" + info.getUphone() + "','" + info.getLevel() + "','" + info.getSex() + "')";
        uid = create(sql, 1l, -1l);
        if (uid == 1) {
            return info;
        }
        return null;
    }


    public long createAccount(String account, String pwd) {
        long uid = BaseUntil.getTime();
        String sql = "insert into db_account (_id,account,pssword,uid) values(?,'" + account + "','" + pwd + "','" + uid + "')";
        uid = create(sql, uid, -1l);
        return uid;
    }

    public UserInfo getUser(long uid) {
        String sql = "select * from db_userinfo where uid='" + uid + "'";
        ResultSet set = query(sql);
        if (set != null) {
            try {
                UserInfo info = new UserInfo(set.getLong("uid"), set.getString("uname"),
                        set.getString("uphone"), set.getInt("level"), set.getInt("sex"));
                return info;
            } catch (Exception e) {
            }
        }
        return null;
    }
}
