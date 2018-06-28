package zhuazhu.science.mvp.web.client;

import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * @author zhuazhu
 */
public class ScienceClient extends WebViewClient {

    @Override
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        //解决https不能访问的问题
        sslErrorHandler.proceed();
    }
}
