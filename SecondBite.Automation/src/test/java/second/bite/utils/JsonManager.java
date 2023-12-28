package second.bite.utils;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.json.JSONObject;

import io.restassured.mapper.ObjectMapper;

public class JsonManager extends MainClass {
	
	private static FileWriter fileWriter;
	private static final ReadWriteLock fileLock = new ReentrantReadWriteLock();
//	private static final ObjectMapper mapper = new ObjectMapper();

	public static JSONObject readJsonFile(String JSONPath, String property) {
		String loc = new String(workingDir + JSONPath);
		File file = new File(loc);
		JSONObject json = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
			json = property.length()==0 ? new JSONObject(content) : new JSONObject(content).getJSONObject(property);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}


}
