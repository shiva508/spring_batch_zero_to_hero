package com.pool.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pool.domin.WishFriend;
import com.pool.modal.CommonResponse;
import com.pool.repository.WishFriendRepository;

@Service
public class WishFriendServiceImpl implements WishFriendService {
	@Autowired
	private WishFriendRepository wishFriendRepository;

	@Override
	public WishFriend createWishFriend(WishFriend wishFriend) {
		return wishFriendRepository.save(wishFriend);
	}

	@Override
	public WishFriend updateWishFriend(WishFriend wishFriend) {
		return wishFriendRepository.save(wishFriend);
	}

	@Override
	public WishFriend getWishFriendNameById(Long wishFriendId) {
		Optional<WishFriend>optionalWishFriend=wishFriendRepository.findById(wishFriendId);
		return null;
	}

	@Override
	public List<WishFriend> getAllWishFriends() {
		return wishFriendRepository.findAll();
	}

	@Override
	public CommonResponse deleteWishFriendById(Long wishFriendId) {
		Optional<WishFriend>optionalWishFriend=wishFriendRepository.findById(wishFriendId);
		return null;
	}

}
