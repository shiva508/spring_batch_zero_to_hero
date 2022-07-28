package com.pool.configuration.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.pool.modal.StudentCsv;

@Service
public class StudentFlatFileItemReader {
	public FlatFileItemReader<StudentCsv> flatFileItemReader(String csvpath){
		FlatFileItemReader<StudentCsv> itemReader=new FlatFileItemReader<>();
		Resource resource = new FileSystemResource(csvpath);
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
}
