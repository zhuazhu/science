package zhuazhu.science.mvp.mine.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jkb.fragment.rigger.annotation.Puppet;

import mejust.frame.mvp.view.BaseFragment;
import zhuazhu.science.R;

/**
 * @author zhuazhu
 */
@Puppet
public class MineFragment extends BaseFragment {

    private static final String TAG = "MineFragment";

    public static MineFragment newInstance(){
        MineFragment mineFragment = new MineFragment();
        mineFragment.setArguments(new Bundle());
        return mineFragment;
    }
    public String getFragmentTag(){
        return TAG;
    }
    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.fragment_mine;
    }
}
