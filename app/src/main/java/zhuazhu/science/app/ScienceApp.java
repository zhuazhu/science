package zhuazhu.science.app;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;

import mejust.frame.FrameManager;
import mejust.frame.common.image.ImageConfig;
import mejust.frame.data.FrameConfig;
import mejust.frame.di.component.FrameComponent;
import mejust.frame.net.config.NetConfig;
import mejust.frame.widget.title.TitleBarConfig;
import zhuazhu.science.BuildConfig;
import zhuazhu.science.R;
import zhuazhu.science.di.component.AppComponent;
import zhuazhu.science.di.component.DaggerAppComponent;

/**
 * @author zhuazhu
 **/
public class ScienceApp extends Application {
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            layout.setEnableHeaderTranslationContent(false);
            return new MaterialHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            return new FalsifyFooter(context);
        });
    }
    @Override
    public void onCreate() {
        super.onCreate();
        initFrameConfig();
        FlowManager.init(this);
    }
    private void initFrameConfig(){
        ImageConfig imageConfig = new ImageConfig();
        NetConfig netConfig = new NetConfig();
        netConfig.setHttpLogTag("Science");
        netConfig.setResponseCodeSuccess("0");
        FrameConfig frameConfig = new FrameConfig();
        frameConfig.setDebug(BuildConfig.DEBUG);
        frameConfig.setOpenNetworkState(true);
        TitleBarConfig titleBarConfig = new TitleBarConfig();
        titleBarConfig.setBackDrawable(R.drawable.ic_back);
        titleBarConfig.setBackgroundColor(R.color.c_2395ff);
        titleBarConfig.setTitleTextColor(R.color.c_fff);
        titleBarConfig.setTitleTextSize(18);
        titleBarConfig.setMenuTextSize(15);
        sFrameComponent = FrameManager.init(this,imageConfig,netConfig,frameConfig,titleBarConfig);
    }
    private static FrameComponent sFrameComponent;
    private static AppComponent sAppComponent;
    public static AppComponent appComponent(){
        if (sAppComponent==null) {
            sAppComponent = DaggerAppComponent.builder().frameComponent(sFrameComponent).build();
        }
        return sAppComponent;
    }
}
