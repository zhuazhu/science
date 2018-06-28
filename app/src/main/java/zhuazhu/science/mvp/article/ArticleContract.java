package zhuazhu.science.mvp.article;


import java.util.List;

import io.reactivex.Observable;
import mejust.frame.mvp.BaseContract;
import zhuazhu.science.mvp.index.model.ArticleBean;
import zhuazhu.science.mvp.index.model.ArticleList;

/**
 * 创建时间:2018/6/27 22:02<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2018/6/27 22:02<br/>
 * 描述:
 */
public interface ArticleContract {
    interface View extends BaseContract.View{
        /**
         * 记载文章数据
         * @param articleBeans
         */
        void setAdapterData(List<ArticleBean> articleBeans);
    }
    interface Presenter extends BaseContract.Presenter{
        /**
         * 加载数据
         * @param cid 二级分类的id
         * @param pageIndex 页码
         */
        void loadData(int cid,int pageIndex);
    }
    interface Model{
        /**
         * 获取文章列表
         * @param cid 二级分类的id
         * @param pageIndex 页码
         * @return
         */
        Observable<ArticleList> getArticles(int cid,int pageIndex);
    }
}
