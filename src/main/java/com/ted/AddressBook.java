package com.ted;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

public class AddressBook {

	private JsonObject object;
	private JsonObject activeEntry;
	private Set activeAddressList;
	private JsonParser parser;
	private File file;

	public AddressBook(String fileName) throws FileNotFoundException {
		file = new File(fileName);
		JsonReader reader = new JsonReader(new FileReader(file));
		parser = new JsonParser();
		object = (JsonObject) parser.parse(reader);

		activeAddressList = null;
		activeEntry = null;
	}

	public int getEntryCount() {
		if (activeAddressList == null)
			return 0;
		return activeAddressList.size();
	}

	/**
	 * Add a new address to selected entry.
	 * If we didn't select an entry, then no address will be added
	 * @param key the person's name which will be added
	 * @param value the detail address information, in raw json string format
	 */
	public void add(String key, String value) {
		if (activeEntry == null) {
			System.err.println("select an entry first");
			return;
		}
		try {
			JsonElement jsonValue = parser.parse(value);
			activeEntry.add(key, jsonValue);
		} catch (JsonSyntaxException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Inspect a specific address entry
	 * @param entryName the person's name who have the address
	 */
	public void cat(String entryName) {
		Iterator iterator = activeAddressList.iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			String name = (String) entry.getKey();
			if (name.equals(entryName)) {
				JsonElement value = (JsonElement) entry.getValue();
				System.out.print("\"" + name + "\" :" + value);
				return;
			}
		}
		System.out.print("no such entry");
	}

	/**
	 * Given the person's name, remove his/her address in entry.
	 * If we didn't select an entry, then no address will be removed
	 * @param key the person's name whose address will be removed.
	 */
	public void remove(String key) {
		if (activeAddressList == null) {
			System.err.println("select an entry first");
			return;
		}
		Iterator iterator = activeAddressList.iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			String name = (String) entry.getKey();
			if (name.equals(key)) {
				activeAddressList.remove(entry);
				System.out.print(name + " was deleted from JSON");
				return;
			}
		}
		System.out.print("no such entry");
	}

	/**
	 * select an entry, entry contains a list of (person name, address) pair
	 * @param entriesName
	 */
	public void cd(String entriesName) {
		if (!object.has(entriesName)) {
			System.out.print("no such entries");
		} else {
			activeEntry = object.getAsJsonObject(entriesName);
			activeAddressList = activeEntry.entrySet();
		}
	}

	/**
	 * If we didn't select an entry, the list of entries will be printed, 
	 * otherwise the list of addresses in selected entry will be printed.
	 */
	public void ls() {
		if (activeEntry == null) {
			Set entries = object.entrySet();
			Iterator entryIterator = entries.iterator();
			while (entryIterator.hasNext()) {
				Entry entry = (Entry) entryIterator.next();
				System.out.print(entry.getKey() + " ");
			}
		} else {
			Iterator iterator = activeAddressList.iterator();
			while (iterator.hasNext()) {
				Entry entry = (Entry) iterator.next();
				System.out.print(entry.getKey() + " ");
			}
		}
	}

	/**
	 * save the address book to file
	 */
	public void save() {
		FileWriter writer;
		try {
			writer = new FileWriter(this.file);
			writer.write(object.toString());
			writer.close();
		} catch (IOException e) {
			System.err.println("address book not saved");
		}
	}
}
