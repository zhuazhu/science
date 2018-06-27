package zhuazhu.science.di.module;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import mejust.frame.di.module.BaseActivityModule;
import mejust.frame.di.scope.ActivityScope;
import zhuazhu.science.mvp.childrencategory.ChildrenCategoryContract;
import zhuazhu.science.mvp.childrencategory.model.ChildrenCategoryModel;
import zhuazhu.science.mvp.childrencategory.presenter.ChildrenCategoryPresenter;

/**
 * @author zhuazhu
 **/
@Module
public class ChildrenCategoryModule extends BaseActivityModule<ChildrenCategoryContract.View> {
    public ChildrenCategoryModule(ChildrenCategoryContract.View view) {
        super(view);
    }
    @ActivityScope
    @Provides
    public ChildrenCategoryContract.Presenter providesChildrenCategoryPresenter(ChildrenCategoryContract.View view, ChildrenCategoryModel childrenCategoryModel){
        return new ChildrenCategoryPresenter(view, childrenCategoryModel);
    }
}
