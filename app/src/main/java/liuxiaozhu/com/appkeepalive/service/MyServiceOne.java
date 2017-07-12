package liuxiaozhu.com.appkeepalive.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import liuxiaozhu.com.appkeepalive.ServiceMangerUtils;


public class MyServiceOne extends Service {
    public final static String TAG = "myservice1";

    public MyServiceOne() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        startServiceTwo();
        //START_REDELIVER_INTENT，START_NOT_STICKY和START_STICKY区别不是太明白
        //如果这个Service被系统Kill了或者app被Kill了，Service还能自动重启。
        return START_REDELIVER_INTENT;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "服务创建了");
       startServiceTwo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "服务销毁了");
        startServiceTwo();
    }

    private void startServiceTwo() {
        boolean b = ServiceMangerUtils.isServiceWorked(MyServiceOne.this, "myservice2");
        if(!b) {
            Intent service = new Intent(MyServiceOne.this, MyServiceTwo.class);
            startService(service);
            Log.e(TAG, "Start ServiceTwo");
        }
    }
}
