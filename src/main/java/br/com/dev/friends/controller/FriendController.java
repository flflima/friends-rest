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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.dev.friends.exception.FileImageOperationException;
import br.com.dev.friends.exception.ResourceNotFoundException;
import br.com.dev.friends.model.Friend;
import br.com.dev.friends.service.FriendService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("friends")
public class FriendController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3798893022698285006L;

	private static final Logger LOGGER = LoggerFactory.getLogger(FriendController.class);

	@Autowired
	private FriendService friendService;

	@GetMapping
	public ResponseEntity<?> getAllFriends() {
		LOGGER.info("Finding all friends");

		return new ResponseEntity<>(this.friendService.findAllFriends(), HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<?> getFriendById(@PathVariable("id") final Long id) throws ResourceNotFoundException {
		LOGGER.info("Finding friend for id [{}]", id);

		return new ResponseEntity<>(this.friendService.findFriendById(id), HttpStatus.OK);
	}

	@GetMapping(path = "{id}/addresses")
	public ResponseEntity<?> getAddressesByFriendById(@PathVariable("id") final Long id) {
		LOGGER.info("Finding all addresses for a friend with id [{}]", id);

		return new ResponseEntity<>(this.friendService.findAddressesByFriendId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insertFriend(@RequestBody final Friend friend) {
		LOGGER.info("Saving a friend: [{}]", friend);

		return new ResponseEntity<>(this.friendService.saveFriend(friend), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> updateFriend(@RequestBody final Friend friend) throws ResourceNotFoundException {
		LOGGER.info("Updating a friend: [{}]", friend);

		return new ResponseEntity<>(this.friendService.updateFriend(friend), HttpStatus.OK);
	}

	@PutMapping(path = "{id}/image")
	public ResponseEntity<?> saveImage(@PathVariable("id") final Long id,
			@RequestParam("image") final MultipartFile file)
			throws FileImageOperationException, ResourceNotFoundException {
		LOGGER.info("Saving image [{}] for friend with id [{}]", file.getOriginalFilename(), id);

		return new ResponseEntity<>(this.friendService.saveImageOnDatabase(id, file), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<?> deleteFriend(@PathVariable("id") final Long id) throws ResourceNotFoundException {
		LOGGER.info("Deleting friend for id [{}]", id);

		this.friendService.deleteFriendById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
