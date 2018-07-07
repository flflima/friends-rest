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

import br.com.dev.friends.dao.FriendDao;
import br.com.dev.friends.model.Friend;

@RestController
@RequestMapping("friends")
public class FriendController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3798893022698285006L;

	@Autowired
	private FriendDao friendDao;

	@GetMapping
	public ResponseEntity<?> getAllFriends() {
		final List<Friend> listAllFriends = this.friendDao.listAllFriends();
		return new ResponseEntity<>(listAllFriends, HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<?> getFriendById(@PathVariable("id") final Long id) {
		final Friend friend = this.friendDao.findFriendById(id);
		return new ResponseEntity<>(friend, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insertFriend(@RequestBody final Friend friend) {
		return new ResponseEntity<>(this.friendDao.insertFriend(friend), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> updateFriend(@RequestBody final Friend friend) {
		this.friendDao.findFriendById(friend.getId());
		return new ResponseEntity<>(this.friendDao.updateFriend(friend), HttpStatus.OK);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<?> deleteFriend(@PathVariable("id") final Long id) {
		this.friendDao.findFriendById(id);
		this.friendDao.deleteFriend(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
