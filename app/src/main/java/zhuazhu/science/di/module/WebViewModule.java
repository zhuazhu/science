package zhuazhu.science.di.module;

import dagger.Module;
import dagger.Provides;
import mejust.frame.di.module.BaseActivityModule;
import mejust.frame.di.scope.ActivityScope;
import zhuazhu.science.mvp.web.WebViewContract;
import zhuazhu.science.mvp.web.model.WebViewModel;
import zhuazhu.science.mvp.web.presenter.WebViewPresenter;

/**
 * @author zhuazhu
 **/
@Module
public class WebViewModule extends BaseActivityModule<WebViewContract.View> {
    public WebViewModule(WebViewContract.View view) {
        super(view);
    }
    @ActivityScope
    @Provides
    public WebViewContract.Presenter providesWebViewPresenter(WebViewContract.View view, WebViewModel webViewModel){
        return new WebViewPresenter(view,webViewModel);
    }
}
