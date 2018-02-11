package app.spring.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("FileItemProcessor")
public class FileItemProcessor implements ItemProcessor<String, String>{
	
	private Logger logger =  LoggerFactory.getLogger(FileItemProcessor.class);
	
	@Override
	public String process(String str) throws Exception {
		
		logger.info(" - process input: {}", str);
		
		return str;
	}
	

}
