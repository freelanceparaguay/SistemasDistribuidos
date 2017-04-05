<?php
include_once("./conexiondb.php");

$botid;
$status;
$hostName;
$osname;
$osversion;
$osarch;
$hostuptime;
$hostips;
$sourceip;
$proxysourceip;
$smtpmode;
$tcpconnections;
$created;
$lastupdated;





		$query="select * from bots order by lastupdated;";

//		$result=$connectmysqli->query($query);	
		
		$row_num=0;
		if($result=$connectmysqli->prepare($query)){

			$result->execute();
			$result->bind_result($botid, $status, $hostName, $osname, $osversion, $osarch, $hostuptime, $hostips, $sourceip, $proxysourceip, $smtpmode, $tcpconnections, $created, $lastupdated);
			$result->store_result();

			for($row_num=0;$row_num < $result->num_rows; $row_num++){
				$result->data_seek($row_num);
				$result->fetch();
				//--------------
				echo "<br> ".$result->num_rows;
				printf ("%s %s\n", $botid, $status);			
			}//
			$result->close();	
					
		}	
		//echo "Table creation failed: (" . $connectmysqli->errno . ") " . $connectmysqli->error;
		
		
	$connectmysqli->close();
?>