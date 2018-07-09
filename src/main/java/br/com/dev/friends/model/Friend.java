package br.com.dev.friends.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({ @NamedQuery(name = Friend.NQ_FIND_ALL, query = "select f from Friend f order by f.name") })
public class Friend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 132809330764351265L;

	public static final String NQ_FIND_ALL = "findAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Field 'name' is required")
	@Column(length = 100)
	private String name;

	private int age;

	@Column
	@Lob
	private byte[] image;

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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", name=" + name + ", age=" + age + ", image=" + Arrays.toString(image) + "]";
	}

}
