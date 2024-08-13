package com.kfdykme.gank.view;
import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.view.View.*;
import android.webkit.*;
import android.widget.*;
import com.google.gson.*;
import com.kfdykme.gank.*;
import com.kfdykme.gank.bean.*;
import java.io.*;
import java.util.*;
import okhttp3.*;
import android.util.*;
import android.graphics.*;
import android.widget.TableRow.*;
import java.net.*;
import android.net.*;
import android.content.*;

import java.util.zip.*;
import android.app.AlertDialog;
import android.app.Dialog;
import android.widget.AdapterView.*;

public class KGankFragment extends Fragment implements KGankMainContract.View
{


	
	private String mTitle;

	private String mType ;

	private int mPage =  0;

	private int mSearchPage = 0;

	private List<result> mResults ;
	
	
	private ListView mListView;
	
	private SimpleAdapter mSimpleAdapter;
	
	private View view ;
	private TextView mTextViewLoading;
	
	private Button udaButton;
	
	private ProgressBar mProgressBarLoading;
	
	private KWebView mKWebView;
	
	
	private KGankMainPresenter mPresenter;

	public static final String BUNDLE_TITLE = "title";

	

	@Override
	public void addViews( List<result> list_result)
	{
		if(mResults == null)
			mResults = list_result;
		else
			mResults.addAll(list_result);


		final List<result> fList_result = mResults;

		getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run()
				{
					//	Toast.makeText(getContext(),fList_result.size() +getMType() + " item load start",Toast.LENGTH_SHORT).show();

					initViews(isInited());

					
					// Creat a list of HashMap to get the datas shows on listview
					ArrayList<HashMap<String,Object>> mArrayList = new ArrayList<HashMap<String, Object>>();
					int mId = 0;
					for(result mResult : fList_result){

						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("Desc",mResult.getDesc());
						map.put("Date",mResult.getPublishedAt());
						map.put("Who",mResult.getWho());
						map.put("Id",(++mId) +"");
						mArrayList.add(map);
					}

					
					mSimpleAdapter = new SimpleAdapter(
						getContext(),
						mArrayList,
						R.layout.listview_dataitem,
						new String[] {"Desc","Date","Who","Id"},
						new int[] {R.id.listview_dataitem_descText,
							R.id.listview_dataitem_datetext,
							R.id.listview_dataitem_whotext,
							R.id.listview_dataitem_IdText});

					mListView.setAdapter(mSimpleAdapter);

					mListView.setOnItemClickListener(new OnItemClickListener(){

							@Override
							public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
							{
								LinearLayout l = (LinearLayout) p2;
								TextView t = (TextView)l.findViewById(R.id.listview_dataitem_descText);

								for(result resultb :fList_result){
									if(resultb.getDesc().equals(t.getText().toString()))
										openWebWindow(resultb.getUrl());
								}


								// TODO: Implement this method
							}
						});
						
					
					//	Toast.makeText(getContext(),fList_result.size() + " " + getMType() + " item load finish",Toast.LENGTH_SHORT).show();
				}
			});
		// TODO: Implement this method
	}

	


	@Override
	public void initViews(Boolean isInited)
	{
		if(isInited){
			mTextViewLoading.setVisibility(View.GONE);
			mProgressBarLoading.setVisibility(View.GONE);
			mListView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public Boolean isInited()
	{

		return (mProgressBarLoading.getVisibility() == View.VISIBLE);
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Bundle bundle = getArguments();

		if (bundle != null)
		{
			mTitle = bundle.getString(BUNDLE_TITLE);
			mType = mTitle;
		}

		if (view == null)
		{
			
			view = inflater.inflate(R.layout.kgankfragmen, container, false);
			
			mKWebView = (KWebView)view.findViewById(R.id.kgankfragmen_KWebView);
			udaButton = (Button)view.findViewById(R.id.vpsfragmentlayoutuDaButton);
			mListView = (ListView)view.findViewById(R.id.kgankfragmen_ListView);
			mProgressBarLoading = (ProgressBar)view.findViewById(R.id.kgankfragmenProgressBar);
			mTextViewLoading = (TextView)view.findViewById(R.id.kgankfragmenLoadingTextView);
			
			udaButton.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						mPresenter.loadArticle(getMType(), ++mPage);	
						
						// TODO: Implement this method
					}
				});
				
			mPresenter.loadArticle(getMType(), ++mPage);	
			
			
		} 
		
		return view;

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		

		mPresenter = new KGankMainPresenter(this);

		
	}


	@Override
	public void openWebWindow(String url)
	{
		mKWebView.setVisibility(View.VISIBLE);
		mKWebView.getWv().loadUrl(url);
		// TODO: Implement this method
	}

	
	


	@Override
	public void setPresenter(KGankMainPresenter presenter)
	{
		this.mPresenter = presenter;
	}
	
	
	

	public static KGankFragment newInstance(String title)
	{
		Bundle bundle = new Bundle();

		bundle.putString(BUNDLE_TITLE, title);


		KGankFragment fragment = new KGankFragment();

		fragment.setArguments(bundle);
		
		return fragment;
	}


	
	
	

	public void setMType(String mType)
	{
		this.mType = mType;
	}

	public String getMType()
	{
		return mType;
	}
	
	

	public void setMPage(int mPage)
	{
		this.mPage = mPage;
	}

	public int getMPage()
	{
		return mPage;
	}

	public void setMSearchPage(int mSearchPage)
	{
		this.mSearchPage = mSearchPage;
	}

	public int getMSearchPage()
	{
		return mSearchPage;
	}
	
	


	public void setTitle(String mTitle)
	{
		this.mTitle = mTitle;
	}

	public String getTitle()
	{
		return mTitle;
	}




}
