package science.skywhale.rccar;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class NetworkDude
{
	private DatagramSocket sock;
	private InetAddress ip;
	
	public NetworkDude() throws Exception
	{
		sock = new DatagramSocket();
		byte[] response = new byte[1];
		DatagramPacket responsePacket = new DatagramPacket(response, 1);
		ip = InetAddress.getByName("192.168.0.1");
		
		//verify we are communicating with our car and not some router
		//send("Are you our RC car?".getBytes());
		//sock.receive(responsePacket);
		//if ((char) response[0] != 'y')
		//	throw new Exception(ip.getHostName() + " responded with " + (char) response[0] + ".");
	}
	
	public void send (byte[] bytes) throws IOException
	{
		DatagramPacket packet = new DatagramPacket(bytes, bytes.length, ip, 60065);
		sock.send(packet);
	}
}
