package zhuazhu.science.data.net;

import java.util.List;

import io.reactivex.Observable;
import mejust.frame.data.annotation.ServiceUrl;
import retrofit2.http.GET;
import retrofit2.http.Path;
import zhuazhu.science.BuildConfig;
import zhuazhu.science.data.db.model.Category;
import zhuazhu.science.mvp.index.model.ArticleList;
import zhuazhu.science.mvp.index.model.BannerBean;

/**
 * @author zhuazhu
 **/
@ServiceUrl(BuildConfig.API_URL)
public interface ApiService {
    /**
     * 获取banner
     *
     * @return
     */
    @GET("banner/json")
    Observable<ResponseData<List<BannerBean>>> getBanner();

    /**
     * 获取首页列表
     *
     * @param page
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<ResponseData<ArticleList>> getIndexArticleData(@Path("page") int page);

    /**
     * 获得分类列表
     *
     * @return
     */
    @GET("tree/json")
    Observable<ResponseData<List<Category>>> getCategorys();
}
