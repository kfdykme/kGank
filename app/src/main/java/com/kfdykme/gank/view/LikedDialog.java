//package com.kfdykme.gank.view;
//import android.app.Dialog;
//import android.content.*;
//import android.util.*;
//import java.util.*;
//import android.view.View;
//import com.kfdykme.gank.R;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.*;
//import android.view.View.*;
//import com.kfdykme.gank.bean.*;
//import android.graphics.*;
//
//public class LikedDialog 
//{
//	private Dialog dialog;
//	
//	private Context  context;
//	
//	private VPSFragment fragment;
//	
//	private View view;
//	
//	private List<Liked> likes;
//	
//	public LikedDialog(Context context){
//		this(context, null);
//	}
//	
//	public LikedDialog(Context context, VPSFragment fragment){
//		this.context = context;
//		this.fragment = fragment;
//	
//	}
//	
//
//	public  Dialog creatDialog(){
//		view = View.inflate(context,R.layout.liked,null);
//
//		dialog = new Dialog(context,R.style.AppTheme);
//
//		dialog.setCanceledOnTouchOutside(true);
//
//		Window window = dialog.getWindow();
//
//		window.requestFeature(window.FEATURE_NO_TITLE);
//
//		dialog.setContentView(view);
//
//		WindowManager.LayoutParams lp =  window.getAttributes();
//
//		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//		DisplayMetrics  outMetrics = new DisplayMetrics();
//
//		wm.getDefaultDisplay().getMetrics(outMetrics);
//
//		lp.width = (int)(outMetrics.widthPixels * 0.9);
//		//lp.height = (int)(((new DataText(context)).getLayoutParams().height) * likes.size());
//		lp.height = (int)( outMetrics.heightPixels * 0.7);
//
//		window.setAttributes(lp);
//		
//		initiData();
//		
//		inisitalView();
//		dialog.show();
//		return dialog;
//	}
//	
//	private void inisitalView(){
//		
//		
//	LinearLayout verticalLinaerLayout = (LinearLayout) view.findViewById(R.id.likedVerticalLinearLayout);
//
//	
//	for (int i = 0; i < likes.size() ; i++)
//	{
//
//		DataText dt = new DataText(context);
//		dt.getDateTextView().setText("");
//		dt.getDescTextView().setText(likes.get(i).getDesc());
//		dt.getDescTextView().setTextColor(Color.parseColor("#fffff0"));
//		final String url = likes.get(i).getUrl();
//
//		final int j = i;
//		dt.getDescTextView().setOnClickListener(new OnClickListener(){
//
//				@Override
//				public void onClick(View view)
//				{
//					fragment.getKWebView().setVisibility(View.VISIBLE);
//					fragment.getKWebView().getWv().loadUrl(url);
//					dialog.dismiss();
//				}
//
//			});
//		dt.getDescTextView().setOnLongClickListener(new OnLongClickListener(){
//
//				@Override
//				public boolean onLongClick(View p1)
//				{
//					List<Liked> l = Liked.listAll(Liked.class);
//					for(Liked i : l){
//						if(i.getDesc().equals(((TextView)p1).getText().toString())){
//							i.delete();
//							dialog.dismiss();
//						}
//					}
//
//
//					// TODO: Implement this method
//					return false;
//				}
//			});
//		dt.getWhoTextView().setText("");
//		verticalLinaerLayout.addView(dt);
//	}	
//	// TODO: Implement this method
//	}
//
//	private void initiData()
//	{
//
//		likes = Liked.listAll(Liked.class);
//
//
//		// TODO: Implement this method
//	}
//
//	
//	
//}
