package liuxiaozhu.com.appkeepalive.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import liuxiaozhu.com.appkeepalive.MainActivity;
import liuxiaozhu.com.appkeepalive.ServiceMangerUtils;

public class MyServiceTwo extends Service {
    public final static String TAG = "myservice2";

    public MyServiceTwo() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        startServiceOne();
        //如果这个Service被系统Kill了或者app被Kill了，Service还能自动重启。
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "服务创建了");
        startServiceOne();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "服务销毁了");
        startServiceOne();

    }

    /**
     * 检查服务是否存在，不存在唤起
     */
    private void startServiceOne() {
        boolean b = ServiceMangerUtils.isServiceWorked(MyServiceTwo.this, "myservice1");
        if (!b) {
            Intent service = new Intent(MyServiceTwo.this, MyServiceOne.class);
            startService(service);
            Log.e(TAG, "start ServiceOne");
        }
    }
}
