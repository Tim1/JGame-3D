package de.timweb.Test3D.network.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Represents a Client for the Server
 * 
 * @author Tim
 * 
 */
class Client {
	private static int clientCounter = 0;

	private int clientNR = clientCounter++;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	private class Incoming extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					Object obj = in.readObject();
					Server.s.handleMessage(clientNR, obj);
				}
			} catch (IOException e) {
				System.out.println("Client["+clientNR+"] quits");
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public Client(Socket socket) {
		this.socket = socket;

		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new Incoming().start();
	}

	public void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
