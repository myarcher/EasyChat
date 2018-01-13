package com.nettlibrary.netty.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

/**
 * Created by moo on 16/10/8.
 */
public class GsonHelper {
    private static GsonHelper gsonHelper = null;
    private static Gson gson = null;

    private GsonHelper() {
        gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.PROTECTED) //@protected 修饰的过滤，比如自动增长的id
                .create();
    }


    public static Gson newInstances() {
        if (gsonHelper == null) {
            gsonHelper = new GsonHelper();
        }
        return gson;
    }

}
