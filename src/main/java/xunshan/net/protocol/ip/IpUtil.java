package xunshan.net.protocol.ip;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class IpUtil {

    public static void main(String[] args) {
        System.out.println(getBroadCastAddr());
    }

    public static String getBroadCastAddr() {
        try {
            Enumeration<NetworkInterface> eni = NetworkInterface
                    .getNetworkInterfaces();
            boolean ipAvailable = false;
            while (eni.hasMoreElements()) {

                NetworkInterface networkCard = eni.nextElement();
                List<InterfaceAddress> ncAddrList = networkCard
                        .getInterfaceAddresses();
                Iterator<InterfaceAddress> ncAddrIterator = ncAddrList.iterator();
                while (ncAddrIterator.hasNext()) {
                    InterfaceAddress networkCardAddress = ncAddrIterator.next();
                    InetAddress address = networkCardAddress.getAddress();
                    if (!address.isLoopbackAddress()) {
                        String hostAddress = address.getHostAddress();
                        System.out.println("address        =   " + hostAddress);

                        if (hostAddress.indexOf(":") > 0) {
                            // case : ipv6
                            ipAvailable = true;
                            continue;
                        } else {
                            // case : ipv4
                            String maskAddress = calcMaskByPrefixLength(networkCardAddress.getNetworkPrefixLength());
                            String subnetAddress = calcSubnetAddress(hostAddress, maskAddress);
                            String broadcastAddress = networkCardAddress.getBroadcast().getHostAddress();

                            System.out.println("subnetmask     =   " + maskAddress);
                            System.out.println("subnet         =   " + subnetAddress);
                            System.out.println("broadcast      =   " + broadcastAddress + "\n");

                            if (ipAvailable) {
                                return broadcastAddress;
                            }
                        }
                    } else {
                        String loopback = networkCardAddress.getAddress().getHostAddress();
                        System.out.println("loopback addr  =   " + loopback + "\n");
                    }
                }
                System.out.println("----- NetworkInterface  Separator ----\n\n");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String calcMaskByPrefixLength(int length) {
        int mask = -1 << (32 - length);
        int partsNum = 4;
        int bitsOfPart = 8;
        int maskParts[] = new int[partsNum];
        int selector = 0x000000ff;

        for (int i = 0; i < maskParts.length; i++) {
            int pos = maskParts.length - 1 - i;
            maskParts[pos] = (mask >> (i * bitsOfPart)) & selector;
        }

        String result = "";
        result = result + maskParts[0];
        for (int i = 1; i < maskParts.length; i++) {
            result = result + "." + maskParts[i];
        }
        return result;
    }

    public static String calcSubnetAddress(String ip, String mask) {
        String result = "";
        try {
            // calc sub-net IP
            InetAddress ipAddress = InetAddress.getByName(ip);
            InetAddress maskAddress = InetAddress.getByName(mask);

            byte[] ipRaw = ipAddress.getAddress();
            byte[] maskRaw = maskAddress.getAddress();

            int unsignedByteFilter = 0x000000ff;
            int[] resultRaw = new int[ipRaw.length];
            for (int i = 0; i < resultRaw.length; i++) {
                resultRaw[i] = (ipRaw[i] & maskRaw[i] & unsignedByteFilter);
            }

            // make result string
            result = result + resultRaw[0];
            for (int i = 1; i < resultRaw.length; i++) {
                result = result + "." + resultRaw[i];
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Get IP address from first non-localhost interface
     * @param useIPv4  true=return ipv4, false=return ipv6
     * @return  address or empty string
     */
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':')<0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
    }

}
