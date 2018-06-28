package zhuazhu.science.mvp.index.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jkb.fragment.rigger.annotation.Puppet;
import com.zhouwei.mzbanner.MZBannerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import mejust.frame.mvp.view.BasePresenterFragment;
import mejust.frame.widget.adapter.RecyclerAdapter;
import mejust.frame.widget.refresh.OnRefreshListener;
import mejust.frame.widget.refresh.RefreshLayoutWrapper;
import zhuazhu.science.R;
import zhuazhu.science.app.ScienceApp;
import zhuazhu.science.di.module.IndexModule;
import zhuazhu.science.mvp.index.IndexContract;
import zhuazhu.science.mvp.index.adapter.BannerCreator;
import zhuazhu.science.mvp.index.adapter.ArtitcleAdapter;
import zhuazhu.science.mvp.index.model.ArticleBean;
import zhuazhu.science.mvp.index.model.BannerBean;
import zhuazhu.science.mvp.web.view.WebViewActivity;

/**
 * @author zhuazhu
 */
@Puppet
public class IndexFragment extends BasePresenterFragment<IndexContract.Presenter> implements IndexContract.View {
    private static final String TAG = "IndexFragment";
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.refresh)
    RefreshLayoutWrapper mRefresh;

    private MZBannerView<BannerBean> mBannerView;
    private View mHeaderView;

    @Inject
    ArtitcleAdapter mArtitcleAdapter;
    @Inject
    BannerCreator mBannerCreator;

    public static IndexFragment newInstance() {
        IndexFragment indexFragment = new IndexFragment();
        indexFragment.setArguments(new Bundle());
        return indexFragment;
    }

    public String getFragmentTag() {
        return TAG;
    }


    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.fragment_index;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void injectComponent() {
        ScienceApp.appComponent().indexComponent(new IndexModule(this,mRefresh)).inject(this);
    }

    @Override
    protected void initView() {
        initAdapter();
        mPresenter.loadBanner();
        mRefresh.setOnRefreshLoadmoreListener(new OnRefreshListener() {
            @Override
            public void update(RefreshLayoutWrapper refreshLayout) {
                mPresenter.loadData(refreshLayout.getNextPageIndex());
            }
        });
        mRefresh.setPageSize(20);
        mRefresh.autoRefresh();
    }



    public void initAdapter() {
        mHeaderView = View.inflate(getActivity(), R.layout.index_header, null);
        mBannerView = mHeaderView.findViewById(R.id.banner);
        mBannerView.setBannerPageClickListener((view, i) -> {
            //TODO banner图点击事件
        });
        mArtitcleAdapter.setHeaderView(mHeaderView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(mArtitcleAdapter);
        mArtitcleAdapter.setOnItemClickListener((RecyclerAdapter.OnItemClickListener<ArticleBean>) (id, articleBean, position) -> WebViewActivity.start(getActivity(),articleBean.getLink()));
    }

    @Override
    public void onResume() {
        super.onResume();
        mBannerView.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBannerView.pause();
    }

    @Override
    public void setBanners(List<BannerBean> banners) {
        mBannerView.setPages(banners, mBannerCreator);
        mBannerView.start();
        mArtitcleAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAdapterData(List<ArticleBean> articleBeans) {
        mArtitcleAdapter.autoUpdateList(articleBeans);
    }

    @Override
    public void finishRefresh() {
        mRefresh.finishRefresh();
    }
}
