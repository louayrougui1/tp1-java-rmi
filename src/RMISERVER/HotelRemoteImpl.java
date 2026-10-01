package RMISERVER;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HotelRemoteImpl extends UnicastRemoteObject
        implements HotelRemote {

    private static final long serialVersionUID = 1L;

    private HashMap<Integer, Reservation> reservations;

    private int prochainID = 1;

    public HotelRemoteImpl() throws RemoteException {
        super();
        reservations = new HashMap<>();
    }

    @Override
    public synchronized int reserveChambre(
            String nomClient,
            int numeroChambre,
            LocalDate dateDebut,
            LocalDate dateFin,
            double prixTotal
    ) throws RemoteException {

        if (nomClient == null || nomClient.trim().isEmpty()) {
            return -1;
        }

        if (numeroChambre <= 0) {
            return -1;
        }

        if (dateDebut == null || dateFin == null) {
            return -1;
        }

        if (dateFin.isBefore(dateDebut)) {
            return -1;
        }

        if (prixTotal < 0) {
            return -1;
        }

        for (Reservation reservation : reservations.values()) {

            if (reservation.getNumeroChambre() == numeroChambre) {

                boolean chevauchement =
                        !dateFin.isBefore(reservation.getDateDebut())
                                && !dateDebut.isAfter(reservation.getDateFin());

                if (chevauchement) {
                    return -1;
                }
            }
        }

        // Création de l'identifiant
        int idReservation = prochainID++;

        // Création de la réservation
        Reservation reservation = new Reservation(
                idReservation,
                nomClient,
                numeroChambre,
                dateDebut,
                dateFin,
                prixTotal
        );

        // L'identifiant est exactement la clé du HashMap
        reservations.put(idReservation, reservation);

        System.out.println(
                "Nouvelle réservation créée : ID = "
                        + idReservation
        );

        return idReservation;
    }

    @Override
    public synchronized boolean annuleReservation(
            String nomClient,
            int numeroChambre
    ) throws RemoteException {

        Integer idASupprimer = null;

        for (Reservation reservation:reservations.values() ) {


            if (reservation.getNomClient().equals(nomClient)
                    && reservation.getNumeroChambre() == numeroChambre) {

                idASupprimer = reservation.getIDReservation();
                break;
            }
        }

        if (idASupprimer != null) {

            reservations.remove(idASupprimer);

            System.out.println(
                    "Réservation annulée : ID = "
                            + idASupprimer
            );

            return true;
        }

        return false;
    }

    @Override
    public synchronized Map<Integer, Reservation> listeReservations()
            throws RemoteException {

        // On retourne une copie pour éviter de modifier
        // directement la HashMap du serveur.
        return new HashMap<>(reservations);
    }
}