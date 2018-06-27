package zhuazhu.science.di.module;

import dagger.Module;
import dagger.Provides;
import mejust.frame.di.module.BaseFragmentModule;
import mejust.frame.di.scope.FragmentScope;
import zhuazhu.science.mvp.category.CategoryContract;
import zhuazhu.science.mvp.category.adapter.CategoryAdapter;
import zhuazhu.science.mvp.category.model.CategoryModel;
import zhuazhu.science.mvp.category.presenter.CategoryPresenter;

/**
 * @author zhuazhu
 **/
@Module
public class CategoryModule extends BaseFragmentModule<CategoryContract.View> {
    public CategoryModule(CategoryContract.View view) {
        super(view);
    }
    @FragmentScope
    @Provides
    public CategoryContract.Presenter providesCategoryPresenter(CategoryContract.View view, CategoryModel categoryModel){
        return new CategoryPresenter(view,categoryModel);
    }
    @FragmentScope
    @Provides
    public CategoryAdapter providesCategoryAdapter(){
        return new CategoryAdapter();
    }
}
