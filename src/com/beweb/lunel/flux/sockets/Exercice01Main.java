/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beweb.lunel.flux.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author cantinelli
 */
public class Exercice01Main {

    public static void main(String[] args) throws IOException {
        
        
        // objet capturant le flux de System.in
        Scanner scanner = new Scanner(System.in);
        
        String reponse = "";
        
        // Socket spéciale serveur ecoute sur 127.0.0.1:4000
        ServerSocket server = new ServerSocket(4000);
        Socket client = null;
        
        // en attente d'une connexion cliente, retourne le handler du canal
        client = server.accept();
        System.out.println("connexion du client");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	PrintWriter out = new PrintWriter(client.getOutputStream());
	out.println("Je suis prêt à recevoir des requêtes");
        out.flush();
	out.println("Saisissez quelque chose ou quittez (q)");
	out.flush();
        
        // préparation de la gestion du flux entrant
        String ligne = "";
        
        // tant que le client ne retourne pas "q" on lit les messages des clients
        while(!(ligne = in.readLine()).contentEquals("q")) {
            System.out.println(ligne);

//            if(scanner.hasNext()){
//                reponse = scanner.nextLine();
//                out.println(reponse);
//                out.flush();
//            }
        }
        
        // fermeture des canaux
        out.println("Vous allez être déconnecté");
        out.flush();
        in.close();
        client.close();
        server.close();
        scanner.close();
    }
    
}
