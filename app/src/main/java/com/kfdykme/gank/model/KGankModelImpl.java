package com.kfdykme.gank.model;
import java.util.*;

import android.util.*;
import okhttp3.*;
import com.kfdykme.gank.bean.*;
import com.google.gson.*;

public class KGankModelImpl implements KGankModel
{

	@Override
	public Call getGankArticle(String type, int page)
	{

		String mBaseUrl = "http://gank.io/api/";

		final String NUM_PER_PAGE = "10";

		OkHttpClient mOkHttpClient = new OkHttpClient();
		Request.Builder builder = new Request.Builder().url(mBaseUrl +"data/" + type +"/" + NUM_PER_PAGE + "/" + page);

		final Request request =builder.build();

		Call mCall = mOkHttpClient.newCall(request);
		
		// TODO: Implement this method
		return mCall;
	}

	public Call getDouBanApi(){

		Call mCall = new OkHttpClient()
									.newCall(
									new Request.Builder()
									.url("https://api.douban.com/v2/movie/top250?start=0&count=10").build());
		return mCall;
		
	}
	
	@Override
	public Call getGankSearch(String key, String type, int page)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public Call getLikedArticle(int page)
	{
		// TODO: Implement this method
		return null;
	}
	

	
}
