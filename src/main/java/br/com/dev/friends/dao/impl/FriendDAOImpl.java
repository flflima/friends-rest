package br.com.dev.friends.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.dev.friends.dao.FriendDAO;
import br.com.dev.friends.model.Friend;

@Component("friendDao")
public class FriendDAOImpl extends BaseDAOImpl<Friend> implements FriendDAO {

	public FriendDAOImpl() {
		super(Friend.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friend> findAll() {
		return this.entityManager.createNamedQuery(Friend.NQ_FIND_ALL).getResultList();
	}
}
