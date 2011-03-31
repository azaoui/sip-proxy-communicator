package gov.nist.sip.sockets;

import gov.nist.sip.block.block;
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
	private static char BLOCK = '1';
	private static char UNBLOCK = '2';
	private static char FORWARD = '3';
	private static char UNFORWARD = '4';

	 public static void main(String[] args) {
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

	public MultipleSocketServer(Socket s, int i) {
		this.connection = s;
		this.ID = i;

	}

	@Override
	public void run() {

		try {
			InputStreamReader isr = new InputStreamReader(connection
					.getInputStream());
			BufferedReader line = new BufferedReader(isr);

			String returnCode;
			StringBuffer process = new StringBuffer();

			int request = line.read();
			String FromUser = line.readLine();
			String ToUser = line.readLine();

			Statement sql = DbConnection.getSql();
			int ToUserID = DbConnection.findUserID(FromUser);
			int FromUserID = DbConnection.findUserID(ToUser);
			
			
			if ((ToUserID == 0) || (FromUserID == 0)) {
				returnCode = "Either UserFrom or UserTo does not exist.";
				System.out.println(request);
				System.out.println(FromUser);
				System.out.println(ToUser);
			} else {
				if (request == BLOCK) {
					// block
					block.BlockUser(sql, FromUserID, ToUserID);
				} else if (request == UNBLOCK) {
					// unblock
					block.UnblockUser(sql, FromUserID, ToUserID);
				} else if (request == FORWARD) {
					// forward
				} else if (request == UNFORWARD) {
					// unforward
					try {
						sql.executeUpdate("delete from forward where forwarder = " + FromUserID);
					} catch (SQLException e) {
						System.out.println("No such user");
						e.printStackTrace();
					}
				} else {
					// other

				}

				System.out.println(process);
				// need to wait 10 seconds to pretend that we're processing
				// something
		//		try {
		//			Thread.sleep(10000);
		//		} catch (Exception e) {
		//		}

				returnCode = "MultipleSocketServer repsonded at " + request;
			}
			System.out.println(returnCode);
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
