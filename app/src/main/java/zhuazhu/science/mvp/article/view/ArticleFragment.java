package zhuazhu.science.mvp.article.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jkb.fragment.rigger.annotation.LazyLoad;
import com.jkb.fragment.rigger.annotation.Puppet;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import mejust.frame.mvp.view.BasePresenterFragment;
import mejust.frame.widget.adapter.RecyclerAdapter;
import mejust.frame.widget.refresh.OnRefreshListener;
import mejust.frame.widget.refresh.RefreshLayoutWrapper;
import zhuazhu.science.R;
import zhuazhu.science.app.ScienceApp;
import zhuazhu.science.di.module.ArticleModule;
import zhuazhu.science.mvp.article.ArticleContract;
import zhuazhu.science.mvp.index.adapter.ArtitcleAdapter;
import zhuazhu.science.mvp.index.model.ArticleBean;
import zhuazhu.science.mvp.web.view.WebViewActivity;

/**
 * 创建时间:2018/6/27 22:05<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2018/6/27 22:05<br/>
 * 描述:
 */
@LazyLoad
@Puppet
public class ArticleFragment extends BasePresenterFragment<ArticleContract.Presenter> implements ArticleContract.View {
    private static final String TAG = "ArticleFragment";
    private static final String PARAM_KEY = "cId";
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refresh)
    RefreshLayoutWrapper mRefresh;
    @Inject
    ArtitcleAdapter mArtitcleAdapter;
    /**
     * 二级分类的id
     */
    private int cId;
    public static ArticleFragment newInstance(int cId) {
        ArticleFragment articleFragment = new ArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PARAM_KEY, cId);
        articleFragment.setArguments(bundle);
        return articleFragment;
    }

    public String getFragmentTag() {
        return TAG;
    }

    public void onLazyLoadViewCreated(Bundle savedInstanceState) {
        mRefresh.autoRefresh();
    }



    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.fragment_article;
    }

    @Override
    protected void initData() {
        cId = getArguments().getInt(PARAM_KEY);
    }

    @Override
    protected void injectComponent() {
        ScienceApp.appComponent().articleComponent(new ArticleModule(this,mRefresh)).inject(this);
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(mArtitcleAdapter);
        mRefresh.setOnRefreshLoadmoreListener(new OnRefreshListener() {
            @Override
            public void update(RefreshLayoutWrapper refreshLayout) {
                mPresenter.loadData(cId,refreshLayout.getNextPageIndex());
            }
        });
        mArtitcleAdapter.setOnItemClickListener((RecyclerAdapter.OnItemClickListener<ArticleBean>) (id, articleBean, position) -> WebViewActivity.start(getActivity(),articleBean.getLink()));
    }

    @Override
    public void setAdapterData(List<ArticleBean> articleBeans) {
        mArtitcleAdapter.autoUpdateList(articleBeans);
    }
}
