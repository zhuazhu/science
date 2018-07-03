package zhuazhu.science.mvp.login.model;

import mejust.frame.FrameManager;
import mejust.frame.net.NetManager;
import zhuazhu.science.data.net.ApiService;
import zhuazhu.science.mvp.login.LoginContract;

/**
 * @author zhuazhu
 **/
public class LoginModel implements LoginContract.Model {
    private ApiService mApiService;
    private NetManager mNetManager;

    public LoginModel(ApiService apiService) {
        mApiService = apiService;
        mNetManager = FrameManager.netManager();
    }

}
