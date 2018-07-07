package br.com.dev.friends.dao;

import java.util.List;

import br.com.dev.friends.model.Friend;

public interface FriendDao {

	Friend insertFriend(final Friend friend);
	
	List<Friend> listAllFriends();
	
	Friend findFriendById(final long id);
	
	Friend updateFriend(final Friend friend);
	
	void deleteFriend(final long id);
}
