package zhuazhu.science.mvp.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.barlibrary.BarHide;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import mejust.frame.mvp.view.BaseActivity;
import zhuazhu.science.mvp.main.MainActivity;
import zhuazhu.science.R;

/**
 * @author zhauzhu
 */
public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_launcher;
    }
    private void initView(){
        mImmersionBar.hideBar(BarHide.FLAG_HIDE_BAR).init();
        Flowable.timer(3, TimeUnit.SECONDS).subscribe(aLong -> {
            MainActivity.start(LauncherActivity.this);
            finish();
        });
    }
}
