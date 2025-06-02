package com.example.printingservicetest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurTCP {

    public static void main(String[] args) {
        try {
            // Adresse IP et port de l'imprimante
            String printerAddress = "10.1.196.69";
            int printerPort = 9100;

            // Créer un socket pour se connecter à l'imprimante
            Socket printerSocket = new Socket(printerAddress, printerPort);
            System.out.println("Connexion établie avec l'imprimante.");

            // Récupérer le chemin du fichier à envoyer
            String filePath = "data/fichier_label_SSCC_Monarch.txt";
            File file = new File(filePath);

            // Vérifier si le fichier existe
            if (!file.exists()) {
                System.err.println("Le fichier spécifié n'existe pas.");
                return;
            }

            // Lire le contenu du fichier
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            // Créer un flux de sortie pour envoyer des données à l'imprimante
            OutputStream outputStream = printerSocket.getOutputStream();

            // Envoyer le contenu du fichier à l'imprimante
            outputStream.write(content.toString().getBytes());
            outputStream.flush(); // Assurer que toutes les données sont envoyées

            System.out.println("Contenu du fichier envoyé à l'imprimante.");

            // Fermer le flux et le socket
            outputStream.close();
            printerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
