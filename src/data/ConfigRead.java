package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class ConfigRead {
	
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static UserData load() throws IOException {
		
		UserData userData = null;
		
		try(Reader reader = new FileReader(new File("C:\\Users\\Yılmaz\\eclipse-workspace\\H2test\\save\\userData.json"))){
			
			userData = gson.fromJson(reader, UserData.class);
			
		}catch(FileNotFoundException f) {
			f.printStackTrace();
		}
		
		
		return userData;
	}

}
