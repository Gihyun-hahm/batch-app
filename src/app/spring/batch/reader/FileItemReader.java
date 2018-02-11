package app.spring.batch.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;


/**
 * {@link ItemReader} with hard-coded input data.
 */

@Component("FileItemReader")
public class FileItemReader implements ItemReader<String> {

	private Logger logger = LoggerFactory.getLogger(FileItemReader.class);
	
	private Properties properties;
	
	private void initialize() {
		properties = new Properties();
		try {
			properties.load(new FileReader("C:\\Users\\jung5\\eclipse-workspace\\batch-app\\resources\\database\\batch.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Iterator<Object> keys;
	/**
	 * Reads next record from input
	 */
	@Override
	public String read() throws Exception {
		if (properties == null) {
			initialize();
			keys = properties.keySet().iterator();
		}
		
		String input = null;
		
		if (keys.hasNext()) {
			logger.info(" - reader input: {}", input = properties.getProperty((String)keys.next()));
		}
		
		return input;
	}

}
