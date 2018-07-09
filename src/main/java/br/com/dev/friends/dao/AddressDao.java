package br.com.dev.friends.dao;

import java.util.List;

import br.com.dev.friends.model.Address;

public interface AddressDao extends BaseDAO<Address> {

	List<Address> findAddressesByFriendById(Long id);
	
}
