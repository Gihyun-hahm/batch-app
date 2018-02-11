package app.spring.batch.writer;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;


/**
 * Dummy {@link ItemWriter} which only logs data it receives.
 */
@Component("FileItemWriter")
public class FileItemWriter implements ItemWriter<Object> {

	private Logger logger = LoggerFactory.getLogger(FileItemWriter.class);
	
	private File result = new File("result/result.txt");
	
	/**
	 * @see ItemWriter#write(java.util.List)
	 */
	public void write(List<? extends Object> data) throws Exception {
		logger.info(" - {}", data);
		
		FileWriter fw = new FileWriter(result, true);
		Iterator<String> iter = (Iterator<String>) data.iterator();
		
		while (iter.hasNext()) {
			fw.write((String)iter.next());
			fw.write("\n");
		}
		fw.flush();
		
		fw.close();
	}

}
