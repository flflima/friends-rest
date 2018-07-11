package br.com.dev.friends.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dev.friends.dao.AddressDao;
import br.com.dev.friends.exception.ResourceNotFoundException;
import br.com.dev.friends.model.Address;

@Service
public class AddressService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7080444909693034815L;

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

	@Autowired
	private AddressDao addressDao;

	public List<Address> findAllAddresses() {
		final List<Address> addresses = this.addressDao.findAll();

		if (addresses.size() == 0) {
			LOGGER.debug("No addresses found in database");
		}

		return addresses;
	}

	public Address findAddressById(final Long id) throws ResourceNotFoundException {
		final Address address = this.addressDao.findById(id);

		if (address == null) {
			LOGGER.debug("Address not found for id [{}]", id);
			throw new ResourceNotFoundException("Address not found for id " + id);
		}

		return address;
	}

	@Transactional(rollbackFor = Exception.class)
	public Address saveAddress(final Address address) {
		return this.addressDao.save(address);
	}

	@Transactional(rollbackFor = Exception.class)
	public Address updateAddress(final Address address) throws ResourceNotFoundException {
		final Address entity = this.addressDao.findById(address.getId());

		if (entity == null) {
			LOGGER.debug("Address not found [{}]", address);
			throw new ResourceNotFoundException("Friend not found: " + entity);
		}

		return this.addressDao.update(address);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteAddressById(final Long id) throws ResourceNotFoundException {
		final Address entity = this.addressDao.findById(id);

		if (entity == null) {
			LOGGER.debug("Address not found for id [{}]", id);
			throw new ResourceNotFoundException("Address not found for id: " + id);
		}

		this.addressDao.delete(entity);

	}
}
