package zhuazhu.science.mvp.index;

import java.util.List;

import io.reactivex.Observable;
import mejust.frame.mvp.BaseContract;
import zhuazhu.science.mvp.index.adapter.IndexAdapter;
import zhuazhu.science.mvp.index.model.ArticleBean;
import zhuazhu.science.mvp.index.model.ArticleList;
import zhuazhu.science.mvp.index.model.BannerBean;

/**
 * @author zhuazhu
 **/
public interface IndexContract {
    interface View extends BaseContract.View{
        /**
         * 设置banner图广告
         * @param banners
         */
        void setBanners(List<BannerBean> banners);

        /**
         * 设置列表属性
         * @param articleBeans
         */
        void setAdapterData(List<ArticleBean> articleBeans);

        /**
         * 结束刷新
         */
        void finishRefresh();
    }
    interface Presenter extends BaseContract.Presenter{
        void loadBanner();

        /**
         * 加载列表数据
         * @param pageIndex 页码
         */
        void loadData(int pageIndex);
    }
    interface Model{
        /**
         * 获取banner图
         * @return
         */
        Observable<List<BannerBean>> getBanners();

        /**
         * 获取首页列表
         * @param page
         * @return
         */
        Observable<ArticleList> getIndexArticleData(int page);
    }
}
