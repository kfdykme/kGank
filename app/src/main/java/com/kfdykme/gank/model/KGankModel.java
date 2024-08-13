package com.kfdykme.gank.model;

import java.util.*;
import com.kfdykme.gank.bean.*;
import okhttp3.*;

public interface KGankModel
{

	Call getGankArticle(String type, int page);
	
	Call getGankSearch(String key, String type, int page);
	
	Call getLikedArticle(int page);
}
