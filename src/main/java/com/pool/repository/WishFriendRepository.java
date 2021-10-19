package com.pool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pool.domin.WishFriend;

@Repository
public interface WishFriendRepository extends JpaRepository<WishFriend, Long>{

}
