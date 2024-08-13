package com.kfdykme.gank.view;
import android.webkit.*;
import android.content.*;
import android.util.*;
import android.widget.*;
import android.view.*;
import com.kfdykme.gank.*;
import android.view.View.*;
import com.kfdykme.gank.bean.*;

public  class KWebView  extends LinearLayout 
{
	private WebSettings wb ;
	
	private WebChromeClient wcc;
	
	private WebViewClient wvc;
	
	private ProgressBar progressBar;
	
	private TextView fTV;

	private TextView rTV;

	private TextView bTV;

	private TextView cTV;
	
	private TextView lTV;
	
	private WebView wv;
	
	private LinearLayout linearLayout;
	
	private result loadResult;
	

	private KGankMainPresenter mPresenter;
	
	
	public KWebView(Context context ){
		
		this(context, null);
	
	}
	
	
	public KWebView(Context context, AttributeSet attrs){
		super(context,attrs);
		
		
		LayoutInflater.from(context).inflate(R.layout.kwebviewlayout, this);

		wv = (WebView)findViewById(R.id.kwebviewlayoutWebView);
		
		
		initWebView(wv);
		
		progressBar = (ProgressBar)findViewById(R.id.kwebviewlayoutProgressBar);
		
		fTV = (TextView)findViewById(R.id.kwebviewlayoutForwardTextView);
		rTV = (TextView)findViewById(R.id.kwebviewlayoutReflashTextView);
		bTV = (TextView)findViewById(R.id.kwebviewlayoutBackTextView);
		cTV = (TextView)findViewById(R.id.kwebviewlayoutCopyTextView);
		lTV = (TextView)findViewById(R.id.kwebviewlayoutLikeTextView);
		
		
		linearLayout  = (LinearLayout)findViewById(R.id.kwebviewlayoutLinearLayout);
		
		fTV.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View view)
				{
					if(wv.canGoForward()){
						wv.goForward();
					}
					// TODO: Implement this method
				}
			});
			
		bTV.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View view)
				{
					if ((!wv.canGoBack())||wv.getUrl().equals("about:blank"))
					{

						closeWV();
					}
					else
					{
						wv.goBack();
						if(wv.getUrl().equals("about:blank"))
						{
							closeWV();
						}

					}
					// TODO: Implement this method
				}
			});
			
		cTV.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View view)
				{
					ClipboardManager cm = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
					cm.setText(wv.getUrl());
					Toast.makeText(getContext(),"Copied : "+wv.getUrl(),Toast.LENGTH_SHORT).show();
					
					// TODO: Implement this method
				}
			});
		
		rTV.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View view)
				{
					wv.reload();
					// TODO: Implement this method
				}
			});
			
			
		lTV.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Log.i("onCLikc","ITV");
					// TODO: Implement this method
				}
			});
	}

	public void setLoadResult(result loadResult)
	{
		this.loadResult = loadResult;
	}

	public result getLoadResult()
	{
		return loadResult;
	}
	
	


//
//	public void doWeb(View view)
//	{
//		switch(view.getId()){
//			case R.id.kwebviewlayoutBackTextView:
//				if(!wv.getUrl().equals("")){
//					wv.goBack();
//		
//				} else {
//					closeWV();
//				}
//				break;
//			case R.id.kwebviewlayoutReflashTextView:
//				wv.reload();
//				break;
//			case R.id.kwebviewlayoutForwardTextView:
//				if(wv.canGoForward()){
//					wv.goForward();
//				}
//				break;
//			case R.id.kwebviewlayoutCopyTextView:
//				ClipboardManager cm = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
//				cm.setText(wv.getUrl());
//				Toast.makeText(getContext(),"Copied : "+wv.getUrl(),Toast.LENGTH_SHORT).show();
//				break;
//				
//		}
//		// TODO: Implement this method
//	}

	private void closeWV()
	{
			setVisibility(View.GONE);
			getWv().loadUrl("about:blank");
			getWv().clearCache(true); 
			getWv().clearHistory();
		// TODO: Implement this method
	}

	
	

	
	
	public void initWebView(WebView webview){
		wb = webview.getSettings();
		wb.setJavaScriptEnabled(true);
		wb.setJavaScriptCanOpenWindowsAutomatically(true);
		wb.setBuiltInZoomControls(true);

		wvc = new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view,String url){
				view.loadUrl(url);
				return true;
			}
		};

		wcc = new WebChromeClient(){

			@Override
			public void onProgressChanged(WebView view, int newProgress)
			{
				if(newProgress <= 20)
					progressBar.setVisibility(View.VISIBLE);
				else if(newProgress >= 100){
					progressBar.setVisibility(View.GONE);
				}
				
				progressBar.setProgress(newProgress);
				}
		};

		webview.setWebChromeClient(wcc);
		webview.setWebViewClient(wvc);
		
	}
	
	
	




	public void setWv(WebView wv)
	{
		this.wv = wv;
	}

	public WebView getWv()
	{
		return wv;
	}

	public void setLinearLayout(LinearLayout linearLayout)
	{
		this.linearLayout = linearLayout;
	}

	public LinearLayout getLinearLayout()
	{
		return linearLayout;
	}
	
	
}
