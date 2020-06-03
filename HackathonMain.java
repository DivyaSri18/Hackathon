import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HackathonMain {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		FileReader fr=null;
		BufferedReader br=null;
		JSONObject cpuvalue=new JSONObject();
		JSONObject value=new JSONObject();
		JSONObject values=new JSONObject();
		JSONArray list=new JSONArray();
		try {
			fr=new FileReader("C:/project/Git/HackathonFinal/src/CPU.txt");
			br=new BufferedReader(fr);
			String data;
			double max=0;
			int count=0;
			double total=0,avg=0;
			
			while((data=br.readLine())!=null)
			{
				
				StringTokenizer st=new StringTokenizer(data," ");
				while(st.hasMoreTokens())
				{
					String next=st.nextToken();
					if(next.equals("S")||next.equals("R")||next.equals("D"))
					{
						count++;
						double cvalue=Double.parseDouble(st.nextToken());
						if(max<cvalue)
						{
							max=cvalue;
							
						}
						
						//String cnt=count+"s";
						String cnt=count+"s";
						
						total+=cvalue;
						cpuvalue.put(count,cvalue);
						
					}

				}
				
			}
			
			avg=total/count;
			avg=Math.round(avg*100.0)/100.0;
			values.put("values", cpuvalue);
			values.put("maxcpu", max);
			values.put("Avgcpu", avg);
			value.put("sampletransaction", values);
			list.add(value);
			
			try(FileWriter fw=new FileWriter("results.json"))
			{
				fw.write(list.toJSONString());
				fw.flush();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			JSONParser parser=new JSONParser();
			try(FileReader reader =new FileReader("results.json"))
			{
				Object obj=parser.parse(reader);
				JSONArray li=(JSONArray)obj;
				System.out.println(li.toString());
			}
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}

	}

}
