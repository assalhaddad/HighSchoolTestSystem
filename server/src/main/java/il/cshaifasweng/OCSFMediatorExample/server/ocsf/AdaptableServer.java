// This file contains material supporting section 6.13 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com

package il.cshaifasweng.OCSFMediatorExample.server.ocsf;

/**
* The <code> AdaptableServer </code> is an adapter class
* that extends the <code> AbstractServer </code> class in place of
* the <code> AbstractObservableServer </code>.<p>
*
* Project Name: OCSF (Object Client-Server Framework)<p>
*
* @author Dr. Robert Lagani&egrave;re
* @version Febuary 2001
*/
class AdaptableServer extends AbstractServer
{
  //Instance variables **********************************************

  /**
   * The adapter used to simulate multiple class inheritance.
   */
  private ObservableServer server;

// CONSTRUCTORS *****************************************************

  /**
   * Constructs the server adapter.
   *
   * @param  server  the server's host name.
   * @param  port  the port number.
   */
  public AdaptableServer(int port, ObservableServer server)
  {
    super(port);
    this.server = server;
  }

// OVERRIDDEN METHODS ---------

  /**
   * Hook method called each time a new client connection is
   * accepted.
   *
   * @param client the connection connected to the client.
   */
  final protected void clientConnected(ConnectionToClient client)
  {
    server.clientConnected(client);
  }

  /**
   * Hook method called each time a client disconnects.
   *
   * @param client the connection with the client.
   */
  final protected void clientDisconnected(ConnectionToClient client)
  {
    server.clientDisconnected(client);
  }

  /**
   * Hook method called each time an exception
   * is raised in a client thread.
   *
   * @param client the client that raised the exception.
   * @param exception the exception raised.
   */
  final protected void clientException(ConnectionToClient client,
                                        Throwable exception)
  {
    server.clientException(client, exception);
  }

  /**
   * Hook method called when the server stops accepting
   * connections because an exception has been raised.
   *
   * @param exception the exception raised.
   */
  final protected void listeningException(Throwable exception)
  {
    server.listeningException(exception);
  }

  /**
   * Hook method called when the server stops accepting
   * connections.
   */
  final protected void serverStopped()
  {
    server.serverStopped();
  }

  /**
   * Hook method called when the server starts listening for
   * connections.
   */
  final protected void serverStarted()
  {
    server.serverStarted();
  }

  /**
   * Sends a message to every client connected to the server.
   * This is merely a utility; a subclass may want to do some checks
   * before actually sending messages to all clients.
   * This method can be overriden, but if so it should still perform
   * the general function of sending to all clients, perhaps after some kind
   * of filtering is done. Any exception thrown while
   * sending the message to a particular client is ignored.
   *
   * @param msg   Object The message to be sent
   */
  public void sendToAllClients(Object msg)
  {
    Thread[] clientThreadList = getClientConnections();

    for (int i=0; i<clientThreadList.length; i++)
    {
      try
      {
        ((ConnectionToClient)clientThreadList[i]).sendToClient(msg);
      }
      catch (Exception ex) {}
    }
  }
  /**
   * Hook method called when the server is closed.
   */
  final protected void serverClosed()
  {
    server.serverClosed();
  }

  /**
   * Handles a command sent from the client to the server.
   *
   * @param msg    the message sent.
   * @param client the connection connected to the client that
   *               sent the message.
   */
  final protected void handleMessageFromClient(Object msg,
                                               ConnectionToClient client)
  {
    server.handleMessageFromClient(msg, client);
  }
}
