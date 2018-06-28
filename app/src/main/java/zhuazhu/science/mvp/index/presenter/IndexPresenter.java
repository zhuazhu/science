package zhuazhu.science.mvp.index.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import mejust.frame.FrameManager;
import mejust.frame.mvp.presenter.BasePresenter;
import mejust.frame.net.HttpObserver;
import zhuazhu.science.mvp.index.IndexContract;
import zhuazhu.science.mvp.index.model.ArticleList;
import zhuazhu.science.mvp.index.model.BannerBean;
import zhuazhu.science.mvp.index.model.IndexModel;

/**
 * @author zhuazhu
 **/
public class IndexPresenter extends BasePresenter<IndexContract.View> implements IndexContract.Presenter {
    private final IndexContract.Model mModel;
    public IndexPresenter(@NonNull IndexContract.View view,IndexModel indexModel) {
        super(view);
        mModel = indexModel;
    }

    @Override
    public void loadBanner() {
        execute(mModel.getBanners().compose(FrameManager.netManager().handleLoadView(mView.getHandler())),new HttpObserver<List<BannerBean>>() {
            @Override
            public void onNext(List<BannerBean> bannerBeans) {
                mView.setBanners(bannerBeans);
            }
        });
    }

    @Override
    public void loadData(int pageIndex) {
        execute(mModel.getIndexArticleData(pageIndex-1), new HttpObserver<ArticleList>() {
            @Override
            public void onNext(ArticleList articleList) {
                mView.setAdapterData(articleList.getDatas());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.finishRefresh();
            }
        });
    }
}
