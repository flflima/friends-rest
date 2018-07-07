package br.com.dev.friends.resource;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.friends.dao.AddressDao;
import br.com.dev.friends.model.Address;

@RestController
@RequestMapping("addresses")
public class AddressController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8416187518009536265L;

	@Autowired
	private AddressDao addressDao;

	@GetMapping
	public ResponseEntity<?> getAllAddresses() {
		final List<Address> listAllAddresses = this.addressDao.listAllAddresses();
		return new ResponseEntity<>(listAllAddresses, HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<?> getAddressById(@PathVariable("id") final Long id) {
		final Address address = this.addressDao.findAddressById(id);
		return new ResponseEntity<>(address, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insertAddress(@RequestBody final Address address) {
		return new ResponseEntity<>(this.addressDao.insertAddress(address), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> updateAddress(@RequestBody Address address) {
		this.addressDao.findAddressById(address.getId());
		return new ResponseEntity<>(this.addressDao.updateAddress(address), HttpStatus.OK);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<?> deleteAddress(@PathVariable("id") final Long id) {
		this.addressDao.findAddressById(id);
		this.addressDao.deleteAddress(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
