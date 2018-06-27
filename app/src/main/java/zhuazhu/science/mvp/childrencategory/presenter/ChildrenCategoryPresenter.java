package zhuazhu.science.mvp.childrencategory.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import mejust.frame.mvp.presenter.BasePresenter;
import mejust.frame.net.HttpObserver;
import zhuazhu.science.data.db.model.Category;
import zhuazhu.science.mvp.childrencategory.ChildrenCategoryContract;
import zhuazhu.science.mvp.childrencategory.model.ChildrenCategoryModel;

/**
 * @author zhuazhu
 **/
public class ChildrenCategoryPresenter extends BasePresenter<ChildrenCategoryContract.View> implements ChildrenCategoryContract.Presenter {
    private final ChildrenCategoryContract.Model mModel;
    public ChildrenCategoryPresenter(@NonNull ChildrenCategoryContract.View view, ChildrenCategoryModel childrenCategoryModel) {
        super(view);
        mModel = childrenCategoryModel;
    }

    @Override
    public void getCategories(int parentChapterId) {
        execute(mModel.getCategories(parentChapterId), new HttpObserver<List<Category>>() {
            @Override
            public void onNext(List<Category> categories) {
                mView.initTabs(categories);
            }
        });
    }
}
