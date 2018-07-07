package br.com.dev.friends.model;

import java.io.Serializable;

public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6095417136454034512L;

	private long id;
	private String location;
	private String streetName;
	private String city;
	private Friend friend;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
