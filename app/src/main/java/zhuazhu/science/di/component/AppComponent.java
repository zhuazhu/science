package zhuazhu.science.di.component;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import mejust.frame.di.component.FrameComponent;
import zhuazhu.science.di.module.AppModule;
import zhuazhu.science.di.module.ArticleModule;
import zhuazhu.science.di.module.CategoryModule;
import zhuazhu.science.di.module.ChildrenCategoryModule;
import zhuazhu.science.di.module.IndexModule;
import zhuazhu.science.di.module.WebViewModule;

/**
 * @author zhuazhu
 **/
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    IndexComponent indexComponent(IndexModule indexModule);

    CategoryComponent categoryComponent(CategoryModule categoryModule);

    ChildrenCategoryComponent childrenCategoryComponent(ChildrenCategoryModule childrenCategoryModule);

    ArticleComponent articleComponent(ArticleModule articleModule);

    WebViewComponent webViewComponent(WebViewModule webViewModule);
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder frameComponent(FrameComponent frameComponent);
        AppComponent build();
    }
}
