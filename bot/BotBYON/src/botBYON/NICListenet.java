package botBYON;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;

/**
 * A class that allows the bot to listen promiscuously for TCP connections to
 * and from this host
 *
 * @author fbegin1
 *
 */
class NICListener extends Thread {

    Set<String> connectionTriplets = new HashSet<String>();
    boolean pleaseWait = false;

    public void run() {
        while (true) {
            // Has a pause been requested?
            synchronized (this) {
                while (pleaseWait) {
                    try {
                        wait();
                    } catch (Exception e) {
                    }
                }//while (pleaseWait)
            }//synchronized
            /*
 * If not pause, start snooping on the interface, keeping a
running tally of
 * (srcIP,dstIP,dstPort) tcp triplets
             */
            try {
                NetworkInterface[] devices = JpcapCaptor.getDeviceList();
                JpcapCaptor captor;
                captor = JpcapCaptor.openDevice(devices[2], 65535, false,
                        20);
                captor.setFilter("tcp and (dst port 80 or dst port 443)",
                        true);
                while (true) {
                    Packet myCapturedPacket = captor.getPacket();
                    if (myCapturedPacket != null) {
                        final TCPPacket tcpPacket = (TCPPacket) myCapturedPacket;

                        connectionTriplets.add("(" + tcpPacket.src_ip.toString().replace("/", "") + "," + tcpPacket.dst_ip.toString().replace("/", "") + "," + tcpPacket.dst_port + ")");
                    }// if (myCapturedPacket != null)
                }// while (true)
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } //try
        }//ciclo while
    }//fin run

}//fin clase
