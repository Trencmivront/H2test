package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConfigSave {
	
	 private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	    public static void save(UserData config) {
	    	File file = new File("C:\\Users\\Yılmaz\\eclipse-workspace\\H2test\\save\\userData.json");
	        try (FileWriter writer = new FileWriter(file)) {
	        	
	            gson.toJson(config, writer);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	
}
