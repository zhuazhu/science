package zhuazhu.science.di.component;

import dagger.Subcomponent;
import mejust.frame.di.scope.FragmentScope;
import zhuazhu.science.di.module.ArticleModule;
import zhuazhu.science.mvp.article.view.ArticleFragment;

/**
 * @author zhuazhu
 **/
@FragmentScope
@Subcomponent(modules = ArticleModule.class)
public interface ArticleComponent {
    void inject(ArticleFragment articleFragment);
}
