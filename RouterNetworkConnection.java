import java.net.*;
import java.util.*;

public class RouterNetworkConnection {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            System.out.println("Interface: " + ni.getName());
            Enumeration<InetAddress> addresses = ni.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                System.out.println("  Address: " + address.getHostAddress());
            }
            List<InterfaceAddress> ifaddrs = ni.getInterfaceAddresses();
            for (InterfaceAddress ifaddr : ifaddrs) {
                InetAddress broadcast = ifaddr.getBroadcast();
                int prefix = ifaddr.getNetworkPrefixLength();
                System.out.println("  Broadcast Address: " + (broadcast == null ? "None" : broadcast.getHostAddress()));
                System.out.println("  Netmask: " + prefix);
                System.out.println("  Router: " + (ni.getInterfaceAddresses().isEmpty() ? "None" : ni.getInterfaceAddresses().get(0).getAddress().getHostAddress()));
            }
        }
    }
}
