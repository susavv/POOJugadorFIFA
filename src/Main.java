import db.Database;
import models.Equipo;
import models.Jugador;
import models.Tecnico;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import static operations.OperationEquipo.*;
import static operations.OperationJugador.*;

public class Main {
    public static void main(String[] args) {
        try {
            Database.connect();
            Database.createTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Scanner leer = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n *********** CRUD Jugador *********** ");
            System.out.println("1. Crear Jugador");
            System.out.println("2. Buscar Jugador");
            System.out.println("3. Mostrar jugadores");
            System.out.println("*********** CRUD Equipo *********** ");
            System.out.println("4. Crear Equipo");
            System.out.println("5. Asignar Jugador en Equipo");
            System.out.println("6. Buscar Equipo");
            System.out.println("7. Mostrar Equipos");
            System.out.println("0. Salir");

            opcion = leer.nextInt();

            switch (opcion) {
                case 1:
                    newPlayer();
                    break;
                case 2:
                    findPlayer();
                    break;
                case 3:
                    showAllPlayers();
                    break;
                case 4:
                    newEquipo();
                    break;
                case 5:
                    assignPlayer();
                    break;
                case 6:
                    findEquipo();
                    break;
                case 7:
                    showAllEquipos();
                    break;
                case 0:
                    System.out.println("Nos vemos");
                    break;
                default:
                    System.out.println("Abrite burro");


            }
        }
        while (opcion!=0);

    }

    public static void newPlayer() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el nombre del jugador");
        String name_player = leer.nextLine();
        System.out.println("Ingrese año de nacimiento");
        int year = leer.nextInt();
        System.out.println("Ingrese mes de naciemiento");
        int month = leer.nextInt();
        System.out.println("Ingrese día de nacimiento");
        int day = leer.nextInt();
        savePlayer(new Jugador(name_player, LocalDate.of(year, month, day)));
    }

    public static void newEquipo() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el nombre del tecnico");
        String nombreTecnico = leer.nextLine();
        System.out.println("Ingrese el nombre del equipo");
        String nombreEquipo = leer.nextLine();
        Tecnico tecnico = new Tecnico();
        tecnico.setClub(nombreEquipo);
        tecnico.setName(nombreTecnico);
        Jugador jugadores[] = {};
        saveEquipo(new Equipo(tecnico, jugadores , nombreEquipo));
    }


    public static void findPlayer() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el jugador a buscar");
        readOnePlayer(leer.next());
    }

    public static void assignPlayer(){
        Scanner leer = new Scanner(System.in);

        System.out.println("Equipos ==");
        showAllEquipos();
        System.out.println("Jugadores ==");
        showAllPlayers();
        System.out.println("Ingrese el id del equipo");
        String equipoId = leer.next();
        System.out.println("Ingrese el id del jugador");
        String jugadorId = leer.next();
        Equipo equipo = readOneEquipo(equipoId);
        Jugador jugador = readOnePlayer(jugadorId);
        Jugador[] plantilla = new  Jugador[equipo.getPlantilla().length + 1];
        for (int i = 0; i < equipo.getPlantilla().length; i++) {
            plantilla[i] = equipo.getPlantilla()[i];
        }
        plantilla[plantilla.length - 1] = jugador;
        equipo.setPlantilla(plantilla);
        replaceLine(equipoId, equipo.equipoToCSV());
    }

    public static void showAllPlayers() {
        readPlayers();
    }

    public static void findEquipo() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el jugador a buscar");
        readOneEquipo(leer.next());
    }

    public static void showAllEquipos() {
        readEquipos();
    }
}
