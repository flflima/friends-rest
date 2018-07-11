package br.com.dev.friends.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.friends.exception.ResourceNotFoundException;
import br.com.dev.friends.model.Address;
import br.com.dev.friends.service.AddressService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("addresses")
public class AddressController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8416187518009536265L;

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	private AddressService addressService;

	@GetMapping
	public ResponseEntity<?> getAllAddresses() {
		LOGGER.info("Finding all addresses");

		return new ResponseEntity<>(this.addressService.findAllAddresses(), HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<?> getAddressById(@PathVariable("id") final Long id) throws ResourceNotFoundException {
		LOGGER.info("Finding address for id [{}]", id);

		return new ResponseEntity<>(this.addressService.findAddressById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insertAddress(@RequestBody final Address address) {
		LOGGER.info("Saving an address: [{}]", address);

		return new ResponseEntity<>(this.addressService.saveAddress(address), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> updateAddress(@RequestBody final Address address) throws ResourceNotFoundException {
		LOGGER.info("Updating an address: [{}]", address);

		return new ResponseEntity<>(this.addressService.updateAddress(address), HttpStatus.OK);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<?> deleteAddress(@PathVariable("id") final Long id) throws ResourceNotFoundException {
		LOGGER.info("Deleting address for id [{}]", id);

		this.addressService.deleteAddressById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
