package zhuazhu.science.mvp.childrencategory.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import mejust.frame.data.annotation.Title;
import mejust.frame.mvp.view.BasePresenterActivity;
import zhuazhu.science.R;
import zhuazhu.science.app.ScienceApp;
import zhuazhu.science.data.db.model.Category;
import zhuazhu.science.di.module.ChildrenCategoryModule;
import zhuazhu.science.mvp.childrencategory.ChildrenCategoryContract;

/**
 * @author zhuazhu
 */
@Title
public class ChildrenCategoryActivity extends BasePresenterActivity<ChildrenCategoryContract.Presenter> implements ChildrenCategoryContract.View {
    private static final String PARENT_CHAPTER_ID_KEY = "parentChapterId";
    private static final String TITLE_KEY = "title";
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

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
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            if(i==0){
                mTabLayout.addTab(mTabLayout.newTab().setText(category.getName()),i,true);
            }else{
                mTabLayout.addTab(mTabLayout.newTab().setText(category.getName()),i);
            }
        }
    }
}
