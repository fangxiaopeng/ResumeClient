package fxp.com.resumeclient.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import fxp.com.resumeclient.BaseApplication;
import fxp.com.resumeclient.R;

public abstract class BaseActivity extends AppCompatActivity {

    public static boolean isOpenDistanceBack = false;

    private boolean isExit = false;

    //手指上下滑动的最大速度
    private static final int YSPEED_MAX = 1000;

    // 手指向右移动的最小距离
    private static final int XDISTANCE_MIN = 100;

    // 手指向上或向下滑动的最大距离
    private static final int YDISTANCE_MAX = 100;

    // 手指按下时候的x/y坐标，移动时的x/y坐标
    private float xDown, yDown, xMove, yMove;

    private VelocityTracker mVelocityTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseApplication.addActivity(this);

        // 初始化组件
        initViews();

        // 初始化数据
        initDatas();

        // 初始化事件监听
        initListeners();
    }


    /**
     * <初始化activity中包含的组件>
     */
    public abstract void initViews();

    /**
     * 初始化组件的事件监听
     */
    public abstract void initListeners();

    /**
     * 初始化数据源
     */
    public abstract void initDatas();

	/*
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			exitBy2Click();
		}
		return false;
	}

	*//**
     * 双击返回键退出应用程序
     *//*
    private void exitBy2Click() {
		Timer tExit = null;
		if(isExit == false){
			isExit = true;	//准备退出
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

//			设置定时器，如果2s内没有继续点击返回，取消退出准备
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出
				}
			}, 2000);
		}else {
//			finish();
			MyApplication.exitApp();

			System.exit(0);
		}
	}
*/

    /**
     * 界面滑动事件响应处理
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        createVelocityTracker(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = ev.getRawX();
                yDown = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = ev.getRawX();
                yMove = ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                //计算滑动的距离
                int distanceX = (int) (xMove - xDown);
                int distanceY = (int) (yMove - yDown);

                int ySpeed = getScrollVelocity();
                // 关闭Activity需满足以下条件：
                // 1. x轴滑动的距离大于 XDISTANCE_MIN（设定的x轴最小移动距离）
                // 2. y轴滑动的距离在 YDISTANCE_MAX（设定的y轴最大移动距离）
                // 3. y轴上滑动速度小于设定的YSPEED_MAX(设定的最大滑动速度)，否则认为是上下滑动而不是右滑结束activity
                // 4. 子activity中设置isOpenDistanceBack为true即打开状态，才能进行滑动返回
                if (distanceX > XDISTANCE_MIN && ySpeed < YSPEED_MAX
                        && (distanceY < YDISTANCE_MAX && distanceY > -YDISTANCE_MAX) && isOpenDistanceBack) {
                    finish();
                    //方法必须在startActivity()或者finish()之后执行，才有效果
                    overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                }

                recycleVelocityTracker();
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 创建VelocityTracker对象，并将触摸界面的滑动事件加入到VelocityTracker中
     *
     * @param ev
     */
    private void createVelocityTracker(MotionEvent ev) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(ev);
    }

    /**
     * 计算每秒钟y方向滑动的距离
     *
     * @return
     */
    private int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) mVelocityTracker.getYVelocity();
        return Math.abs(velocity);
    }

    /**
     * 回收VelocityTracker对象
     */
    private void recycleVelocityTracker() {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    /**
     * 设置屏幕只能竖屏
     * @param activity
     */
    public void setActivityState(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}
