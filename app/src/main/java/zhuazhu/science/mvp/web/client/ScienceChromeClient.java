package zhuazhu.science.mvp.web.client;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

/**
 * @author zhuazhu
 */
public class ScienceChromeClient extends WebChromeClient {
    private onReceivedTitleListener mOnReceivedTitleListener;
    private OnRecievedProgressListener mOnRecievedProgressListener;

    public void setOnRecievedProgressListener(OnRecievedProgressListener onRecievedProgressListener) {
        mOnRecievedProgressListener = onRecievedProgressListener;
    }

    public void setOnReceivedTitleListener(onReceivedTitleListener onReceivedTitleListener) {
        mOnReceivedTitleListener = onReceivedTitleListener;
    }

    @Override
    public void onProgressChanged(WebView webView, int i) {
        if (mOnRecievedProgressListener!=null) {
            mOnRecievedProgressListener.onReceivedProgress(i);
        }
    }
    private static final String HTTP_STRING = "http://";
    private static final String HTTPS_STRING = "https://";
    @Override
    public void onReceivedTitle(WebView webView, String s) {
        if(mOnReceivedTitleListener!=null){
            String url = webView.getUrl();
            if(url.contains(HTTP_STRING)){
                url = url.replace(HTTP_STRING,"");
            }
            if(url.contains(HTTPS_STRING)){
                url = url.replace(HTTPS_STRING,"");
            }
            String host = url.substring(0,url.indexOf("/"));
            mOnReceivedTitleListener.onReceivedTitle(host,s);
        }
    }

    /**
     * 进度加载的监听
     */
    public interface OnRecievedProgressListener{
        void onReceivedProgress(int progress);
    }
    /**
     * 标题内容的接收监听
     */
    public interface onReceivedTitleListener{
        void onReceivedTitle(String host, String title);
    }
}
