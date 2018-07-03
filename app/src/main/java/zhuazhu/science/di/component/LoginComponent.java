package zhuazhu.science.di.component;

import dagger.Subcomponent;
import zhuazhu.science.di.module.LoginModule;
import zhuazhu.science.mvp.login.view.LoginActivity;

/**
 * @author zhuazhu
 **/
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
