package com.pool.configuration.batch;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.AbstractBatchConfiguration;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.pool.configuration.batch.itemreader.UserItemReader;
import com.pool.domin.WeWish;

//@Configuration
//@EnableBatchProcessing
public class WeWishBatchConfiguration extends DefaultBatchConfigurer{

	@Autowired
	public JobBuilderFactory jobBuilderFactory; 
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	@Autowired
	EntityManagerFactory emf;
	
	private UserItemReader<WeWish> reader;

	@StepScope
	@Bean
	public UserItemReader<WeWish> reader() {
		return reader;
	}

	public void setReader(UserItemReader<WeWish> reader) {
		this.reader = reader;
	}
	@Bean
	@StepScope
	public JpaItemWriter<WeWish> writer(){
		JpaItemWriter<WeWish> writer=new JpaItemWriter<>();
		writer.setEntityManagerFactory(emf);
		return writer;
	}
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("stepName")
				.<WeWish,WeWish>chunk(10)
				.reader(reader())
				.writer(writer())
				.build();
	}
	
	@Bean
	public Job weWishJob() {
		return jobBuilderFactory.get("nameOfFactory")
				.incrementer(new RunIdIncrementer())
				.start(step1())
				.build();
	}
	@Override
	public JobRepository createJobRepository() throws Exception {
		MapJobRepositoryFactoryBean factoryBean=new MapJobRepositoryFactoryBean();
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}
}
