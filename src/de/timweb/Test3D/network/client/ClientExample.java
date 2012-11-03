package de.timweb.Test3D.network.client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientExample {
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;

	public ClientExample() {
	}

	void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
		
		try {
			// 1. creating a socket to connect to the server
			requestSocket = new Socket("localhost", 2004);
			System.out.println("Connected to localhost in port 2004");
			
			// 2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			
			// 3: Communicating with the server
			do {
				// message = (String) in.readObject();
//				System.out.println("server>" + message);
				sendMessage(reader.readLine());
			} while (true);
			
		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// 4: Closing connection
			try {
				in.close();
				out.close();
				requestSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	void sendMessage(String msg) {
		try {
			ArrayList<String> strings = new ArrayList<String>();
			strings.add("Sting0");
			strings.add("Sting1");
			strings.add("Sting2");
			out.writeObject(strings);
			out.flush();
			System.out.println("client>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ClientExample client = new ClientExample();
		client.run();
	}
}