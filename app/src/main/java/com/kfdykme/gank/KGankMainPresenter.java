package com.kfdykme.gank;
import com.kfdykme.gank.model.*;
import okhttp3.*;
import java.io.*;
import com.kfdykme.gank.bean.*;
import com.google.gson.*;
import android.support.v4.view.*;
import android.util.*;
import android.content.*;

public class KGankMainPresenter implements KGankMainContract.Presenter
{

	private KGankMainContract.View mView;
	
	private KGankModelImpl mModelImpl;
	
	public KGankMainPresenter(KGankMainContract.View mView){

		this.mView = mView;
		this.mView.setPresenter(this);
		mModelImpl = new KGankModelImpl();
	}
	
	
	@Override
	public void loadArticle(String type, int page)
	{
				
		Call mCall = mModelImpl.getGankArticle(type,page);
		mCall.enqueue(new Callback(){

				@Override
				public void onFailure(Call p1, IOException p2)
				{
					
					// TODO: Implement this method
				}

				@Override
				public void onResponse(Call p1, Response p2) throws IOException
				{
					DataEntity mDE = new Gson().fromJson(p2.body().string(),DataEntity.class);
					Log.i("test",""+mDE.getResults().size());
					mView.addViews(mDE.getResults());
					// TODO: Implement this method
				}
			});
		// TODO: Implement this method
	}

	@Override
	public void loadSearch(String key, String type, int page)
	{
		// TODO: Implement this method
	}


	
	
}
