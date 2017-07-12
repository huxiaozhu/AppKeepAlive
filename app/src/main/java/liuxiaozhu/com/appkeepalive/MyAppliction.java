package liuxiaozhu.com.appkeepalive;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import liuxiaozhu.com.appkeepalive.brodcast.MyBroadcast;
import liuxiaozhu.com.appkeepalive.service.MyServiceOne;

/**
 * Created by chenhui on 2017/7/12.
 * All Rights Reserved by YiZu
 */

public class MyAppliction extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        //开启系统时间广播(动态注册,不能静态注册)
        //部分机型会屏蔽时间广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);//系统时间，每分钟发送一次
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);//屏幕打开（解锁），
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);//屏幕关闭
        MyBroadcast myBroadcast = new MyBroadcast();
        registerReceiver(myBroadcast, intentFilter);
        startMyService();
    }

    public static Context getmContext() {
        return mContext;
    }
    /**
     * 开启双服务
     */
    private void startMyService() {
        Intent serviceOne = new Intent();
        serviceOne.setClass(this, MyServiceOne.class);
        startService(serviceOne);
    }

}
