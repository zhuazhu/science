package zhuazhu.science.mvp.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.OnClick;
import mejust.frame.mvp.view.BaseActivity;
import mejust.frame.mvp.view.BasePresenterActivity;
import zhuazhu.science.R;
import zhuazhu.science.app.ScienceApp;
import zhuazhu.science.di.module.LoginModule;
import zhuazhu.science.mvp.login.LoginContract;

public class LoginActivity extends BasePresenterActivity<LoginContract.Presenter> implements LoginContract.View {
    public static void start(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    protected void initParam() {
        mImmersionBar.statusBarColor(R.color.c_fff).statusBarDarkFont(true).init();
    }

    @Override
    protected void injectComponent() {
        ScienceApp.appComponent().loginComponent(new LoginModule(this)).inject(this);
    }

    @Override
    protected void initView() {

    }
}
