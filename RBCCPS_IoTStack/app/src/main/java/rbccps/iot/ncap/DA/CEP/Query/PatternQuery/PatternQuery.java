package rbccps.iot.ncap.DA.CEP.Query.PatternQuery;

import java.util.ArrayList;
import java.util.HashMap;

import rbccps.iot.ncap.DA.CEP.Core.TimeEventPair;
import rbccps.iot.ncap.DA.CEP.Query.Query;

abstract public class PatternQuery implements Query{
	protected String query; 
	public PatternQuery(String in_query)
	{
		this.query=in_query;
	}
	abstract public ArrayList<TimeEventPair> process(HashMap<String,String> currentDataMap); 
}
