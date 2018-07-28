package com.uurobot.androidcodeutil.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.uurobot.androidcodeutil.R;

public class WebviewActivity extends AppCompatActivity {

	private WebView webView;

	private LinearLayout webViewLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		webViewLayout = findViewById(R.id.web_view);
		webView = new WebView(this);
		webViewLayout.addView(webView);

		settings();
		webView.loadUrl("https://www.baidu.com/");//WebView加载的网页使用loadUrl

		setWebChromeClient();
		setWbViewClient();
		setDownloadListener();
	}

	private void setDownloadListener() {
		webView.setDownloadListener(new DownloadListener() {//下载监听
			@Override
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				Log.e("WebviewActivity", url + ", " + userAgent + ", " + contentDisposition + ", " + mimetype + ", " + contentLength);
			}
		});
	}

	private void setWbViewClient() {
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//需要设置在当前WebView中显示网页，才不会跳到默认的浏览器进行显示
				Log.d("WebviewActivity", url);
				return true;
			}

			@Override
			public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
				super.onReceivedError(view, request, error);
				//加载出错了
				Log.d("WebviewActivity", "error.getDescription():" + error.getDescription());
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				//加载完成
				Log.d("WebviewActivity", url);
			}
		});
	}

	private void setWebChromeClient() {
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				//加载的进度
				Log.d("WebviewActivity", "newProgress:" + newProgress);
			}

			@Override
			public void onReceivedTitle(WebView view, String title) {
				//获取WebView的标题
				Log.d("WebviewActivity", title);
			}

			@Override
			public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
				return super.onJsAlert(view, url, message, result);
				//Js 弹框
			}

			@Override
			public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
				AlertDialog.Builder b = new AlertDialog.Builder(WebviewActivity.this);
				b.setTitle("删除");
				b.setMessage(message);
				b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						result.confirm();
					}
				});
				b.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						result.cancel();
					}
				});
				b.create().show();
				return true;
			}
		});
	}

	private void settings() {
		WebSettings webSettings = webView.getSettings();//获得WebView的设置
		webSettings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放
		webSettings.setLoadWithOverviewMode(true);//适配
		webSettings.setJavaScriptEnabled(true);  //支持js
		webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
		webSettings.setDomStorageEnabled(true);// 开启 DOM storage API 功能
		webSettings.setDatabaseEnabled(true);//开启 database storage API 功能
		webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//HTTPS，注意这个是在LOLLIPOP以上才调用的
		webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能
		webSettings.setBlockNetworkImage(true);//关闭加载网络图片，在一开始加载的时候可以设置为true，当加载完网页的时候再设置为false
	}


	@Override
	protected void onPause() {
		super.onPause();
		if (webView != null) {
			webView.onPause();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (webView != null) {
			webView.onResume();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (webView != null) {
			webView.clearCache(true); //清空缓存
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
				if (webViewLayout != null) {
					webViewLayout.removeView(webView);
				}
				webView.removeAllViews();
				webView.destroy();
			}
			else {
				webView.removeAllViews();
				webView.destroy();
				if (webViewLayout != null) {
					webViewLayout.removeView(webView);
				}
			}
			webView = null;
		}
	}
	/*webView.addJavascriptInterface(new WebAppInterface(this), "WebJs");
	public class WebAppInterface {
		Context mContext;
		public WebAppInterface(Context c) {
			mContext = c;
		}
		@JavascriptInterface
		public void method() {
		}
	}
	webView.loadUrl("javascript:jsMethod()");//这是WebView最简单的调用JS的方法*/
}
