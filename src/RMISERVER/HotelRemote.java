package RMISERVER;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Map;

public interface HotelRemote extends Remote {

    int reserveChambre(
            String nomClient,
            int numeroChambre,
            LocalDate dateDebut,
            LocalDate dateFin,
            double prixTotal
    ) throws RemoteException;

    boolean annuleReservation(
            String nomClient,
            int numeroChambre
    ) throws RemoteException;

    Map<Integer, Reservation> listeReservations()
            throws RemoteException;
}