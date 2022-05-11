package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	private static final int UNABLE_TO_OPEN_INPUT_STREAM = -1;
	private static final int UNABLE_TO_OPEN_OUTPUT_STREAM = -2;
	private static final int UNABLE_TO_READ_FROM_BUFFER_READER = -3;
	private static final int UNABLE_TO_CLOSE_SOCKET = -4;

	private BufferedReader reader;
	private InputStream input;
	private OutputStream output;
	private PrintWriter writer;

	private Socket socket = null;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		super.run();
		
		try {
			input = socket.getInputStream();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(UNABLE_TO_OPEN_INPUT_STREAM);
		}
		
		this.reader = new BufferedReader(new InputStreamReader(input));
		
		try {
			output = socket.getOutputStream();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(UNABLE_TO_OPEN_OUTPUT_STREAM);
		}
		
		this.writer = new PrintWriter(this.output, true);
		
		String message = "";
		
		do {
			try {
				message = this.reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(UNABLE_TO_READ_FROM_BUFFER_READER);
			}
			
			if(!message.equalsIgnoreCase("end")) {
				String response = message.toUpperCase() + "\tLength = "+ message.length();
				this.writer.println(response);
			}
			
		}while (!message.equalsIgnoreCase("end"));
		
		System.out.println("client connection Ended");
		
		try {
			this.socket.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(UNABLE_TO_CLOSE_SOCKET);
		}
	}
}
