package RMISERVER;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {

        try {

            HotelRemoteImpl hotel = new HotelRemoteImpl();

            LocateRegistry.createRegistry(1233);
            Naming.rebind ("rmi://localhost:1233/HotelService", hotel);
            System.out.println("Hotel service est disponible.");

        } catch (Exception e) {

            System.err.println(
                    "Erreur du serveur : "
                            + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}