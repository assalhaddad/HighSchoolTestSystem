package il.cshaifasweng.OCSFMediatorExample.server.ocsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Request;


public abstract class ChatServer extends AbstractServer {
    public ArrayList<ConnectionToClient> clients;

    public ChatServer(int port) {
        super(port);
        clients = new ArrayList<>();
    }

    @Override
    protected void receiveMessageFromClient(Object message, ConnectionToClient client,ChatServer clients) {
        // Handle the message received from a client
        System.out.println("Received from " + client + ": " + message);

        // Construct the combined message with the object
        Message combinedMessage = new Message((String) message, null);

        // Broadcast the combined message to all connected clients, including the sender
        sendToAllClients(combinedMessage);
    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
        // Add the connected client to the clients list
        clients.add(client);
        System.out.println("Client connected: " + client);
    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {
        // Remove the disconnected client from the clients list
        clients.remove(client);
        System.out.println("Client disconnected: " + client);
    }

    public void sendToAllClients(Object object) {
        for (ConnectionToClient client : clients) {
            try {
                client.sendToClient(object);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}