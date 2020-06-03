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

public class Battery {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		FileReader fr=null;
		BufferedReader br=null;
		JSONObject bvalue=new JSONObject();
		try {
			fr=new FileReader("C:/project/Git/HackathonFinal/src/Battery.txt");
			br=new BufferedReader(fr);
			String data,value="";
			Double drainvalue=0.0,drain_percent=0.0;
			
			while((data=br.readLine())!=null)
			{
				if(data.contains("Uid u0a202"))
				{
					StringTokenizer st=new StringTokenizer(data,":");
					String first=st.nextToken();
					String second=st.nextToken();
					StringTokenizer st1=new StringTokenizer(second," ");
					drainvalue=Double.parseDouble(st1.nextToken());
					drain_percent=drainvalue/1000;
					
				}
				if(data.contains("Foreground activities"))
				{
					StringTokenizer st=new StringTokenizer(data,":");
					
						String next=st.nextToken();
						value=st.nextToken();
									
				}
				
				

			}
			bvalue.put("Foreground_time",value);
			bvalue.put("Battery_percentage", drain_percent);
			bvalue.put("Battery_drain",drainvalue);	
			
			
			
			try(FileWriter fw=new FileWriter("Battery.json"))
			{
				fw.write(bvalue.toJSONString());
				fw.flush();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			JSONParser parser=new JSONParser();
			try(FileReader reader =new FileReader("Battery.json"))
			{
				Object obj=parser.parse(reader);
				JSONObject li=(JSONObject)obj;
				System.out.println(li.toString());
			}
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}


	}

}
