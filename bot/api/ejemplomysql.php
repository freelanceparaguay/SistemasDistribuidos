<?php
try{ 
 $host="localhost"; 
 $username="apache";
 $password="miclavesupersecreta";
 $database="basedatosbot";

	$mbd = new PDO('mysql:host=localhost;dbname=basedatosbot', $username, $password);
} catch (PDOException $e) {
    print "¡Error!: " . $e->getMessage() . "<br/>";
    die();
}


?>