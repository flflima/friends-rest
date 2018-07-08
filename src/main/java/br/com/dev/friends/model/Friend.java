package br.com.dev.friends.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@NamedQueries({ @NamedQuery(name = Friend.NQ_FIND_ALL, query = "select f from Friend f order by f.name"), })
public class Friend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 132809330764351265L;

	public static final String NQ_FIND_ALL = "findAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	private int age;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "friend", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("friend")
	private List<Address> addresses = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", name=" + name + ", age=" + age + ", addresses=" + addresses + "]";
	}

}
