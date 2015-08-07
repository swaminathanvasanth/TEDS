package rbccps.iot.ncap.DA.CEP.Query.BooleanQuery;

import java.util.HashMap;

import rbccps.iot.ncap.DA.CEP.Query.Query;

abstract public class BooleanQuery implements Query{
	protected String query; 
	public BooleanQuery(String in_query)
	{
		this.query=in_query;
		//System.out.println(this.query); 
	}
	abstract public boolean process(HashMap<String,String> currentDataMap);
}
