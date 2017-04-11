<?php
//se modificará para que tome de la base de datos
function getDBParam($parametro){



	$valorDevuelto="vacio";
	
	if($parametro==="botInitialPwd"){
//		echo "el parámetro recibido es->".$parametro;
		$valorDevuelto="hola";	
	}else if($parametro==="currentDirective"){
		$valorDevuelto="start";	
	}else if($parametro==="initialResponse"){
		$valorDevuelto="reset";	
	}
	return $valorDevuelto;
}//fin function getDBParam


function getDBParamBot($botid){	
	include("./conexiondb.php");

	$valorDevuelto="sleep";
			
	$query="SELECT * FROM bots WHERE botid='".$botid."'";
	$result=$connectmysqli->query($query);
	
	if($result->num_rows<1){
//		echo "cero";
		$valorDevuelto="nobotexist";
	}else{
		$result->data_seek(1);
		$row=$result->fetch_assoc();
//		echo $row['currentdirective'];
		$valorDevuelto=$row['currentdirective'];	
	}//if
	
		
	return $valorDevuelto;
}//fin function getDBParam


?>