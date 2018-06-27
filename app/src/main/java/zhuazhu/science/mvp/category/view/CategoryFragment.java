package zhuazhu.science.mvp.category.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jkb.fragment.rigger.annotation.Puppet;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import mejust.frame.mvp.view.BasePresenterFragment;
import mejust.frame.widget.adapter.RecyclerAdapter;
import mejust.frame.widget.divider.SpacesItemDecoration;
import zhuazhu.science.R;
import zhuazhu.science.app.ScienceApp;
import zhuazhu.science.data.db.model.Category;
import zhuazhu.science.di.module.CategoryModule;
import zhuazhu.science.mvp.category.CategoryContract;
import zhuazhu.science.mvp.category.adapter.CategoryAdapter;
import zhuazhu.science.mvp.childrencategory.view.ChildrenCategoryActivity;

/**
 * @author zhuazhu
 */
@Puppet
public class CategoryFragment extends BasePresenterFragment<CategoryContract.Presenter> implements CategoryContract.View {
    private static final String TAG = "CategoryFragment";
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @Inject
    CategoryAdapter mCategoryAdapter;
    public static CategoryFragment newInstance() {
        CategoryFragment categoryFragment = new CategoryFragment();
        categoryFragment.setArguments(new Bundle());
        return categoryFragment;
    }

    public String getFragmentTag() {
        return TAG;
    }

    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.fragment_category;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void injectComponent() {
        ScienceApp.appComponent().categoryComponent(new CategoryModule(this)).inject(this);
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        SpacesItemDecoration decoration = new SpacesItemDecoration(0,1,R.color.c_dedede);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.addItemDecoration(decoration);
        mRecycler.setAdapter(mCategoryAdapter);
        mPresenter.getCategorys();
        mCategoryAdapter.setOnItemClickListener((RecyclerAdapter.OnItemClickListener<Category>) (id, category, position) -> {
            ChildrenCategoryActivity.start(getActivity(),category.getId(),category.getName());
        });
    }

    @Override
    public void setAdapterData(List<Category> categories) {
        mCategoryAdapter.set(categories);
    }
}
