package zhuazhu.science.mvp.web.presenter;

import android.support.annotation.NonNull;

import mejust.frame.mvp.presenter.BasePresenter;
import zhuazhu.science.mvp.web.WebViewContract;
import zhuazhu.science.mvp.web.model.WebViewModel;

/**
 * @author zhuazhu
 **/
public class WebViewPresenter extends BasePresenter<WebViewContract.View> implements WebViewContract.Presenter {
    private final WebViewContract.Model mModel;
    public WebViewPresenter(@NonNull WebViewContract.View view, WebViewModel webViewModel) {
        super(view);
        mModel = webViewModel;
    }
}
