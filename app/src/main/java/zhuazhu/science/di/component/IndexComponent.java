package zhuazhu.science.di.component;

import dagger.Subcomponent;
import mejust.frame.di.scope.FragmentScope;
import zhuazhu.science.di.module.IndexModule;
import zhuazhu.science.mvp.index.view.IndexFragment;

/**
 * @author zhuazhu
 **/
@FragmentScope
@Subcomponent(modules = IndexModule.class)
public interface IndexComponent {
    void inject(IndexFragment indexFragment);
}
