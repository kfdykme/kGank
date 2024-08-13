package com.kfdykme.gank.bean;
import java.util.*;

public class DataEntity
{
	private String error;

	private List<result> results;


	public void setError(String error)
	{
		this.error = error;
	}

	public String getError()
	{
		return error;
	}

	public void setResults(List<result> results)
	{
		this.results = results;
	}

	public List<result> getResults()
	{
		return results;
	}

	@Override
	public String toString()
	{
		return "dataEntity { error : "+ error +", results : " + results +"}";
	}



}
