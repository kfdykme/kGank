# kGank  

## 介绍

用[gank](gank.io/api)
提供的api制作的app


摸索着使用了MVP模式
## 功能

- 提供gank网上的数据
- 点击单项可进入对应页面

## 未完成功能

- 下拉刷新
- 收藏页面
- 侧边栏菜单
- 搜索界面
- 暂无

## 记录

1. listview的简单使用


 ``` Java

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
						
``` 
2. sugar的简单使用
##### 还没用到，暂不总结

3. okhttp3的简单使用


- 导包：

``` Java


 compile 'com.squareup.okhttp3:okhttp:3.6.0'

//用于转化实例
 compile 'com.google.code.gson:gson:2.2.4'
	

```

- 获取Call


``` Java

	@Override
	public Call getGankArticle(String type, int page)
	{

		//new a Call by using okhttp3 
		//and return it.
		
		String mBaseUrl = "http://gank.io/api/";

		final String NUM_PER_PAGE = "10";

		OkHttpClient mOkHttpClient = new OkHttpClient();
		Request.Builder builder = new Request.Builder().url(mBaseUrl +"data/" + type +"/" + NUM_PER_PAGE + "/" + page);

		final Request request =builder.build();

		Call mCall = mOkHttpClient.newCall(request);
		
		// TODO: Implement this method
		return mCall;
	}


```

- 额..不知道怎么分


``` java



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

```

## 备注1

AIDE 无法使用引入了注解的库
有机会弄明白原因并尝试使用

###### greenDao & retrofit & maybe others need it 

## 备注2

使用mvp模式和网络请求时，之前一直纠结于如何在model或presenter 中onResponse后再在得到返回后在view中刷新

后来发现其实可以返回一个call 然后在别处使用它

###### 还是有点纠结，难以说清

## 备注3

mvp模式看了这个[博客](m.blog.csdn.net/article/details?id=52658378)后感觉有点清晰起来了。












