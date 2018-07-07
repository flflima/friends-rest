package br.com.dev.friends.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.dev.friends.dao.FriendDao;
import br.com.dev.friends.jdbc.factory.ConnectionFactory;
import br.com.dev.friends.model.Friend;

@Component("friendDao")
public class FriendDaoImpl implements FriendDao {

	private Connection connection;

	public FriendDaoImpl() {
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public Friend insertFriend(final Friend friend) {
		final String sql = "INSERT INTO FRIEND (NAME, AGE) VALUES (?, ?)";

		try {
			final PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, friend.getName());
			ps.setInt(2, friend.getAge());

			ps.execute();

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					friend.setId(generatedKeys.getLong(1));
				}
			}

			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return friend;
	}

	@Override
	public List<Friend> listAllFriends() {
		final String sql = "SELECT * FROM FRIEND f ORDER BY NAME";

		final List<Friend> friends = new ArrayList<Friend>();

		try {
			final PreparedStatement ps = this.connection.prepareStatement(sql);
			final ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				final Friend friend = new Friend();
				friend.setId(rs.getLong("id"));
				friend.setName(rs.getString("name"));
				friend.setAge(rs.getInt("age"));

				friends.add(friend);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return friends;
	}

	@Override
	public Friend findFriendById(final long id) {
		final String sql = "SELECT * FROM FRIEND WHERE ID = ?";

		try {
			final Friend friend = new Friend();
			final PreparedStatement ps = this.connection.prepareStatement(sql);

			ps.setLong(1, id);

			final ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				friend.setId(rs.getLong("id"));
				friend.setName(rs.getString("name"));
				friend.setAge(rs.getInt("age"));
			} else {
				throw new RuntimeException("Not found for " + id);
			}

			ps.close();
			rs.close();
			
			return friend;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Friend updateFriend(final Friend friend) {
		final String sql = "UPDATE FRIEND SET NAME = ?, AGE = ? WHERE ID = ?";

		try {
			final PreparedStatement ps = this.connection.prepareStatement(sql);

			ps.setString(1, friend.getName());
			ps.setInt(2, friend.getAge());
			ps.setLong(3, friend.getId());

			ps.executeUpdate();
			ps.close();
			
			return friend;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteFriend(long id) {
		final String sql = "DELETE FROM FRIEND WHERE ID = ?";

		try {
			final PreparedStatement ps = this.connection.prepareStatement(sql);

			ps.setLong(1, id);

			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
