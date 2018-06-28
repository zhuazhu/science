package zhuazhu.science.mvp.article.model;

import javax.inject.Inject;

import io.reactivex.Observable;
import mejust.frame.FrameManager;
import mejust.frame.net.NetManager;
import zhuazhu.science.data.net.ApiService;
import zhuazhu.science.mvp.article.ArticleContract;
import zhuazhu.science.mvp.index.model.ArticleList;

/**
 * @author zhuazhu
 **/
public class ArtitcleModel implements ArticleContract.Model {
    private final ApiService mApiService;
    private final NetManager mNetManager;
    @Inject
    public ArtitcleModel(ApiService apiService) {
        mApiService = apiService;
        mNetManager = FrameManager.netManager();
    }

    @Override
    public Observable<ArticleList> getArticles(int cid, int pageIndex) {
        return mApiService.getArticleData(pageIndex,cid).compose(mNetManager.transformerHttp());
    }
}
