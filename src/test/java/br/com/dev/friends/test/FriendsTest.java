package br.com.dev.friends.test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class FriendsTest {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@Test
	public void testFriendByIdisFound() {
		get("/friends-rest/friends/4")
		.then()
		.body("id", equalTo(4))
		.body("name", equalTo("Bruce Wayne"));
	}
	
	//TODO: check how to treat when error 500 returns
	@Test
	public void testFriendNotFound() {
		get("/friends-rest/friends/100");
//		fail("Falha. Uma exceção deve ser lançada!");
	}

}
