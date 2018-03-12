/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beweb.lunel.flux;
import java.util.Scanner;
/**
 *
 * @author cantinelli
 */

/**
 * Pour le developpement de la carte de restaurant il faut 
 * - fournir une interface conviviale (l'utilisateur doit pouvoir faire tout ce qui est prevu par le programme)
 * - Sauvegarder les données brutes afin de pouvoir effectuer les traitements 
 * - Séparer les données du design ( on applique le design au moment du rendu)
 * Il est impératif de comprendre l'utilité des fonctions qui executent une seule et unique tache.
 * Le design du rendu est perfectible mais étant une fonctionnalité à part, il est facilement evoluable
 * @author loic
 */

public class CarteRestaurant {

    // Pour un plus grande souplesse on declare quelques variables de classe
    public static String[] carte;
    public static String[] menu = new String[4];
    public static int nombreDeLignes = 0;
    public static int maxWidth = 0 ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bonjour, voulez vous creer une nouvelle carte (c) ou quitter (q) ?");
        if (scanner.hasNextLine()) {
            String value = scanner.nextLine();
            switch (value) {
                case "c":
                    System.out.println("cool ! commençons,");
                    carte(scanner);
                    System.exit(0);
                case "q":
                    System.out.println("Au revoir et merci ! ");
                    System.exit(0);
                default:
                    System.out.println("Une erreur est survenue, merci de recommencer");
                    System.exit(0);
            }

        }

    }

    /**
     * Fonction d'entrée de création de la carte. 
     * A la fin de l'initialisation de la carte on rentre dans la partie de la création des menus
     * @param scanner 
     */
    public static void carte(Scanner scanner) {
        String nomDeLaCarte;
        System.out.println("donnez un nom a votre carte");
        if (scanner.hasNextLine()) {
            nomDeLaCarte = scanner.nextLine();
            nombreDeLignes++;
            carte = new String[nombreDeLignes];
            carte[0] = nomDeLaCarte;
        }
        choix(scanner);
    }

    /**
     * Fonction principale du programme, l'interface utilisateur propose tous les choix.
     * On reste dans cette fonction tant qu'on quitte pas
     * @param scanner 
     */
    public static void choix(Scanner scanner) {
        System.out.println("Voulez vous ajouter un menu (m) ou afficher la carte (a) ou quitter (q) ?");
        if (scanner.hasNextLine()) {
            String choix = scanner.nextLine();
            switch (choix) {
                case "m":
                    addMenu(scanner);
                    afficherCarte();
                    choix(scanner);
                    break;
                case "a":
                    afficherCarte();
                    choix(scanner);
                    break;
                case "q":
                    System.out.println("Au revoir et merci !");
                    System.exit(0);
                default:
                    System.out.println("Une erreur est survenue, merci de recommencer");
                    System.exit(0);
            }
        }
    }

    /**
     * Fonction spécifique à la création d'un menu. 
     * A la fin de la création du menu on effectue une mise a jour de la carte avec le nouveau menu.
     * A retour de la fonction de mise a jour de la carte on retourne dans 
     * le switch de la fonction choix
     * @param scanner 
     */
    public static void addMenu(Scanner scanner) {
        System.out.println("Donnez un nom au menu :");
        if (scanner.hasNextLine()) {
            nombreDeLignes++;
            menu[0] = scanner.nextLine();
        }
        for (int i = 0; i < 3; i++) {
            String message = "";
            switch (i) {
                case 0:
                    message = "veuillez ajouter une entrée";
                    break;
                case 1:
                    message = "veuillez ajouter un plat principal";
                    break;
                case 2:
                    message = "veuillez ajouter un dessert";
                    break;
            }
            System.out.println(message);
            if (scanner.hasNextLine()) {
                nombreDeLignes++;
                menu[i+1] = scanner.nextLine();
            }
        }
        updateCarte();

    }

    /**
     * La fonction met a jour la carte en y incluant le nouveau menu
     * a la fin de la mise a jour de la carte on retourne dans la fonction appelante
     */
    public static void updateCarte() {
        String[] temp = new String[nombreDeLignes];
        for (int i = 0; i < carte.length; i++) {
            temp[i] = carte[i];
        }
        int j = 0;
        for (int i = carte.length; i < nombreDeLignes; i++) {
            temp[i] = menu[j];
            j++;
        }
        carte = temp;
    }

    /**
     * Affichage de la carte en y incluant le design
     * Les données restent brutes dans le programme ! 
     * On applique le design lors de l'affiche uniquement !
     */
    public static void afficherCarte() {
        int i = 0;
        maxWidth = getMaxWidth();
        setBorders();
        for (String ligne : carte) {            
            designLine(ligne);
            if(i == 0){
                setSeparator();
                setSeparator();
            }else if(i%4==0 && i != carte.length-1){
                setSeparator();
            }
            i++;
        }
        setBorders();
    }

    /*
    *Les fonctions pour le design de la carte
    */

    /**
     * On recupere la taille maximale de la ligne
     * @return 
     */
    public static int getMaxWidth() {
        int max = 0;
        for (String ligne : carte) {
            max = (ligne.length() > max) ? ligne.length() : max;
        }
        return max + 2;
    }
    /**
     * Une ligne bordure haute et basse
     */
    public static void setBorders(){
        String line = "";
        for (int i = 0; i < maxWidth; i++) {
            line += "#";
        }
        System.out.println(line);
    }
    /**
     * ajout du design sur une line
     * @param text 
     */
    public static void designLine(String text){        
        for (int i = 0; i < maxWidth; i++) {
            if(i==0){
                text = "#" + text;
                i = text.length();
            }
            if(i<maxWidth-1){
                text += " ";
            }else{
                text +="#";
            }            
        }
        System.out.println(text);
    }
    
    /**
     * une ligne qui sert de séparateur
     */
    public static void setSeparator(){
        String line = "#";
        for (int i = 0; i < maxWidth-2; i++) {
            line += "-";
        }
        line += "#";
        System.out.println(line);
    }
}