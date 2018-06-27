package zhuazhu.science.mvp.childrencategory.model;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import mejust.frame.FrameManager;
import mejust.frame.net.NetManager;
import zhuazhu.science.data.db.model.Category;
import zhuazhu.science.data.db.model.Category_Table;
import zhuazhu.science.data.net.ApiService;
import zhuazhu.science.mvp.childrencategory.ChildrenCategoryContract;

/**
 * @author zhuazhu
 **/
public class ChildrenCategoryModel implements ChildrenCategoryContract.Model {
    private final ApiService mApiService;
    private final NetManager mNetManager;
    @Inject
    public ChildrenCategoryModel(ApiService apiService) {
        mApiService = apiService;
        mNetManager  = FrameManager.netManager();
    }

    @Override
    public Observable<List<Category>> getCategories(int parentChapterId) {
        return Observable.just(parentChapterId).subscribeOn(Schedulers.newThread()).flatMap((Function<Integer, ObservableSource<List<Category>>>) integer -> {
            List<Category> categories = SQLite.select().from(Category.class).where(Category_Table.parentChapterId.eq(integer)).queryList();
            return Observable.just(categories);
        }).observeOn(AndroidSchedulers.mainThread());
    }
}
