package zhuazhu.science.mvp.article.presenter;

import android.support.annotation.NonNull;

import mejust.frame.mvp.presenter.BasePresenter;
import mejust.frame.net.HttpObserver;
import zhuazhu.science.mvp.article.ArticleContract;
import zhuazhu.science.mvp.article.model.ArtitcleModel;
import zhuazhu.science.mvp.index.model.ArticleList;

/**
 * @author zhuazhu
 **/
public class ArticlePresenter extends BasePresenter<ArticleContract.View> implements ArticleContract.Presenter {
    private final ArticleContract.Model mModel;
    public ArticlePresenter(@NonNull ArticleContract.View view, ArtitcleModel artitcleModel) {
        super(view);
        mModel = artitcleModel;
    }

    @Override
    public void loadData(int cid, int pageIndex) {
        execute(mModel.getArticles(cid, pageIndex - 1), new HttpObserver<ArticleList>() {
            @Override
            public void onNext(ArticleList articleList) {
                mView.setAdapterData(articleList.getDatas());
            }
        });
    }
}
