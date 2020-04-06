package br.com.dev.friends.service;

import br.com.dev.friends.dao.AddressDAO;
import br.com.dev.friends.dao.FriendDAO;
import br.com.dev.friends.exception.ResourceNotFoundException;
import br.com.dev.friends.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public final class FriendServiceTest {

    @Test
    void givenAnExistentIdMustReturnAFriend() {
        final FriendDAO friendDAO = mock(FriendDAO.class);
        when(friendDAO.findById(any())).thenReturn(new Friend(1L, "John Doe", 20));

        final FriendService friendService = new FriendService(friendDAO, mock(AddressDAO.class));
        try {
            final Friend friend = friendService.findFriendById(1L);

            Assertions.assertThat(friend.getName()).isEqualTo("John Doe");
            Assertions.assertThat(friend.getAge()).isEqualTo(20);

            verify(friendDAO).findById(1L);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
    }
}