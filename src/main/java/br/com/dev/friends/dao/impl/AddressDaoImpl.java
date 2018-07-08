package br.com.dev.friends.dao.impl;

import org.springframework.stereotype.Component;

import br.com.dev.friends.dao.AddressDao;
import br.com.dev.friends.model.Address;

@Component("addressDao")
public class AddressDaoImpl extends BaseDAOImpl<Address> implements AddressDao {

	// private Connection connection;

	public AddressDaoImpl() {
		// this.connection = ConnectionFactory.getConnection();

		super(Address.class);
	}

	// @Override
	// public Address insertAddress(final Address address) {
	// final String sql = "INSERT INTO ADDRESS (LOCATION, STREET_NAME, CITY,
	// FRIEND_ID) VALUES (?, ?, ?, ?)";
	//
	// try {
	// final PreparedStatement ps = this.connection.prepareStatement(sql,
	// Statement.RETURN_GENERATED_KEYS);
	//
	// ps.setString(1, address.getLocation());
	// ps.setString(2, address.getStreetName());
	// ps.setString(3, address.getCity());
	// ps.setLong(4, address.getFriend().getId());
	//
	// ps.execute();
	//
	// try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	// if (generatedKeys.next()) {
	// address.setId(generatedKeys.getLong(1));
	// }
	// }
	//
	// ps.close();
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// }
	//
	// return address;
	// }
	//
	// @Override
	// public List<Address> listAllAddresses() {
	// final String sql = "SELECT a.*, f.* FROM ADDRESS a INNER JOIN FRIEND f ON
	// f.id = a.friend_id ORDER BY ID";
	//
	// final List<Address> addresses = new ArrayList<Address>();
	//
	// try {
	// final PreparedStatement ps = this.connection.prepareStatement(sql);
	// final ResultSet rs = ps.executeQuery();
	//
	// while (rs.next()) {
	// final Address address = new Address();
	// address.setId(rs.getLong("id"));
	// address.setLocation(rs.getString("location"));
	// address.setStreetName(rs.getString("street_name"));
	// address.setCity(rs.getString("city"));
	//
	// final Friend friend = new Friend();
	// friend.setId(rs.getLong("friend_id"));
	// friend.setName(rs.getString("name"));
	// friend.setAge(rs.getInt("age"));
	//
	// address.setFriend(friend);
	//
	// addresses.add(address);
	// }
	//
	// rs.close();
	// ps.close();
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// }
	//
	// return addresses;
	// }
	//
	// @Override
	// public Address findAddressById(final long id) {
	// final String sql = "SELECT a.*, f.* FROM ADDRESS a INNER JOIN FRIEND f ON
	// f.id = a.friend_id WHERE a.ID = ?";
	//
	// final Address address = new Address();
	//
	// try {
	// final PreparedStatement ps = this.connection.prepareStatement(sql);
	//
	// ps.setLong(1, id);
	//
	// final ResultSet rs = ps.executeQuery();
	//
	// if (rs.next()) {
	// address.setId(rs.getLong("id"));
	// address.setLocation(rs.getString("location"));
	// address.setStreetName(rs.getString("street_name"));
	// address.setCity(rs.getString("city"));
	//
	// final Friend friend = new Friend();
	// friend.setId(rs.getLong("friend_id"));
	// friend.setName(rs.getString("name"));
	// friend.setAge(rs.getInt("age"));
	//
	// address.setFriend(friend);
	// } else {
	// throw new RuntimeException("Not found for " + id);
	// }
	//
	// ps.close();
	// rs.close();
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// }
	//
	// return address;
	// }
	//
	// @Override
	// public Address updateAddress(final Address address) {
	// final String sql = "UPDATE ADDRESS SET LOCATION = ?, STREET_NAME = ?, CITY =
	// ?, FRIEND_ID = ? WHERE ID = ?";
	//
	// try {
	// final PreparedStatement ps = this.connection.prepareStatement(sql);
	//
	// ps.setString(1, address.getLocation());
	// ps.setString(2, address.getStreetName());
	// ps.setString(3, address.getCity());
	// ps.setLong(4, address.getFriend().getId());
	// ps.setLong(5, address.getId());
	//
	// ps.executeUpdate();
	// ps.close();
	//
	// return address;
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// }
	// }
	//
	// @Override
	// public void deleteAddress(long id) {
	// final String sql = "DELETE FROM ADDRESS WHERE ID = ?";
	//
	// try {
	// final PreparedStatement ps = this.connection.prepareStatement(sql);
	//
	// ps.setLong(1, id);
	//
	// ps.execute();
	// ps.close();
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// }
	// }

}
