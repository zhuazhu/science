package zhuazhu.science.mvp.category;

import java.util.List;

import io.reactivex.Observable;
import mejust.frame.mvp.BaseContract;
import zhuazhu.science.data.db.model.Category;

/**
 * @author zhuazhu
 **/
public interface CategoryContract {
    interface View extends BaseContract.View {
        /**
         * 设置列表数据
         * @param categories
         */
        void setAdapterData(List<Category> categories);
    }

    interface Presenter extends BaseContract.Presenter {
        /**
         * 获取分类列表
         */
        void getCategorys();
    }

    interface Model {
        /**
         * 获取分类列表
         * @return
         */
        Observable<List<Category>> getCategorys();

        /**
         * 保存分类到数据库
         * @param categories
         */
        void saveCategoryToDb(List<Category> categories);

        /**
         * 从数据库中获取一级分类列表
         * @return
         */
        List<Category> queryCategoriesToDb();
    }
}