package zhuazhu.science.di.component;

import dagger.Subcomponent;
import mejust.frame.di.scope.ActivityScope;
import zhuazhu.science.di.module.LoginModule;
import zhuazhu.science.mvp.login.view.LoginActivity;

/**
 * @author zhuazhu
 **/
@ActivityScope
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
