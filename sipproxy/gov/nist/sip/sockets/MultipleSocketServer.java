package gov.nist.sip.sockets;

import gov.nist.sip.block.block;
import gov.nist.sip.db.DbConnection;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MultipleSocketServer implements Runnable {

	
	private Socket connection;
	private int ID;
	private static final char BLOCK = '1';
	private static final char UNBLOCK = '2';
	private static final char FORWARD = '3';
	private static final char UNFORWARD = '4';
	private static final char BILL = '5';
	private static final char BLOCK_LIST = '6';

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
//			
			BufferedReader line = new BufferedReader(isr);

			BufferedOutputStream os = new BufferedOutputStream(connection
					.getOutputStream());
	//		OutputStreamWriter osw = new OutputStreamWriter(os, "US-ASCII");
			PrintWriter osw = new PrintWriter(os);
			
			String returnCode;
			StringBuffer process = new StringBuffer();

			int request = line.read();
			String FromUser = line.readLine();
			String ToUser = line.readLine();

			Statement sql = DbConnection.getSql();
			int ToUserID = DbConnection.findUserID(ToUser);
			int FromUserID = DbConnection.findUserID(FromUser);
			
			
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
				} else if (request == BILL){
					// accept request with call info
				//	ObjectInputStream obj = new ObjectInputStream(connection.getInputStream());
					System.out.println(FromUser + "ole " + ToUser + " ");
				//	Date start = (Date) obj.readObject();
				//	Date end = (Date) obj.readObject();
					System.out.println(FromUser + "ole " + ToUser + "");
				} else if (request == BLOCK_LIST) {					
					ResultSet result1 =  sql.executeQuery("select blockee from block where blocker =" + FromUserID);
					while (result1.next()){
						ResultSet result2 =  sql.executeQuery("select username from users where \"ID\" =" + result1.getInt(1));
						osw.println(result2.getString(1));
					}
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
			
			osw.println(returnCode);
		//	osw.write(returnCode);
			osw.flush();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			
				try {
					connection.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}

}
