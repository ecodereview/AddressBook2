package com.encore;

import java.io.Console;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		AddressBook book = new AddressBook("address.json");
		Console console = System.console();
		if (console == null) {
			System.err.println("This program only run in console.");
			System.exit(1);
		}
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

			// match command
			if (command.equals("cd")) {
				book.cd(arguments.get(0));
			} else if (command.equals("ls")) {
				System.out.print("ab>");
				book.ls();
			} else if (command.equals("add")) {
				System.out.print("key: ");
				String key = console.readLine();
				System.out.print("value: ");
				String value = console.readLine();
				book.add(key, value);
			} else if (command.equals("cat")) {
				System.out.print("ab>");
				book.cat(arguments.get(0));
			} else if (command.equals("remove")) {
				System.out.print("please give the key: ");
				String key = console.readLine();
				book.remove(key);
			} else if (command.equals("exit")) {
				System.exit(0);
			} else {
				System.out.println("Unrecognized command \"" + command + "\"");
			}
		}
	}
}