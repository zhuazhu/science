package zhuazhu.science.mvp.login.presenter;

import android.support.annotation.NonNull;

import mejust.frame.mvp.presenter.BasePresenter;
import zhuazhu.science.di.module.LoginModule;
import zhuazhu.science.mvp.login.LoginContract;
import zhuazhu.science.mvp.login.model.LoginModel;

/**
 * @author zhuazhu
 **/
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    private final LoginContract.Model mModel;
    public LoginPresenter(@NonNull LoginContract.View view, LoginModel loginModel) {
        super(view);
        mModel = loginModel;
    }

}
