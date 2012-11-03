package de.timweb.Test3D.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
	public static final int PORT = 2004;
	public static final Server s = new Server();

	ServerSocket providerSocket;
	ArrayList<Client> clients = new ArrayList<Client>();

	/**
	 * Waits for new Clients and adds them to the ArrayList
	 * 
	 * @author Tim
	 * 
	 */
	private class ConnectionListener extends Thread {

		@Override
		public void run() {
			while (true) {
				try {
					// Waits for a new Client and add it to the list
					Socket connection = providerSocket.accept();

					clients.add(new Client(connection));

					System.out.println("Connection received from "
							+ connection.getInetAddress().getHostName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {

		try {
			// 1. creating a server socket
			providerSocket = new ServerSocket(PORT, 10);

			// Start the Listener for new Connections
			new ConnectionListener().start();

		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
		}
	}

	void sendMessage(String msg) {
		for (Client c : clients) {
			c.sendMessage(msg);
		}
		System.out.println("server>" + msg);
	}

	public void handleMessage(int clientNR, Object obj) {
		System.out.println("client["+clientNR+"]> " + obj);
	}

	public static void main(String[] args) {
		System.out.println("Server started...");

		Server.s.start();
	}


}
