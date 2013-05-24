package com.ted;

import java.io.Console;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ExecuteThread implements Runnable {

	private AddressBook book;
	private Console console;

	public ExecuteThread() {
		try {
			book = new AddressBook("address.json");
		} catch (FileNotFoundException e) {
			System.err.println("Address book not found");
			System.exit(1);
		}
		console = System.console();
		if (console == null) {
			System.err.println("This program only run in console.");
			System.exit(1);
		}
	}

	@Override
	public void run() {
		while (true) {
			System.out.print("ab>");
			String line = console.readLine();

			if (line.isEmpty())
				continue;
			StringTokenizer tokens = new StringTokenizer(line, " ");
			// get command
			String command = tokens.nextToken();
			// parse arguments
			ArrayList<String> arguments = new ArrayList<String>();
			while (tokens.hasMoreTokens())
				arguments.add(tokens.nextToken());

			String key, value;
			// match command
			switch (command) {
			case "cd":
				book.cd(arguments.get(0));
				break;
			case "ls":
				book.ls();
				break;
			case "add":
				System.out.print("key: ");
				key = console.readLine();
				System.out.print("value: ");
				value = console.readLine();
				book.add(key, value);
				break;
			case "cat":
				book.cat(arguments.get(0));
				break;
			case "remove":
				System.out.print("please give the key: ");
				key = console.readLine();
				book.remove(key);
				break;
			case "exit":
				book.save();
				System.exit(0);
				break;
			default:
				System.out.print("Unrecognized command \"" + command + "\"");
			}
			System.out.println();
		}
	}
}
