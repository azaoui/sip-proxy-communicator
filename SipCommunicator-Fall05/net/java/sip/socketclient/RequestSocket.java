package net.java.sip.socketclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import net.java.sip.communicator.common.Console;
import net.java.sip.communicator.common.NetworkAddressManager;
import net.java.sip.communicator.common.Utils;

public class RequestSocket {
	public static char BLOCK = '1';
	public static char UNBLOCK = '2';
	public static char FORWARD = '3';
	public static char UNFORWARD = '4';
	public static char BILL = '5';

	protected String registrarAddress = null;
	PrintWriter out;
	BufferedReader in;
	Socket socket;
	int port = 4444;

	protected static Console console = Console.getConsole(RequestSocket.class);

	public void listenSocket() {
		// Create socket connection
		try {
			registrarAddress = Utils.getProperty("javax.sip.IP_ADDRESS");

			socket = new Socket(registrarAddress, port);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: " + registrarAddress);
			System.exit(1);
		} catch (IOException e) {
			System.out.println("No I/O");
			// System.exit(1);
		}
	}

	public String SendRequest(char req_no, String UserFrom, String UserTo) {
		console.logEntry();
		String answer = null;
		
		out.print(req_no);
		out.println(UserFrom);
		out.println(UserTo);
		try {
			answer  = in.readLine();
			console.debug(answer);
		} catch (IOException e) {
			console.error(e);
			console.showException(e);
		}

		try {
			socket.close();
		} catch (IOException e) {
			console.error(e);
			System.out.println("No I/O");
		}
		
		console.logExit();
		return answer;
	}

	public void SendBillInfo(String UserFrom, String UserTo, Date Start, Date End) throws IOException {
		console.logEntry();

		ObjectOutputStream obj = new ObjectOutputStream(socket.getOutputStream());
		out.print(BILL);
		out.println(UserFrom);
		out.println(UserTo);
		obj.writeObject(Start);
		obj.writeObject(End);
		
		try {
			console.debug(in.readLine());
		} catch (IOException e) {
			console.error(e);
			console.showException(e);
		}

		try {
			socket.close();
		} catch (IOException e) {
			console.error(e);
			System.out.println("No I/O");
		}
		console.logExit();
	}
}
