package com.pool.configuration.batch.itemreader;

import java.util.List;


import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.stereotype.Component;

import com.pool.domin.WeWish;
@Component
public class UserItemReader<T> implements ItemReader<T> {

	private ItemReader<T> delegate;
	private List<T> readerItems;
	
	
 	public ItemReader<T> getDelegate() {
		return delegate;
	}


	public void setDelegate(ItemReader<T> delegate) {
		this.delegate = delegate;
	}


	public List<T> getReaderItems() {
		return readerItems;
	}


	public void setReaderItems(List<T> readerItems) {
		this.readerItems = readerItems;
	}


	@Override
	public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
 		if(delegate==null) {
 			delegate=new IteratorItemReader<>(readerItems);
 		}
		return delegate.read();
	}


}
