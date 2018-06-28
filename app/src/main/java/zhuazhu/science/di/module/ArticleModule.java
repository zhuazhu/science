package zhuazhu.science.di.module;

import dagger.Module;
import dagger.Provides;
import mejust.frame.di.module.BaseFragmentModule;
import mejust.frame.di.scope.FragmentScope;
import mejust.frame.widget.refresh.IPageControl;
import zhuazhu.science.mvp.article.ArticleContract;
import zhuazhu.science.mvp.article.model.ArtitcleModel;
import zhuazhu.science.mvp.article.presenter.ArticlePresenter;
import zhuazhu.science.mvp.index.adapter.ArtitcleAdapter;

/**
 * @author zhuazhu
 **/
@Module
public class ArticleModule extends BaseFragmentModule<ArticleContract.View> {
    private final IPageControl mIPageControl;
    public ArticleModule(ArticleContract.View view,IPageControl iPageControl) {
        super(view);
        mIPageControl = iPageControl;
    }
    @FragmentScope
    @Provides
    public ArticleContract.Presenter providesArticlePresenter(ArticleContract.View view, ArtitcleModel artitcleModel){
        return new ArticlePresenter(view,artitcleModel);
    }
    @FragmentScope
    @Provides
    public ArtitcleAdapter providesArtitcleAdapter(){
        return new ArtitcleAdapter(mIPageControl);
    }
}
