package br.com.dev.friends.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.dev.friends.dao.AddressDAO;
import br.com.dev.friends.dao.FriendDAO;
import br.com.dev.friends.exception.FileImageOperationException;
import br.com.dev.friends.exception.ResourceNotFoundException;
import br.com.dev.friends.model.Address;
import br.com.dev.friends.model.Friend;

@Service
public class FriendService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1333728249223243451L;

	private static final Logger LOGGER = LoggerFactory.getLogger(FriendService.class);

	@Autowired
	public FriendService(FriendDAO friendDao, AddressDAO addressDao) {
		this.friendDao = friendDao;
		this.addressDao = addressDao;
	}

	private FriendDAO friendDao;
	private AddressDAO addressDao;

	public List<Friend> findAllFriends() {
		final List<Friend> friends = this.friendDao.findAll();

		if (friends.size() == 0) {
			LOGGER.debug("No friends found in database");
		}

		return friends;
	}

	public Friend findFriendById(final Long id) throws ResourceNotFoundException {
		final Friend friend = this.friendDao.findById(id);

		if (friend == null) {
			LOGGER.debug("Friend not found for id [{}]", id);
			throw new ResourceNotFoundException("Friend not found for id ".concat(id.toString()));
		}

		return friend;
	}

	@Transactional(rollbackFor = Exception.class)
	public Friend saveFriend(final Friend friend) {
		return this.friendDao.save(friend);
	}

	@Transactional(rollbackFor = Exception.class)
	public Friend updateFriend(final Friend friend) throws ResourceNotFoundException {
		final Friend oldFriend = this.friendDao.findById(friend.getId());

		if (oldFriend == null) {
			LOGGER.debug("Friend not found [{}]", friend);
			throw new ResourceNotFoundException("Friend not found: " + friend);
		}

		friend.setId(oldFriend.getId());
		return this.friendDao.update(friend);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteFriendById(final Long id) throws ResourceNotFoundException {
		// remove all associated addresses, if exist any
		final List<Address> addresses = this.addressDao.findAddressesByFriendById(id);

		for (Address address : addresses) {
			LOGGER.info("Deleting address [{}]", address);
			this.addressDao.delete(address);
		}

		// get friend entity
		final Friend friendEntity = this.friendDao.findById(id);

		if (friendEntity == null) {
			LOGGER.debug("Friend not found for id [{}]", id);
			throw new ResourceNotFoundException("Friend not found for id: " + id);
		}

		this.friendDao.delete(friendEntity);
	}

	public List<Address> findAddressesByFriendId(final Long id) {
		// get all addresses associated to this friend id
		final List<Address> addresses = this.addressDao.findAddressesByFriendById(id);

		if (addresses.size() == 0) {
			LOGGER.debug("No address found for friend with id [{}]", id);
		}

		return addresses;
	}

	public Friend saveImageOnDatabase(final Long id, final MultipartFile file)
			throws FileImageOperationException, ResourceNotFoundException {
		try {
			// get friend entity
			final Friend friendEntity = this.friendDao.findById(id);

			if (friendEntity == null) {
				LOGGER.debug("Friend not found for id [{}]", id);
				throw new ResourceNotFoundException("Friend not found for id: " + id);
			}

			// set uploaded image
			friendEntity.setImage(file.getBytes());
			return this.friendDao.update(friendEntity);
		} catch (final IOException e) {
			LOGGER.error("Error on processing file [{}]: [{}]", file.getOriginalFilename(), e.getMessage());
			throw new FileImageOperationException(e.getMessage());
		}
	}

}
