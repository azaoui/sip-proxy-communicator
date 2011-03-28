package gov.nist.sip.sockets;

import gov.nist.sip.db.DbConnection;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Statement;

public class MultipleSocketServer implements Runnable {

	private Socket connection;
	private int ID;

	public void listenSocket() {
		int port = 4444;
		int count = 0;

		try {
			ServerSocket socket1 = new ServerSocket(port);

			while (true) {
				Socket connection = socket1.accept();
				Runnable runnable = new MultipleSocketServer(connection,
						++count);
				Thread thread = new Thread(runnable);
				thread.start();
			}
		} catch (IOException e) {
			System.out.println("Accept failed: 4444");
			System.exit(-1);
		}
	}

	MultipleSocketServer(Socket s, int i) {
		this.connection = s;
		this.ID = i;

	}

	@Override
	public void run() {
		
		try {
			InputStreamReader isr = new InputStreamReader(connection.getInputStream());
			BufferedReader line = new BufferedReader(isr);
			
			int character;
			StringBuffer process = new StringBuffer();

			int request = line.read();
			String FromUser = line.readLine();
			String ToUser = line.readLine();
			
			Statement sql = DbConnection.getSql();
			int ToUserID = DbConnection.findUserID(ToUser);
			int FromUserID = DbConnection.findUserID(FromUser);
			
			switch (request) {
			case 01:
				// block
				try {
					sql.executeQuery("insert into block (blocker,blockee) values (" + FromUserID + "," + ToUserID + ")");
				} catch (SQLException e) {
					System.out.println("No such user");
					e.printStackTrace();
				}
			
			case 02:
				// unblock
				try {
					sql.executeQuery("delete from block where blocker = " + FromUserID + " and blockee = " + ToUserID);
				} catch (SQLException e) {
					System.out.println("No such user");
					e.printStackTrace();
				}
			case 03:
				// forward
				
			case 04:
				// unforward
				try {
					sql.executeQuery("delete from forward where forwarder = " + FromUserID);
				} catch (SQLException e) {
					System.out.println("No such user");
					e.printStackTrace();
				}
			case 05:
				//other

			}
			
			System.out.println(process);
			// need to wait 10 seconds to pretend that we're processing
			// something
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
			}

			String returnCode = "MultipleSocketServer repsonded at " + request;
			BufferedOutputStream os = new BufferedOutputStream(connection
					.getOutputStream());
			OutputStreamWriter osw = new OutputStreamWriter(os, "US-ASCII");
			osw.write(returnCode);
			osw.flush();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				connection.close();
			} catch (IOException e) {
			}
		}
	}

}
