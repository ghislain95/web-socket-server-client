package com.example.printingservicetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientTCP {

    public static void main(String[] args) {
        try {
            // Créer un socket client pour se connecter au serveur
            Socket socket = new Socket("10.1.199.231", 9100);

            // Créer un flux d'entrée pour recevoir les données du serveur
            InputStream inputStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            // Lire les données envoyées par le serveur
            String message = in.readLine();
            System.out.println("Message reçu du serveur : " + message);

            // Fermer les flux et le socket
            in.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
