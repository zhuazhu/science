package zhuazhu.science.mvp.index.model;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mejust.frame.FrameManager;
import mejust.frame.net.NetManager;
import zhuazhu.science.data.net.ApiService;
import zhuazhu.science.mvp.index.IndexContract;

/**
 * @author zhuazhu
 **/
public class IndexModel implements IndexContract.Model {
    private final ApiService mApiService;
    private final NetManager mNetManager;
    @Inject
    public IndexModel(ApiService apiService) {
        mApiService = apiService;
        mNetManager = FrameManager.netManager();
    }

    @Override
    public Observable<List<BannerBean>> getBanners() {
        return mApiService.getBanner().compose(mNetManager.transformerHttp());
    }

    @Override
    public Observable<ArticleList> getIndexArticleData(int page) {
        return mApiService.getIndexArticleData(page).compose(mNetManager.transformerHttp());
    }
}
