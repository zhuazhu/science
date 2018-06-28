package zhuazhu.science.mvp.web.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;
import butterknife.OnClick;
import mejust.frame.common.log.Logger;
import mejust.frame.data.annotation.Title;
import mejust.frame.mvp.view.BaseActivity;
import mejust.frame.mvp.view.BasePresenterActivity;
import zhuazhu.science.R;
import zhuazhu.science.app.ScienceApp;
import zhuazhu.science.di.module.WebViewModule;
import zhuazhu.science.mvp.web.WebViewContract;
import zhuazhu.science.mvp.web.client.ScienceChromeClient;
import zhuazhu.science.mvp.web.client.ScienceChromeClient.OnRecievedProgressListener;
import zhuazhu.science.mvp.web.client.ScienceChromeClient.onReceivedTitleListener;
import zhuazhu.science.mvp.web.client.ScienceClient;
@Title
public class WebViewActivity extends BasePresenterActivity<WebViewContract.Presenter> implements WebViewContract.View,OnRecievedProgressListener,onReceivedTitleListener {
    private static final String TAG = "WebViewActivity";
    private static final String URL_KEY = "url";

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(URL_KEY, url);
        context.startActivity(intent);
    }

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.progress)
    ProgressBar mProgressBar;
    @BindView(R.id.web_view)
    WebView mWebView;

    private String mUrl;
    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initParam() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra(URL_KEY);
    }

    @Override
    protected void injectComponent() {
        ScienceApp.appComponent().webViewComponent(new WebViewModule(this)).inject(this);
    }
    @Override
    protected void initView() {
        //网页中的视频，上屏幕的时候，可能出现闪烁的情况,需要如下设置：Activity在onCreate时需要设置
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        ScienceChromeClient scienceChromeClient = new ScienceChromeClient();
        scienceChromeClient.setOnReceivedTitleListener(this);
        scienceChromeClient.setOnRecievedProgressListener(this);
        mWebView.setWebChromeClient(scienceChromeClient);
        mWebView.setWebViewClient(new ScienceClient());
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl(mUrl);
    }

    @Override
    public void onReceivedTitle(String host, String title) {
        Logger.i(TAG, "标题为->%s", title);
        if (mTitle!=null) {
            mTitle.setText(title);
        }
        
    }

    @Override
    public void onReceivedProgress(int progress) {
        Logger.i(TAG, "加载进度为->%d", progress);
        if(mProgressBar!=null){
            mProgressBar.setProgress(progress);
            if (progress == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }
    }
}
