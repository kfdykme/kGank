package com.kfdykme.gank;
import com.kfdykme.gank.base.BaseView;
import com.kfdykme.gank.base.BasePresenter;
import java.util.List;
import com.kfdykme.gank.bean.result;

public class KGankMainContract
{
	public interface View extends BaseView<KGankMainPresenter>{
		void addViews(List<result> list_result);
		void initViews(Boolean isInited);
		Boolean isInited();
		void openWebWindow(String url);
	}
	public interface Presenter extends BasePresenter {
		void loadArticle(String type, int page);
		void loadSearch(String key , String type , int page);
	}
}
