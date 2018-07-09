package br.com.dev.friends.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
		@NamedQuery(name = Address.NQ_FIND_ADDRESS_BY_ID, query = "select a from Address a where a.friend.id = :id order by a.streetName") })
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6095417136454034512L;

	public static final String NQ_FIND_ADDRESS_BY_ID = "findAddressById";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String location;

	@NotNull
	@Column(name = "street_name")
	private String streetName;

	@NotNull
	private String city;

	@ManyToOne
	@JoinColumn(name = "friend_id", referencedColumnName = "id")
	private Friend friend;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", location=" + location + ", streetName=" + streetName + ", city=" + city
				+ ", friend=" + friend + "]";
	}

}
