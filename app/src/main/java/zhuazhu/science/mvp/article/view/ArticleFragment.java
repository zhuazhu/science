package zhuazhu.science.mvp.article.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.jkb.fragment.rigger.annotation.Puppet;

import mejust.frame.mvp.view.BaseFragment;
import zhuazhu.science.R;

/**
 * 创建时间:2018/6/27 22:05<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2018/6/27 22:05<br/>
 * 描述:
 */
@Puppet
public class ArticleFragment extends BaseFragment {
    private static final String TAG = "ArticleFragment";
    private static final String PARAM_KEY = "cId";
    public static ArticleFragment newInstance(int cId){
        ArticleFragment articleFragment = new ArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PARAM_KEY,cId);
        articleFragment.setArguments(bundle);
        return articleFragment;
    }
    public String getFragmentTag() {
        return TAG;
    }
    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.fragment_article;
    }
}
