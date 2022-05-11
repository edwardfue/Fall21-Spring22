package demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
public class Server {
	
	private static final int DEAFAULT_SERVER_PRT_NUMBER = 0000;
	
	private static final int UNABLE_TO_OPEN_SERVER_SOCKET = -1;
	private static final int UNABLE_TO_ACCEPT_CONNECTION = -2;
	private static final int UNABLE_TO_OBTAIN_IP_ADDRESS_OF_HOST= -3;


	private ServerSocket serverSocket;
	private int portNumber;
	
	public Server() {
		try {
			this.serverSocket = new ServerSocket(DEAFAULT_SERVER_PRT_NUMBER);
			this.portNumber = DEAFAULT_SERVER_PRT_NUMBER;
		}
		catch (IOException e){
			e.printStackTrace();
			System.exit(UNABLE_TO_OPEN_SERVER_SOCKET);
		}
	}
	
	public Server(int portNumber) {
		try {
			this.serverSocket = new ServerSocket(portNumber);
			this.portNumber = portNumber;
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(UNABLE_TO_OPEN_SERVER_SOCKET);
		}
	}
	
	public void acceptConnections() {
		Socket socket = null;
		
		while(true) {
			try {
				socket = serverSocket.accept();
				System.out.println("Acccept new connection");
			}catch(IOException e) {
				e.printStackTrace();
				System.exit(UNABLE_TO_ACCEPT_CONNECTION);
			}
			new ServerThread(socket).run();
		}
	}
	
	public int getPortNumber() {
		return portNumber;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InetAddress myIPAddress;
		
		try {
			myIPAddress = InetAddress.getLocalHost();
			System.out.println("My Sever is " + myIPAddress.getHostAddress());
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println();
			System.exit(UNABLE_TO_OBTAIN_IP_ADDRESS_OF_HOST);
		}
		
		Server server = new Server();
		server.acceptConnections();

	}

}
