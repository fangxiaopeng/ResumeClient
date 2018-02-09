package fxp.com.resumeclient.activity;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import fxp.com.resumeclient.R;
import fxp.com.resumeclient.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Context context;

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    private FloatingActionButton floatingActionBtn;

    @Override
    public void initViews() {

        setContentView(R.layout.activity_main);

        findViews();

        // 显示actionBar
        setSupportActionBar(toolbar);

        initNavigationView();
    }

    @Override
    public void initDatas() {
        context = getApplicationContext();
    }

    @Override
    public void initListeners() {
        floatingActionBtn.setOnClickListener(this);

    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        floatingActionBtn = (FloatingActionButton) findViewById(R.id.fab);
    }

    /**
     * 初始化侧边栏
     */
    private void initNavigationView() {
        // 左上角打开左侧栏按钮
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        // 动态设置左侧栏头部(不会覆盖xml布局中app:headerLayout设置，会都显示)
//        navigationView.inflateHeaderView(R.layout.nav_header_main);
        // 动态设置左侧栏导航列表(不会覆盖xml布局中app:menu设置，会都显示)
//        navigationView.inflateMenu(R.menu.activity_main_drawer);
        onNavgationViewMenuItemSelected(navigationView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            default:
                break;
        }
    }

    /**
     * NavigationView侧栏menu列表的item点击事件
     *
     * @param mNav
     */
    private void onNavgationViewMenuItemSelected(NavigationView mNav) {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_camera:
                        // Handle the camera action
                        break;
                    case R.id.nav_gallery:
                        // Handle the nav_gallery action
                        break;
                    case R.id.nav_slideshow:
                        // Handle the nav_slideshow action
                        break;
                    case R.id.nav_manage:
                        // Handle the nav_manage action
                        break;
                    case R.id.nav_share:
                        // Handle the nav_share action
                        break;
                    case R.id.nav_send:
                        // Handle the nav_send action
                        break;
                    default:
                        break;
                }

                // Menu item点击后选中
                menuItem.setChecked(true);
                // 关闭Drawerlayout
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
