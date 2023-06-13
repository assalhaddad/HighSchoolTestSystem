package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.sql.SQLOutput;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static SimpleServer server;
    public static void main( String[] args ) throws IOException
    {
        server = new SimpleServer(3003);
        server.connectToData();
        System.out.println("server is listening");
        server.listen();
    }
}
