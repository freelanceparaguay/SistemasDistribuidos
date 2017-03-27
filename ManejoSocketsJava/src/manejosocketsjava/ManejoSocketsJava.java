/*
 * Ejemplo de servidor de socket simple, no maneja concurrencia
adaptado por http://otroblogdetecnologias.blogspot.com
Fuente: Oracle. 
El servidor puede ser probado abriendo una terminal y utilizando la aplicación TELNET.
Ejemplo desde Linux: telnet localhost 10005


*/
package manejosocketsjava;


/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import java.net.*;
import java.io.*;

public class ManejoSocketsJava {
    public static void main(String[] args) throws IOException {
        
//        if (args.length != 1) {
//            System.err.println("Usage: java EchoServer <port number>");
//            System.exit(1);
//        }
        
//        int portNumber = Integer.parseInt(args[0]);

        int portNumber = 10005;

                    System.out.println("Servidor Iniciado y a la escucha en puerto="+portNumber);
        
        try (
//            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
            ServerSocket serverSocket = new ServerSocket(portNumber);                
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
                                
        ) {
            String inputLine;


            System.out.println("Recibiendo conexiones ->");
            
            //lectura de datos recibidos
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibiendo->"+inputLine);
                //Si la cadena proveniente del flujo comienza con COMANDO, pasa a ser una cadena reconocida
                if (inputLine.startsWith("COMANDO")){
                    out.println("El servidor envia-> COMANDO");                    
                }else{
                    out.println("NO SE RECONOCE->"+inputLine);
                }//fin if                
            }//fin while in
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}