package com.encore;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class AddressBookTest {

	private AddressBook book;
	
	@Before
	public void setUp() throws FileNotFoundException {
		book = new AddressBook("address.json");
	}
	
	@Test
	public void testRemove() {
		book.cd("entries");
		assertEquals(2, book.getEntryCount());
		book.remove("lilei");
		assertEquals(1, book.getEntryCount());
	}

}
