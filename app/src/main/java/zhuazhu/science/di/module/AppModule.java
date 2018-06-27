package zhuazhu.science.di.module;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mejust.frame.di.component.FrameComponent;
import zhuazhu.science.data.db.ScienceDataBase;
import zhuazhu.science.data.net.ApiService;

/**
 * @author zhuazhu
 **/
@Module
public class AppModule {
    @Singleton
    @Provides
    public ApiService providesApiService(FrameComponent frameComponent) {
        return frameComponent.netManager().getApi(ApiService.class);
    }
    @Singleton
    @Provides
    public DatabaseDefinition providesDatabaseDefinition(){
        return FlowManager.getDatabase(ScienceDataBase.class);
    }
}
