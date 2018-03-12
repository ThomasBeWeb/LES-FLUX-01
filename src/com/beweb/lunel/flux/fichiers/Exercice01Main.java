/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beweb.lunel.flux.fichiers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author cantinelli
 */
public class Exercice01Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        // recupération d'une reference vers le fichier exercice1.txt
        File f = new File("/home/cantinelli/programmes/fichiers/exercice1.txt");
        
        // création d'un flux entrant du fichier (sortant pour le programme)
        FileOutputStream output = new FileOutputStream(f);
        
        // message a enregistrer dans le fichier
        String message = "Hello world !";
        
        if(f.exists()){
            
            // initialisation des variables
            int fin = message.length();
            
            // envoie par paquets de fin octets
            byte[] paquet = new byte[fin];

            // insertion des caractères dans le tableau
            for (int i = 0; i < fin; i++) {
                paquet[i] = (byte)message.charAt(i);
            }
            // on envoie chaque paquets dans le fichier
            output.write(paquet);    
        }else{
            System.out.println("Le fichier n'existe pas");
        }     
    }
    
}
