package zhuazhu.science.di.module;

import dagger.Module;
import mejust.frame.di.module.BaseActivityModule;
import zhuazhu.science.mvp.login.LoginContract;

/**
 * @author zhuazhu
 **/
@Module
public class LoginModule extends BaseActivityModule<LoginContract.View> {
    public LoginModule(LoginContract.View view) {
        super(view);
    }
}
