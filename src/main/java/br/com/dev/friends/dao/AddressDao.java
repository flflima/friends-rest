package br.com.dev.friends.dao;

import java.util.List;

import br.com.dev.friends.model.Address;

public interface AddressDao {
	
	Address insertAddress(final Address address);

	List<Address> listAllAddresses();

	Address findAddressById(final long id);

	Address updateAddress(final Address address);

	void deleteAddress(final long id);
}
