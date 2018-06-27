package zhuazhu.science.di.component;

import dagger.Subcomponent;
import mejust.frame.di.scope.ActivityScope;
import zhuazhu.science.di.module.ChildrenCategoryModule;
import zhuazhu.science.mvp.childrencategory.view.ChildrenCategoryActivity;

/**
 * @author zhuazhu
 **/
@ActivityScope
@Subcomponent(modules = ChildrenCategoryModule.class)
public interface ChildrenCategoryComponent {
    void inject(ChildrenCategoryActivity childrenCategoryActivity);
}
