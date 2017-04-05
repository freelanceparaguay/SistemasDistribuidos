<?php
try{
 $host="localhost"; 
 $username="apache";
 $password="miclavesupersecreta";
 $database="basedatosbot";
 $port=3306;
 
 $connectmysqli = new mysqli($host, $username, $password, $database, $port);
 
  if (!function_exists('mysqli_init') && !extension_loaded('mysqli')) {
      echo 'no se encuentra el driver mysqli :(';
 } else {
      echo 'se consiguio la conexión';
 }
 
 $res = $connectmysqli->query("SELECT * FROM bots");
  echo "<br> res->".$res->num_rows;
 
 $res = $connectmysqli->query("SELECT * FROM bots WHERE botid=(?)");
  echo "<br> res->".$res->num_rows;
 	 
}catch (Exception $e) {
  echo $e->errorMessage();
} 

 
?>