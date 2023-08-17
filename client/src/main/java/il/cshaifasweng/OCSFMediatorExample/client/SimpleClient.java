package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		System.out.println("inside handleMessageFromServer:");
		System.out.println(((Message)msg).getMessage());
		EventBus.getDefault().post((Message)msg);

	}



	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3306);
		}
		return client;
	}

}
