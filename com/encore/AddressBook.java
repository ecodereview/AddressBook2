package com.encore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class AddressBook {

	private JsonObject object;
	private Set<Entry<String, JsonElement>> addresses;
	private JsonObject entries;
	private Gson gson;

	public AddressBook(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		JsonReader reader = new JsonReader(new FileReader(file));
		gson = new Gson();
		object = gson.fromJson(reader, JsonObject.class);
	}
	
	public int getEntryCount() {
		return addresses.size();
	}

	public void add(String key, String value) {
		JsonElement jsonValue = gson.fromJson(value, JsonElement.class);
	}
	
	public void cat(String entryName) {
		Iterator iterator = addresses.iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			String name = (String) entry.getKey();
			if (name.equals(entryName)) {
				JsonElement value = (JsonElement) entry.getValue();
				System.out.println("\"" + name + "\" :" + value);
				return;
			}
		}
		System.out.println("no such entry");
	}
	
	public void remove(String key) {
		Iterator iterator = addresses.iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			String name = (String) entry.getKey();
			if (name.equals(key)) {
				addresses.remove(entry);
				System.out.println(name + " was deleted from JSON");
				return;
			}
		}
		System.out.println("no such entry");
	}

	public void cd(String entriesName) {
		if (!object.has(entriesName)) {
			System.out.println("no such entries");
		} else {
			entries = object.getAsJsonObject(entriesName);
			addresses = entries.entrySet();
		}
	}

	public void ls() {
		if (addresses == null) {
			System.out.println("select an entry first");
			return;
		}
		Iterator iterator = addresses.iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			System.out.print(entry.getKey() + " ");
		}
		System.out.println();
	}
}
