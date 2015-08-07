package rbccps.iot.ncap.DA.CEP.InputParser;
public class StringInputParser {
	public String[] parse(String input_Query)
	{
		String[] query=input_Query.split(",");
		return query;
	}

}
