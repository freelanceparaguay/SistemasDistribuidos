<?php
//se modificará para que tome de la base de datos
function getDBParam($parametro){
	$valorDevuelto="vacio";
	
	if($parametro==="botInitialPwd"){
		echo "el parámetro recibido es->".$parametro;
		$valorDevuelto="hola";	
	}else if($parametro==="currentDirective"){
		$valorDevuelto="start";	
	}else if($parametro==="initialResponse"){
		$valorDevuelto="reset";	
	}
	return $valorDevuelto;
}//fin function getDBParam

?>