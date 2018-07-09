package br.com.dev.friends.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@NotNull
	private String name;

	private int age;

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

	@Override
	public String toString() {
		return "Friend [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
