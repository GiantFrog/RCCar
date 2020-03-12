package science.skywhale.rccar;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class NetworkDude
{
	private DatagramSocket sock;
	
	public NetworkDude() throws SocketException
	{
		sock = new DatagramSocket();
	}
	
	public void send (byte[] bytes) throws IOException
	{
		DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.0.1"), 60065);
		sock.send(packet);
	}
}
