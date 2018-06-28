package zhuazhu.science.mvp.web.model;

import javax.inject.Inject;

import mejust.frame.FrameManager;
import mejust.frame.net.NetManager;
import zhuazhu.science.data.net.ApiService;
import zhuazhu.science.mvp.web.WebViewContract;

/**
 * @author zhuazhu
 **/
public class WebViewModel implements WebViewContract.Model {
    private final ApiService mApiService;
    private final NetManager mNetManager;
    @Inject
    public WebViewModel(ApiService apiService) {
        mApiService = apiService;
        mNetManager = FrameManager.netManager();
    }
}
