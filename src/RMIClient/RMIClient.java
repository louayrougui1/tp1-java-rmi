        package RMIClient;

import RMISERVER.HotelRemote;
import RMISERVER.Reservation;

import java.rmi.Naming;
import java.time.LocalDate;
import java.util.Map;

public class RMIClient {

    public static void main(String[] args) {

        try {

            HotelRemote hotel =
                    (HotelRemote) Naming.lookup(
                            "rmi://localhost:1233/HotelService"
                    );

            System.out.println("CLIENT RMI HOTEL");
            System.out.println("Connexion au serveur reussie.");
            System.out.println();

            // reserver 3 chambres

            System.out.println("===== RESERVATION DE 3 CHAMBRES =====");

            int id1 = hotel.reserveChambre(
                    "Ahmed",
                    101,
                    LocalDate.of(2026, 10, 5),
                    LocalDate.of(2026, 10, 10),
                    500.0
            );

            int id2 = hotel.reserveChambre(
                    "Mohamed",
                    102,
                    LocalDate.of(2026, 10, 6),
                    LocalDate.of(2026, 10, 12),
                    600.0
            );

            int id3 = hotel.reserveChambre(
                    "Ali",
                    103,
                    LocalDate.of(2026, 10, 8),
                    LocalDate.of(2026, 10, 15),
                    700.0
            );

            System.out.println("Reservation Ahmed : ID = " + id1);
            System.out.println("Reservation Mohamed : ID = " + id2);
            System.out.println("Reservation Ali : ID = " + id3);

            System.out.println();

            // afficher les reservations

            System.out.println("===== LISTE DES RESERVATIONS =====");

            afficherReservations(hotel.listeReservations());

            // annuler une reservation

            System.out.println("===== ANNULATION =====");

            boolean resultat = hotel.annuleReservation(
                    "Mohamed",
                    102
            );

            System.out.println(
                    "Annulation Mohamed / chambre 102 : "
                            + resultat
            );

            System.out.println();

            // afficher encore une fois

            System.out.println("===== NOUVELLE LISTE =====");

            afficherReservations(hotel.listeReservations());

            // tests invalides

            System.out.println();
            System.out.println("===== TESTS INVALIDES =====");

            // nom vide

            int testNomVide = hotel.reserveChambre(
                    "",
                    104,
                    LocalDate.of(2026, 10, 20),
                    LocalDate.of(2026, 10, 25),
                    300.0
            );

            System.out.println("Nom vide : " + testNomVide);

            // numero de chambre invalide

            int testChambre = hotel.reserveChambre(
                    "Test",
                    -1,
                    LocalDate.of(2026, 10, 20),
                    LocalDate.of(2026, 10, 25),
                    300.0
            );

            System.out.println(
                    "Chambre invalide : " + testChambre
            );

            // date invalide

            int testDate = hotel.reserveChambre(
                    "Test",
                    105,
                    LocalDate.of(2026, 10, 30),
                    LocalDate.of(2026, 10, 20),
                    300.0
            );

            System.out.println(
                    "Date invalide : " + testDate
            );

            // prix negatif

            int testPrix = hotel.reserveChambre(
                    "Test",
                    106,
                    LocalDate.of(2026, 10, 20),
                    LocalDate.of(2026, 10, 25),
                    -100.0
            );

            System.out.println(
                    "Prix negatif : " + testPrix
            );

            // chambre deja reservee

            int testChambreOccupee = hotel.reserveChambre(
                    "Nouveau Client",
                    101,
                    LocalDate.of(2026, 10, 7),
                    LocalDate.of(2026, 10, 9),
                    200.0
            );

            System.out.println(
                    "Chambre deja reservee : "
                            + testChambreOccupee
            );

            // reservation qui n'existe pas

            boolean testAnnulation =
                    hotel.annuleReservation(
                            "ClientInexistant",
                            999
                    );

            System.out.println(
                    "Annulation inexistante : "
                            + testAnnulation
            );

            System.out.println();

        } catch (Exception e) {

            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // afficher les reservations

    private static void afficherReservations(
            Map<Integer, Reservation> reservations) {

        if (reservations.isEmpty()) {
            System.out.println("Aucune reservation.");
            return;
        }

        for (Map.Entry<Integer, Reservation> entry
                : reservations.entrySet()) {

            System.out.println(
                    "ID = " + entry.getKey()
            );

            System.out.println(
                    entry.getValue()
            );

            System.out.println();
        }
    }
}