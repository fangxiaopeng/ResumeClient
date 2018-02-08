package fxp.com.resumeclient;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.view.WindowManager;

/**
 * Created by fxp on 2018/2/8.
 */

public class BaseApplication extends Application {

    private static BaseApplication myApplication;

    //	activity任务栈管理
    private static List<Activity> activityList = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    /**
     * 获取应用程序的application对象
     */
    public static BaseApplication getMyApplication() {
        return myApplication;
    }

    /**
     * 在activity活动栈添加activity对象，退出程序时候全部finish
     */
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    /**
     * 彻底退出程序，结束每个activity
     */
    public static void exitApp(){
        try {
            for (Activity activity : activityList) {
                if(activity != null){
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    /**
     * 获取应用的versionCode
     *
     * @return
     */
    public int getApplicationVersionCode(){
        try {
            return this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return  0;
    }

    /**
     * 获取应用的versionName
     *
     * @return
     */
    public String getApplicationVersionName(){
        try {
            return this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 获取屏幕的宽度
     *
     * @return
     */
    public int getDisplayWidth(){
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕的高度
     *
     * @return
     */
    public int getDisplayHeight(){
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * 判断应用是否在前台
     *
     * @return boolean
     */
    public boolean isAppForeRunning() {
        boolean isAppRunning = false;
        ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = am.getRunningTasks(1);
        if (runningTasks.size() > 0) {
            //应用程序位于堆栈的顶层
            if (this.getPackageName().equals(runningTasks.get(0).topActivity.getPackageName())) {
                isAppRunning = true;
            }
        }

        return isAppRunning;
    }

    /**
     * 判断应用是否在后台运行
     *
     * @return boolean
     */
    public boolean isAppBackRunning() {
        boolean isAppRunning = false;
        ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = am.getRunningTasks(100);
        int length = runningTasks.size();
        for (int i = 1; i < length; i++) {
            if (runningTasks.get(i).baseActivity.getPackageName().equals(this.getPackageName())) {
                isAppRunning = true;
                break;
            }
        }

        return isAppRunning;
    }

    /**
     * 判断应用进程是否存活
     *
     * @return boolean
     */
    public boolean isAppSurvive() {
        boolean isAppRunning = false;
        ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
            if (processInfo.processName.equals(this.getPackageName())) {
                isAppRunning = true;
                break;
            }
        }

        return isAppRunning;
    }

    /**
     * 对网络连接状态进行判断
     *
     * @return true 可用 false 不可用
     */
    public boolean isOpenNetwork()
    {
        ConnectivityManager connManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null)
        {
            return connManager.getActiveNetworkInfo().isAvailable();
        }

        return false;
    }
}
