package zhuazhu.science.mvp.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.jkb.fragment.rigger.annotation.Puppet;
import com.jkb.fragment.rigger.rigger.Rigger;

import butterknife.BindView;
import butterknife.OnClick;
import mejust.frame.mvp.view.BaseActivity;
import zhuazhu.science.R;
import zhuazhu.science.mvp.category.view.CategoryFragment;
import zhuazhu.science.mvp.index.view.IndexFragment;
import zhuazhu.science.mvp.mine.view.MineFragment;

/**
 * @author zhuazhu
 */
@Puppet(containerViewId = R.id.fragment)
public class MainActivity extends BaseActivity {
    public static void start(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }
    @BindView(R.id.main_title)
    FrameLayout mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    private IndexFragment mIndexFragment;
    private CategoryFragment mCategoryFragment;
    private MineFragment mMineFragment;
    private void initView(){
        mIndexFragment = IndexFragment.newInstance();
        mCategoryFragment = CategoryFragment.newInstance();
        mMineFragment = MineFragment.newInstance();
        Rigger.getRigger(this).addFragment(R.id.fragment,mIndexFragment,mCategoryFragment,mMineFragment);
        clickIndex();
    }
    /**
     * 点击首页tab
     */
    @OnClick(R.id.radio_index)
    protected void clickIndex(){
        Rigger.getRigger(this).showFragment(mIndexFragment.getFragmentTag());
        mTitle.setVisibility(View.VISIBLE);
    }

    /**
     * 点击分类tab
     */
    @OnClick(R.id.radio_category)
    protected void clickCategory(){
        Rigger.getRigger(this).showFragment(mCategoryFragment.getFragmentTag());
        mTitle.setVisibility(View.VISIBLE);
    }

    /**
     * 点击我的tab
     */
    @OnClick(R.id.radio_mine)
    protected void clickMine(){
        Rigger.getRigger(this).showFragment(mMineFragment.getFragmentTag());
        mTitle.setVisibility(View.GONE);
    }
}
