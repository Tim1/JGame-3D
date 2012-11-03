package de.timweb.Test3D.network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 
 * @author Tim
 * 
 */
public class IncomingStreamDispatcher extends Thread {
	private static int counter = 0;
	private int clientNR = counter++;
	private Socket client;
	private BufferedReader bw;
	private Server server;

	public IncomingStreamDispatcher(Socket client, Server server) {
		this.client = client;
		this.server = server;
	}

	@Override
	public void run() {
		try {
			bw = new BufferedReader(new InputStreamReader(
					client.getInputStream()));

			try {
				while (true) {
					String line = bw.readLine();
					server.handleMessage(clientNR, line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}