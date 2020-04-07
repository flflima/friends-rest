package br.com.dev.friends.service;

import br.com.dev.friends.dao.AddressDAO;
import br.com.dev.friends.dao.FriendDAO;
import br.com.dev.friends.exception.ResourceNotFoundException;
import br.com.dev.friends.model.Friend;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public final class FriendServiceTest {

    private List<Friend> buildFriendsList() {
        final Friend f1 = new Friend(1L, "John Doe", 20);
        final Friend f2 = new Friend(2L, "Jane Doe", 30);

        final List<Friend> friends = new ArrayList<>();
        friends.add(f1);
        friends.add(f2);

        return friends;
    }

    private List<Friend> buildEmptyFriendsList() {
        return new ArrayList<>();
    }

    @Test
    void givenASearchWhenTheDBContainsRegistersMustReturnAPopulatedList() {
        final FriendDAO friendDAO = mock(FriendDAO.class);
        when(friendDAO.findAll()).thenReturn(buildFriendsList());

        final FriendService friendService = new FriendService(friendDAO, mock(AddressDAO.class));

        final List<Friend> friends = friendService.findAllFriends();

        assertThat(friends).size().isEqualTo(2);
        assertThat(friends.get(0).getName()).isEqualTo("John Doe");
        assertThat(friends.get(1).getName()).isEqualTo("Jane Doe");

        verify(friendDAO).findAll();
    }

    @Test
    void givenASearchWhenTheDBDoesNotContainsRegistersMustReturnAnEmptyList() {
        final FriendDAO friendDAO = mock(FriendDAO.class);
        when(friendDAO.findAll()).thenReturn(buildEmptyFriendsList());

        final FriendService friendService = new FriendService(friendDAO, mock(AddressDAO.class));

        final List<Friend> friends = friendService.findAllFriends();

        assertThat(friends).size().isEqualTo(0);

        verify(friendDAO).findAll();
    }

    @Test
    void givenAnExistentIdMustReturnAFriend() {
        final FriendDAO friendDAO = mock(FriendDAO.class);
        when(friendDAO.findById(any())).thenReturn(new Friend(1L, "John Doe", 20));

        final FriendService friendService = new FriendService(friendDAO, mock(AddressDAO.class));
        try {
            final Friend friend = friendService.findFriendById(1L);

            assertThat(friend.getName()).isEqualTo("John Doe");
            assertThat(friend.getAge()).isEqualTo(20);

            verify(friendDAO).findById(1L);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenANonExistentIdMustThrowAnException() {
        final FriendDAO friendDAO = mock(FriendDAO.class);
        when(friendDAO.findById(any())).thenReturn(null);

        final FriendService friendService = new FriendService(friendDAO, mock(AddressDAO.class));
        assertThatThrownBy(() -> friendService.findFriendById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Friend not found for id 1");
    }
}