import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader("C:/project/Git/Hackathon/src/Memory.txt");
			br=new BufferedReader(fr);
			String data;
			int line=1;
			int count=1;
			double total=0,max=0;
			double avg=0;
			JSONObject megabytes=new JSONObject();
			JSONObject value=new JSONObject();
			JSONArray list=new JSONArray();
			while((data=br.readLine())!=null)
			{
				if(line%2==0)
				{
					
					String v[]=data.split("       ");
					String size[]=v[1].split("   ");  //Kilobyte value
					float mb=Float.parseFloat(size[1])/1000;  //Megabyte value
					double val=Math.round(mb*100.0)/100.0;  //Round off to 2 decimal places
					if(max<val)
					{
						max=val;
					}
					//String cnt=n+"s";
					String cnt=count+"s";
					//System.out.println(count);
					megabytes.put(count, val);
					count=count+1;
					total+=val;
					//list.add(megabytes);
				}
				line++;
			}
			avg=total/count;
			value.put("AverageMemory(MB)", avg);
			value.put("values", megabytes);
			value.put("MaxMemory(MB)", max);
			value.put("UseCaseName", "Memory");
		
			list.add(value);
			
			try(FileWriter fw=new FileWriter("result.json"))
			{
				fw.write(list.toJSONString());
				fw.flush();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			JSONParser parser=new JSONParser();
			try(FileReader reader =new FileReader("result.json"))
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
