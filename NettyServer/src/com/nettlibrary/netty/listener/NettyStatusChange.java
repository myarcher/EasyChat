package com.nettlibrary.netty.listener;

/**
 *
 *
 * @author
 * @date    2018/1/2
 * @version 1.0
 */ 
public interface NettyStatusChange{
    public void onSuccess();
    public void onFaild();
}