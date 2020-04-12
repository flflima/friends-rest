package br.com.dev.friends.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.dev.friends.dao.AddressDAO;
import br.com.dev.friends.model.Address;

@Component("addressDao")
public class AddressDAOImpl extends BaseDAOImpl<Address> implements AddressDAO {

	public AddressDAOImpl() {
		super(Address.class);
	}

	@SuppressWarnings("unchecked")
	public List<Address> findAddressesByFriendById(final Long id) {
		return this.entityManager.createNamedQuery(Address.NQ_FIND_ADDRESS_BY_ID).setParameter("id", id).getResultList();
	}

}
