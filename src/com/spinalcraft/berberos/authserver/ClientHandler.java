package com.spinalcraft.berberos.authserver;

import java.net.Socket;

public class ClientHandler implements Runnable{
	private Socket conn;
	
	public ClientHandler(Socket conn){
		this.conn = conn;
	}
	
	@Override
	public void run(){
		Receiver receiver = new Receiver(conn, Crypt.getInstance());
		
		if(receiver.receiveMessage())
			processRequest(receiver);
	}
	
	private void processRequest(Receiver receiver){
		String intent = receiver.getHeader("intent");
		switch(intent){
		case "ticket":
			(new TicketRequest(receiver, conn)).process();
			break;
		case "registerService":
			break;
		}
	}
	
//	private void processRequest(MessageReceiver receiver){
//		String intent = receiver.getHeader("intent");
//		switch(intent){
//		case "access":
//			(new AccessRequest(receiver, conn)).process();
//			break;
//		case "message":
//			String publicKey = receiver.getHeader("publicKey");
//			System.out.println("Got Public Key: " + publicKey);
//			Actor actor;
//			try {
//				actor = Actor.getFromPublicKey(publicKey);
//				String message = receiver.getItem("message");
//				System.out.println("Received Message: " + message);
//				MessageSender sender = new Sender(conn, Crypt.getInstance());
//				sender.addItem("message", message + " to you as well");
//				sender.sendEncrypted(actor.secretKey);
//			} catch (SQLException | IOException e) {
//				e.printStackTrace();
//			}
//			break;
//		}
//	}
}



