package zhuazhu.science.di.component;

import dagger.Subcomponent;
import mejust.frame.di.scope.FragmentScope;
import zhuazhu.science.di.module.CategoryModule;
import zhuazhu.science.mvp.category.view.CategoryFragment;

/**
 * @author zhuazhu
 **/
@FragmentScope
@Subcomponent(modules = CategoryModule.class)
public interface CategoryComponent {
    void inject(CategoryFragment categoryFragment);
}
