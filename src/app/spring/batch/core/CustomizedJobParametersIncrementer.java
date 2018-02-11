package app.spring.batch.core;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

public class CustomizedJobParametersIncrementer implements JobParametersIncrementer { 
	
	private Logger logger = LoggerFactory.getLogger(CustomizedBatchConfiguration.class);

	@Override
	public JobParameters getNext(JobParameters parameters) {    
		logger.info(" - getNext(parameters) start, params = [{}]", parameters); 

		long runId; 
		JobParametersBuilder builder = new JobParametersBuilder(); 

		Set<String> paramSet = parameters.getParameters().keySet(); 
		for(String key : paramSet) { 
			builder.addParameter(key, parameters.getParameters().get(key)); 
		} 

		if (parameters == null || parameters.isEmpty()) { 
			runId = 1L; 
		} else { 
			runId = parameters.getLong("job.run.id") + 1L; 
		} 

		builder.addLong("job.run.id", runId).toJobParameters(); 

		logger.info(" - JobParameters: {}", builder.toJobParameters().toString()); 

		return builder.toJobParameters(); 
	} 

} 