package com.ted.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import com.ted.AddressBook;

public class AddressBookTest {

	private AddressBook book;

	@Before
	public void setUp() throws FileNotFoundException {
		book = new AddressBook("store/address.json");
	}

	@Test
	/**
	 * we must select an entry before adding a new address
	 */
	public void testSelectBeforeAdd() {
		int count = book.getEntryCount();
		book.add("ted", "{}");
		assertEquals(count, book.getEntryCount());
	}

	@Test
	public void testAdd() {
		book.cd("entries");
		int count = book.getEntryCount();
		book.add("ted", "{}");
		assertEquals(count + 1, book.getEntryCount());
	}

	/**
	 * we must select an entry before remove an address
	 */
	@Test
	public void testSelectBeforeRemove() {
		int count = book.getEntryCount();
		book.remove("lilei");
		assertEquals(count, book.getEntryCount());
	}

	@Test
	public void testRemove() {
		book.cd("entries");
		int count = book.getEntryCount();
		book.remove("lilei");
		assertEquals(count - 1, book.getEntryCount());
	}

}
