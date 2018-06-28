package zhuazhu.science.mvp.childrencategory;

import android.support.v4.app.FragmentManager;

import java.util.List;

import io.reactivex.Observable;
import mejust.frame.mvp.BaseContract;
import zhuazhu.science.data.db.model.Category;

/**
 * @author zhuazhu
 **/
public interface ChildrenCategoryContract {
    interface View extends BaseContract.View {
        /**
         * 初始化tabs
         * @param categories
         */
        void initTabs(List<Category> categories);

        /**
         * 获取fragment的管理器
         * @return
         */
        FragmentManager getManager();
    }

    interface Presenter extends BaseContract.Presenter {
        /**
         * 获取二级分类列表
         * @param parentChapterId 一级分类的id
         */
        void getCategories(int parentChapterId);
    }

    interface Model {
        /**
         * 获取二级分类列表
         * @param parentChapterId 一级分类的id
         * @return
         */
        Observable getCategories(int parentChapterId);
    }
}