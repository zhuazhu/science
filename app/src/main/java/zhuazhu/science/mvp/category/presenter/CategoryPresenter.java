package zhuazhu.science.mvp.category.presenter;

import android.support.annotation.NonNull;

import org.reactivestreams.Publisher;

import java.util.List;

import conm.zhuazhu.common.utils.ListUtils;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import mejust.frame.mvp.presenter.BasePresenter;
import mejust.frame.net.HttpObserver;
import zhuazhu.science.data.db.model.Category;
import zhuazhu.science.mvp.category.CategoryContract;
import zhuazhu.science.mvp.category.model.CategoryModel;

/**
 * @author zhuazhu
 **/
public class CategoryPresenter extends BasePresenter<CategoryContract.View> implements CategoryContract.Presenter {
    private CategoryContract.Model mModel;
    public CategoryPresenter(@NonNull CategoryContract.View view, CategoryModel categoryModel) {
        super(view);
        mModel = categoryModel;
    }

    @Override
    public void getCategorys() {
        Observable<List<Category>> observable =Observable.just(0).observeOn(AndroidSchedulers.mainThread()).flatMap((Function<Integer, ObservableSource<List<Category>>>) integer -> {
            List<Category> categories = mModel.queryCategoriesToDb();
            if(ListUtils.isNotEmpty(categories)){
                mView.setAdapterData(categories);
            }
            return mModel.getCategorys();
        });
        execute(observable, new HttpObserver<List<Category>>() {
            @Override
            public void onNext(List<Category> parentCategories) {
                mView.setAdapterData(parentCategories);
            }
        });
    }
}
