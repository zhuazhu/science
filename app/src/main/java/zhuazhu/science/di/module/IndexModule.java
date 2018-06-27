package zhuazhu.science.di.module;

import dagger.Module;
import dagger.Provides;
import mejust.frame.di.module.BaseFragmentModule;
import mejust.frame.di.scope.FragmentScope;
import mejust.frame.widget.refresh.IPageControl;
import zhuazhu.science.mvp.index.IndexContract;
import zhuazhu.science.mvp.index.adapter.BannerCreator;
import zhuazhu.science.mvp.index.adapter.IndexAdapter;
import zhuazhu.science.mvp.index.model.IndexModel;
import zhuazhu.science.mvp.index.presenter.IndexPresenter;

/**
 * @author zhuazhu
 **/
@Module
public class IndexModule extends BaseFragmentModule<IndexContract.View> {
    private IPageControl mIPageControl;
    public IndexModule(IndexContract.View view,IPageControl iPageControl) {
        super(view);
        mIPageControl = iPageControl;
    }
    @FragmentScope
    @Provides
    public IndexContract.Presenter providesIndexPresenter(IndexContract.View view,IndexModel indexModel){
        return new IndexPresenter(view,indexModel);
    }
    @FragmentScope
    @Provides
    public IndexAdapter providesIndexAdapter(){
        return new IndexAdapter(mIPageControl);
    }
    @FragmentScope
    @Provides
    public BannerCreator providesBannerCreator(){
        return new BannerCreator();
    }
}
