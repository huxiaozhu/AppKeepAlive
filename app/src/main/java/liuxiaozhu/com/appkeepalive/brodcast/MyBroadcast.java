package liuxiaozhu.com.appkeepalive.brodcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import liuxiaozhu.com.appkeepalive.MainActivity;
import liuxiaozhu.com.appkeepalive.MyAppliction;
import liuxiaozhu.com.appkeepalive.ServiceMangerUtils;
import liuxiaozhu.com.appkeepalive.service.MyServiceOne;


/**
 * Created by chenhui on 2017/5/11.
 * All Rights Reserved by
 * 用来监听手机时间广播（在appliytion里开启广播），每一分钟系统会发送一次
 * 该广播只需要拉起service1
 */

public class MyBroadcast extends BroadcastReceiver {
    private boolean isServiceRunning = false;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {//如果广播是每分钟发送一次的时间广播
            Log.e("timeBroad", "时间变化了");
            isServiceRunning = ServiceMangerUtils.isServiceWorked(MyAppliction.getmContext(), "myservice1");
            if (!isServiceRunning) {
                Intent i = new Intent(context, MyServiceOne.class);
                context.startService(i);
            }
        }
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.e("timeBroad", "屏幕解锁了");
            isServiceRunning = ServiceMangerUtils.isServiceWorked(MyAppliction.getmContext(), "myservice1");
            if (!isServiceRunning) {
                Intent i = new Intent(context, MyServiceOne.class);
                context.startService(i);
            }
        }
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.e("timeBroad", "屏幕关闭了");
            isServiceRunning = ServiceMangerUtils.isServiceWorked(MyAppliction.getmContext(), "myservice1");
            if (!isServiceRunning) {
                Intent i = new Intent(context, MyServiceOne.class);
                context.startService(i);
            }
        }
    }


}
