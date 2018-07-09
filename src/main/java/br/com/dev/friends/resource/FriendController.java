package br.com.dev.friends.resource;

import java.io.Serializable;
import java.util.List;

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

import br.com.dev.friends.dao.AddressDao;
import br.com.dev.friends.dao.FriendDao;
import br.com.dev.friends.exception.ResourceNotFoundException;
import br.com.dev.friends.model.Address;
import br.com.dev.friends.model.Friend;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("friends")
public class FriendController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3798893022698285006L;

	@Autowired
	private FriendDao friendDao;

	@Autowired
	private AddressDao addressDao;

	@GetMapping
	public ResponseEntity<?> getAllFriends() {
		return new ResponseEntity<>(this.friendDao.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<?> getFriendById(@PathVariable("id") final Long id) {
		return new ResponseEntity<>(this.friendDao.findById(id), HttpStatus.OK);
	}

	@GetMapping(path = "{id}/addresses")
	public ResponseEntity<?> getAddressesByFriendById(@PathVariable("id") final Long id) {
		// get all addresses associated to this friend id
		final List<Address> addresses = this.addressDao.findAddressesByFriendById(id);
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insertFriend(@RequestBody final Friend friend) {
		return new ResponseEntity<>(this.friendDao.save(friend), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> updateFriend(@RequestBody final Friend friend) throws ResourceNotFoundException {
		// verify if exists in db
		final Friend oldFriend = this.friendDao.findById(friend.getId());

		if (oldFriend == null) {
			throw new ResourceNotFoundException("Friend not found: " + friend);
		}

		friend.setId(oldFriend.getId());
		return new ResponseEntity<>(this.friendDao.update(friend), HttpStatus.OK);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<?> deleteFriend(@PathVariable("id") final Long id) {
		// remove all associated addresses, if exist any
		final List<Address> addresses = this.addressDao.findAddressesByFriendById(id);

		for (Address address : addresses) {
			this.addressDao.delete(address);
		}

		// get friend entity
		final Friend friendEntity = this.friendDao.findById(id);

		if (friendEntity == null) {
			throw new RuntimeException("Friend not found for id: " + id);
		}

		this.friendDao.delete(friendEntity);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
