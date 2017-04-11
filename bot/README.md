README.md

Código fuente adaptado de 
SANS Institute InfoSec Reading Room
https://www.sans.org/reading-room/whitepapers/threats/byob-build-botnet-33729

Materiales necesarios para ejecución
-----------------------------------
Apache, MariaDB, JavaSDK, Netbeans, PHP.

Cliente bot Java
------------------
Se han comentado las funciones de:
SPAM.
SCAN.

Aunque la mismas continuan en sus archivos originales.

El cliente Java requiere dependencias externas como:
* Jpcap.
* dns, para los paquetes org.xbill.*.

Se ejecuta en clientes Linux, y existe una leve dependencia debido a comandos del sistema operativo.
lshw, uptime, nmap.

Servidor C&C
---------------
connect.php -> ha sido modificado y adaptado a las necesidades.
listadoresp.php -> es una página que muestra los bots conectados en el navegador.
basedatosbot.sql -> resguardo de la base de datos.


