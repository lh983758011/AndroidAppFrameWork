package com.zenglb.baselib.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.zenglb.commonlib.R;

/**
 * 基类就只做基类的事情,不要把业务层面的代码写到这里来 ！！
 * 1.toolbar 的封装
 * 2.页面之间的跳转
 * 3.注意WebViewActivity 开启了多进程！
 * @author zenglb 20170301
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = BaseActivity.class.getSimpleName();
    private Toolbar mToolbar;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        mContext=BaseActivity.this;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        initViews();
    }


    /**
     * 检查网络
     *
     */
    private void getNetState(){
//        //用于网络监测
//        if(!NetUtils.isConnected(BaseActivity.this)){
//            AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
//            builder.setIcon(R.mipmap.ic_launcher);
//            builder.setTitle("提示");
//            builder.setMessage("当前没有可用网络，是否进入网络设置界面");
//            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                    NetUtils.openSetting(BaseActivity.this);
//                }
//            });
//            builder.setPositiveButton("取消",null);
//            builder.create().show();
//        }
    }


    protected abstract int setLayoutId();
    protected abstract void initViews();

    public void onClick(View view){}  //不是必须的


    /*
	 * Activity的跳转
	 */
    public void setIntentClass(Class<?> cla) {
        Intent intent = new Intent();
        intent.setClass(this, cla);
        startActivity(intent);
//        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

//    /*
//     * Activity的跳转-带参数
//     */
//    public void setIntentClass(Class<?> cla, Object obj) {
//        Intent intent = new Intent();
//        intent.setClass(this, cla);
//        intent.putExtra(INTENTTAG, (Serializable) obj);
//        startActivity(intent);
//        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
//    }

    /**
     * Activity -> webview Activity的跳转-带参数
     *
     * @param link
     */
    public void goWebView(String link) {
        Intent intent = new Intent();
        intent.setAction(BaseWebViewActivity.WEB_ACTION);
        intent.addCategory(BaseWebViewActivity.WEB_CATEGORY);
        intent.putExtra(BaseWebViewActivity.URL, link);
        startActivity(intent);
    }

    /**
     * Activity -> webview Activity的跳转-带参数
     *
     * @param
     * @param title
     * @param link
     */
    public void goWebView(String link,String title) {
        Intent intent = new Intent();
        intent.setAction(BaseWebViewActivity.WEB_ACTION);
        intent.addCategory(BaseWebViewActivity.WEB_CATEGORY);
        intent.putExtra(BaseWebViewActivity.TITLE, title);
        intent.putExtra(BaseWebViewActivity.URL, link);
        startActivity(intent);
//        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }


    /**
     * Get toolbar
     *
     * @return support.v7.widget.Toolbar.
     */
    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }


    /**
     * 设置头部标题
     *
     * @param title
     */
    public void setToolBarTitle(CharSequence title) {
        getToolbar().setTitle(title);
        setSupportActionBar(getToolbar());
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack() {
        getToolbar().setNavigationIcon(R.drawable.ic_back_copy);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); //返回事件
            }
        });
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     *
     * @return
     */
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (null != getToolbar() && isShowBacking()) {
            showBack();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
