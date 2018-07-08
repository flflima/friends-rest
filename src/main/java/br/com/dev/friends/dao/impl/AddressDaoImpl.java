package br.com.dev.friends.dao.impl;

import org.springframework.stereotype.Component;

import br.com.dev.friends.dao.AddressDao;
import br.com.dev.friends.model.Address;

@Component("addressDao")
public class AddressDaoImpl extends BaseDAOImpl<Address> implements AddressDao {

	public AddressDaoImpl() {
		super(Address.class);
	}

}
