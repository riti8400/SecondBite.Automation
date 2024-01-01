package second.bite.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.json.JSONObject;

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
	
	/**
	 * A method to save output response value in outputValues.json file
	 */

	public static synchronized void writeOutputValue(String value, String attribute) {
		JSONObject jobj = new JSONObject();
		jobj.put("Value", value);
		Lock writeLock = fileLock.writeLock();
        writeLock.lock();
		try (RandomAccessFile file = new RandomAccessFile(workingDir + "/resources/output-generated/outputValues.json", "rw");
	             FileChannel channel = file.getChannel()) {
			try {
				JSONObject temp = JsonManager.readJsonFile(property.getProperty("outputValuesPath"),"");
				if(temp.has(attribute)) temp.remove(attribute);
				temp.put(attribute, jobj);
				file.writeBytes(temp.toString());
			} catch (IOException connectionException) {
				 writeLock.unlock();
			}
			finally {
				 writeLock.unlock();
			}
		} catch (IOException connectionException) {
//			logger.info("Could not close the connection" + connectionException.getMessage());
		}
	}


}
