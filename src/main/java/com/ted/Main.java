package com.ted;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		ExecuteThread thread = new ExecuteThread();
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.submit(thread);
	}
}