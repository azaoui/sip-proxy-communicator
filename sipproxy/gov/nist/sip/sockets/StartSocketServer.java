package gov.nist.sip.sockets;

public class StartSocketServer extends Thread {
	public StartSocketServer(){
	}
	
	public void run() {
		MultipleSocketServer.main(null);
	}
}
