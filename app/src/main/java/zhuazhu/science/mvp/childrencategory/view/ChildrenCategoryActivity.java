package zhuazhu.science.mvp.childrencategory.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.jkb.fragment.rigger.annotation.Puppet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import mejust.frame.data.annotation.Title;
import mejust.frame.mvp.view.BasePresenterActivity;
import zhuazhu.science.R;
import zhuazhu.science.app.ScienceApp;
import zhuazhu.science.data.db.model.Category;
import zhuazhu.science.di.module.ChildrenCategoryModule;
import zhuazhu.science.mvp.article.view.ArticleFragment;
import zhuazhu.science.mvp.childrencategory.ChildrenCategoryContract;
import zhuazhu.science.mvp.childrencategory.adapter.CategoryPagerAdapter;

/**
 * @author zhuazhu
 */
@Title
@Puppet
public class ChildrenCategoryActivity extends BasePresenterActivity<ChildrenCategoryContract.Presenter> implements ChildrenCategoryContract.View {
    private static final String PARENT_CHAPTER_ID_KEY = "parentChapterId";
    private static final String TITLE_KEY = "title";
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Inject
    CategoryPagerAdapter mCategoryPagerAdapter;

    /**
     * @param context
     * @param parentChapterId 一级分类的id
     * @param title           标题
     */
    public static void start(Context context, int parentChapterId, String title) {
        Intent intent = new Intent(context, ChildrenCategoryActivity.class);
        intent.putExtra(PARENT_CHAPTER_ID_KEY, parentChapterId);
        intent.putExtra(TITLE_KEY, title);
        context.startActivity(intent);
    }

    /**
     * 一级分类的id
     */
    private int parentChapterId;
    /**
     * 标题
     */
    private String title;

    @Override
    protected void initParam() {
        Intent intent = getIntent();
        parentChapterId = intent.getIntExtra(PARENT_CHAPTER_ID_KEY, 0);
        title = intent.getStringExtra(TITLE_KEY);
    }

    @Override
    protected void injectComponent() {
        ScienceApp.appComponent().childrenCategoryComponent(new ChildrenCategoryModule(this)).inject(this);
    }

    @Override
    protected void initView() {
        mTitle.setText(title);
        mPresenter.getCategories(parentChapterId);
    }

    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_children_category;
    }

    @Override
    public void initTabs(List<Category> categories) {
        List<Fragment> fragments = new ArrayList<>();
        int size = categories.size();
        for (Category category : categories) {
            fragments.add(ArticleFragment.newInstance(category.getId()));
        }
        mCategoryPagerAdapter.setFragments(fragments);
        mViewPager.setAdapter(mCategoryPagerAdapter);

        mViewPager.setOffscreenPageLimit(size);
        mTabLayout.setupWithViewPager(mViewPager,true);
        if(size<=4){
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        }else{
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        for (int i = 0; i < size; i++) {
            Category category = categories.get(i);
            mTabLayout.getTabAt(i).setText(category.getName());
        }
    }

    @Override
    public FragmentManager getManager() {
        return getSupportFragmentManager();
    }
}
