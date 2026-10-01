package RMISERVER;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    private int IDReservation;
    private String nomClient;
    private int numeroChambre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prixTotal;

    public Reservation(
            int IDReservation,
            String nomClient,
            int numeroChambre,
            LocalDate dateDebut,
            LocalDate dateFin,
            double prixTotal
    ) {
        this.IDReservation = IDReservation;
        this.nomClient = nomClient;
        this.numeroChambre = numeroChambre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
    }

    public int getIDReservation() {
        return IDReservation;
    }

    public String getNomClient() {
        return nomClient;
    }

    public int getNumeroChambre() {
        return numeroChambre;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "IDReservation=" + IDReservation +
                ", nomClient='" + nomClient + '\'' +
                ", numeroChambre=" + numeroChambre +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prixTotal=" + prixTotal +
                '}';
    }
}