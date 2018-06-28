package zhuazhu.science.di.component;

import dagger.Subcomponent;
import mejust.frame.di.scope.ActivityScope;
import zhuazhu.science.di.module.WebViewModule;
import zhuazhu.science.mvp.web.view.WebViewActivity;

/**
 * @author zhuazhu
 **/
@ActivityScope
@Subcomponent(modules = WebViewModule.class)
public interface WebViewComponent {
    void inject(WebViewActivity webViewActivity);
}
