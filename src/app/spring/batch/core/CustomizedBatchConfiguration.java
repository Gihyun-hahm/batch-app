package app.spring.batch.core;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/***
 * configuration annotaion
 * @author jung5
 */
@Configuration
public class CustomizedBatchConfiguration {

	private Logger logger = LoggerFactory.getLogger(CustomizedBatchConfiguration.class);

	@Value("${batch.jdbc.driver}")
	private String driverClassName;

	@Value("${batch.jdbc.url}")
	private String driverUrl;

	@Value("${batch.jdbc.user}")
	private String driverUsername;

	@Value("${batch.jdbc.password}")
	private String driverPassword;

	@Autowired
	@Qualifier("jobRepository")
	private JobRepository jobRepository;

	/**
	 * @return org.apache.commons.dbcp.BasicDataSource(implements javax.sql.DataSource)
	 */
	@Bean
	public DataSource dataSource() {
		logger.info(" - DataSource Bean.");

		BasicDataSource ds  = new BasicDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(driverUrl);
		ds.setUsername(driverUsername);
		ds.setPassword(driverPassword);

		logger.info(" - {}",  ds);

		return ds;
	}

	/**
	 * @return org.springframework.batch.core.launch.support.SimpleJobLauncher
	 */
	@Bean
	public SimpleJobLauncher jobLauncher() {
		logger.info(" - SimpleJobLauncher Bean.");
		
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);

		return jobLauncher;
	}

	/**
	 * @return org.springframework.transaction.PlatformTransactionManager
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		logger.info(" - TransactionManager Bean. ");

		return new DataSourceTransactionManager(dataSource());
	}

}
