package operations;

import models.Equipo;
import models.Jugador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OperationJugador {
    public static void savePlayer(Jugador jugadorToSave) {
        try {
            FileWriter escribir = new FileWriter("players.csv", true);
            escribir.write(jugadorToSave.playerToCSV());
            escribir.write("\n");
            escribir.close();
            System.out.println("Player Saved");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readPlayers() {
        try {
            FileReader lector = new FileReader("players.csv");
            BufferedReader buffer = new BufferedReader(lector);
            String playerReader;

            while ((playerReader = buffer.readLine()) != null) {
                System.out.println(playerReader);
            }
            buffer.close();
            System.out.println("All players have been read");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Jugador readOnePlayer(String id) {
        try {
            FileReader lector = new FileReader("players.csv");
            BufferedReader buffer = new BufferedReader(lector);
            String playerReader;

            while ((playerReader = buffer.readLine()) != null) {

                String[] data_readed = playerReader.split(",");
                Jugador temp_player = new Jugador();
                temp_player.setId(data_readed[0]);
                temp_player.name = data_readed[1];
                temp_player.dorsal = Integer.parseInt(data_readed[2]);
                temp_player.is_alive = Boolean.parseBoolean(data_readed[4]);

                if (temp_player.getId().equals(id)) {
                    return temp_player;
                }

            }
            buffer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
