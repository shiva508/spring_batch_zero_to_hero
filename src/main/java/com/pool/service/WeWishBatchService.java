package com.pool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pool.configuration.batch.WeWishBatchConfiguration;
import com.pool.configuration.batch.itemreader.UserItemReader;
import com.pool.domin.WeWish;

@Service
public class WeWishBatchService {
	@Autowired
	private WeWishBatchConfiguration weWishBatchConfiguration;

	@Autowired
	private UserItemReader<WeWish> userItemReader;

	public void testBatch(List<WeWish> weWishs) {
		userItemReader.setReaderItems(weWishs);
		weWishBatchConfiguration.setReader(userItemReader);
	}
}
