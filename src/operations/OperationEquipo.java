package operations;

import models.Equipo;
import models.Jugador;
import models.Tecnico;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static operations.OperationJugador.readOnePlayer;

public class OperationEquipo {
    public static String EQUIPO_FILE = "equipo.csv";

    public static void saveEquipo(Equipo equipoToSave) {
        try {
            FileWriter escribir = new FileWriter(EQUIPO_FILE, true);
            escribir.write(equipoToSave.equipoToCSV());
            escribir.write("\n");
            escribir.close();
            System.out.println("Equipo Saved");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readEquipos() {
        try {
            FileReader lector = new FileReader(EQUIPO_FILE);
            BufferedReader buffer = new BufferedReader(lector);
            String equipoReader;

            while ((equipoReader = buffer.readLine()) != null) {
                System.out.println(equipoReader);
            }
            buffer.close();
            System.out.println("All equipos have been read");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Equipo readOneEquipo(String id) {
        try {
            FileReader lector = new FileReader(EQUIPO_FILE);
            BufferedReader buffer = new BufferedReader(lector);
            String equipoReader;

            while ((equipoReader = buffer.readLine()) != null) {
                String[] data_readed = equipoReader.split(",");
                Equipo equipo = new Equipo();
                System.out.println(data_readed[0]);
                equipo.setId(data_readed[0]);
                equipo.setTeamName(data_readed[1]);
                Tecnico tecnico = new Tecnico();
                tecnico.setName(data_readed[2]);
                equipo.setTecnicoEquipo(tecnico);
                String[] jugadoresIds = data_readed[3].replace("{", "").replace("}", "").split("<>");
                if (data_readed[3].contains("<>")) {
                    Jugador[] jugadores = new Jugador[jugadoresIds.length];
                    for (int i = 0; i < jugadoresIds.length; i++) {
                        jugadores[i] = readOnePlayer(jugadoresIds[i]);
                    }
                    equipo.setPlantilla(jugadores);
                } else {
                    equipo.setPlantilla(new Jugador[0]);
                }
                if (equipo.getId().equals(id)) {
                    System.out.println("Equipo read");
                    return equipo;
                }
            }
            buffer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void replaceLine(String id, String newLine) {
        Path path = Paths.get(EQUIPO_FILE);
        try {
            List<String> lineas = Files.readAllLines(path);
            List<String> lineasModificadas = lineas.stream()
                    .map(linea -> linea.contains(id) ? newLine : linea)
                    .collect(Collectors.toList());

            Files.write(path, lineasModificadas);
            System.out.println("Archivo actualizado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
