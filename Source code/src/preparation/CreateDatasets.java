package preparation;
import java.io.*;
import java.util.ArrayList;

import org.json.*;

import weka.core.Utils;


public class CreateDatasets {

	public static void createDatasets(String filename) {
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(filename));
	        ArrayList<String> data = new ArrayList<>();
	        String sentiment = "";
	        String line = br.readLine();
	        int no_pos = 0;
	        int no_neg = 0;
	        while (line != null) {
	        	if(no_neg + no_neg == 2000) {
	        		break;
	        	}
	        	JSONObject jobj = new JSONObject(line);
	        	
	        	
	        	JSONObject votes = new JSONObject(jobj.getJSONObject("votes").toString());
	        	int useful = votes.getInt("useful");
	        	int cool = votes.getInt("cool");
	        	
	        	if(jobj.getInt("stars") > 3 && no_pos < 1000) {
	        		if(no_pos == 1000) 
	        			continue;
	        		sentiment = "positive";
	        		String row = Utils.quote(jobj.getString("text")) + ", " + sentiment + ", " + useful  + ", " + cool ;
	        		data.add(row);
	        		no_pos++;
	        	} else if (jobj.getInt("stars") < 3 && no_neg < 1000) {
	        		sentiment = "negative";
	        		String row = Utils.quote(jobj.getString("text")) + ", " + sentiment + ", " + useful + ", " + cool ;
	        		data.add(row);
	        		no_neg++;
	        	}
	        	
	            line = br.readLine();
    
	        }
	        
	        PrintWriter writer = new PrintWriter("reviewsDataset.arff", "UTF-8");
	        writer.println("@relation reviewsDataset");
			writer.println();
			writer.println("@attribute text STRING");
			writer.println("@attribute review_type {positive, negative}");
			writer.println("@attribute votes_useful numeric");
			writer.println("@attribute votes_cool numeric");
			writer.println();
			writer.println("@data");
			for (int i = 0; i < data.size(); i++) {
				writer.println(data.get(i));
			}
			writer.close();
			System.out.println("Reviews extraction completed!");
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
}
