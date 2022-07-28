package com.pool.configuration.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.pool.configuration.batch.listener.FirstJobListener;
import com.pool.configuration.batch.listener.FirstStepExecutionListener;
import com.pool.configuration.batch.reader.StudentFlatFileItemReader;
import com.pool.configuration.batch.writer.StudentFlatFileItemWriter;
import com.pool.modal.StudentCsv;
import com.pool.service.batch.CustomTasklet;

@Configuration
@EnableBatchProcessing
public class FlatFileReaderBatchConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private StudentFlatFileItemReader studentFlatFileItemReader; 
	
	@Autowired
	private StudentFlatFileItemWriter studentFlatFileItemWriter;
	
	@Value("${csv_file_path}")
	private String csvFilePath;
	
	@Bean
	public Job flatFileJob() {
		return jobBuilderFactory
								.get("FLAT FILE Job")
								.incrementer(new RunIdIncrementer())
								.start(flatFileStep())
								.build();
	}
	
	private Step flatFileStep() {
		return stepBuilderFactory
				                .get("FLAT FILE Step")
				                .<StudentCsv,StudentCsv>chunk(2)
				                //.reader(flatFileItemReader())
				                //.reader(studentFlatFileItemReader.flatFileItemReader(csvFilePath))
				                //.reader(flatFileItemReaderFromJobParam(null))
				                .reader(customFlatFileItemReaderFromJobParam(null))
				                .writer(studentFlatFileItemWriter)
				                .build();
	}
	
	public FlatFileItemReader<StudentCsv> flatFileItemReader(){
		FlatFileItemReader<StudentCsv> itemReader=new FlatFileItemReader<>();
		Resource resource = new FileSystemResource(csvFilePath);
		itemReader.setResource(resource);
		
		DefaultLineMapper< StudentCsv> lineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
		delimitedLineTokenizer.setNames("ID","First Name","Last Name",	"Email");
		lineMapper.setLineTokenizer(delimitedLineTokenizer);
		lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<StudentCsv>() {
			{
				setTargetType(StudentCsv.class);
			}
		});
		itemReader.setLineMapper(lineMapper);
		itemReader.setLinesToSkip(1);
		return itemReader;
	}
	
	@Bean
	@StepScope
	public FlatFileItemReader<StudentCsv> flatFileItemReaderFromJobParam(@Value("#{jobParameters['inputFile']}") FileSystemResource fileSystemResource){
		FlatFileItemReader<StudentCsv> itemReader=new FlatFileItemReader<>();
		
		itemReader.setResource(fileSystemResource);
		
		DefaultLineMapper< StudentCsv> lineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
		delimitedLineTokenizer.setNames("ID","First Name","Last Name",	"Email");
		lineMapper.setLineTokenizer(delimitedLineTokenizer);
		lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<StudentCsv>() {
			{
				setTargetType(StudentCsv.class);
			}
		});
		itemReader.setLineMapper(lineMapper);
		itemReader.setLinesToSkip(1);
		return itemReader;
	}
	
	@Bean
	@StepScope
	public FlatFileItemReader<StudentCsv> customFlatFileItemReaderFromJobParam(@Value("#{jobParameters['inputFile']}") FileSystemResource fileSystemResource){
		FlatFileItemReader<StudentCsv> itemReader=new FlatFileItemReader<>();
		
		itemReader.setResource(fileSystemResource);
		
		DefaultLineMapper< StudentCsv> lineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
		delimitedLineTokenizer.setNames("ID","First Name","Last Name",	"Email");
		lineMapper.setLineTokenizer(delimitedLineTokenizer);
		BeanWrapperFieldSetMapper<StudentCsv> beanWrapperFieldSetMapper=new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(StudentCsv.class);
		lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		itemReader.setLineMapper(lineMapper);
		itemReader.setLinesToSkip(1);
		return itemReader;
	}

}
