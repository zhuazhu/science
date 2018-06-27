package zhuazhu.science.mvp.category.model;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import mejust.frame.FrameManager;
import mejust.frame.common.log.Logger;
import mejust.frame.net.NetManager;
import zhuazhu.science.data.db.model.Category;
import zhuazhu.science.data.db.model.Category_Table;
import zhuazhu.science.data.net.ApiService;
import zhuazhu.science.mvp.category.CategoryContract;

/**
 * @author zhuazhu
 **/
public class CategoryModel implements CategoryContract.Model {
    private static final String TAG = "CategoryModel";
    private final ApiService mApiService;
    private final NetManager mNetManager;
    private final DatabaseDefinition mDatabaseDefinition;
    @Inject
    public CategoryModel(ApiService apiService,DatabaseDefinition databaseDefinition) {
        mApiService = apiService;
        mNetManager = FrameManager.netManager();
        mDatabaseDefinition = databaseDefinition;
    }

    @Override
    public Observable<List<Category>> getCategorys() {
        return mApiService.getCategorys().compose(mNetManager.transformerHttp()).map(categories -> {
            saveCategoryToDb(categories);
            return categories;
        });
    }

    @Override
    public void saveCategoryToDb(List<Category> categories) {
        mDatabaseDefinition.beginTransactionAsync(databaseWrapper -> {
            for (Category category : categories) {
                category.save(databaseWrapper);
                for (Category category1 : category.getChildren()) {
                    category1.save(databaseWrapper);
                }
            }
        }).success(transaction -> {
            Logger.i(TAG,"事物-分类保存成功");
        }).error((transaction, error) -> {
            Logger.e(TAG,"事物-分类保存失败");
        }).build().execute();
    }

    @Override
    public List<Category> queryCategoriesToDb() {
        return SQLite.select().from(Category.class).where(Category_Table.parentChapterId.eq(0)).queryList();
    }
}
