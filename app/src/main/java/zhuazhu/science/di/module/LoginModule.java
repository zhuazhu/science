package zhuazhu.science.di.module;

import javax.inject.Scope;

import dagger.Module;
import dagger.Provides;
import mejust.frame.di.module.BaseActivityModule;
import mejust.frame.di.scope.ActivityScope;
import zhuazhu.science.mvp.login.LoginContract;
import zhuazhu.science.mvp.login.model.LoginModel;
import zhuazhu.science.mvp.login.presenter.LoginPresenter;

/**
 * @author zhuazhu
 **/
@Module
public class LoginModule extends BaseActivityModule<LoginContract.View> {
    public LoginModule(LoginContract.View view) {
        super(view);
    }
    @ActivityScope
    @Provides
    public LoginContract.Presenter providesLoginPresenter(LoginContract.View view, LoginModel loginModel){
        return new LoginPresenter(view,loginModel);
    }
}
