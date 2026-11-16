package operations;

import models.partido;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OperationPartido {

    public static void savePartido(partido partidoToSave) {
        try {
            FileWriter escribir = new FileWriter("partido.csv", true);
            escribir.write(partidoToSave.partidoToCSV());
            escribir.write("\n");
            escribir.close();
            System.out.println("match Saved");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readmatch() {
        try {
            FileReader lector = new FileReader("partido.csv");
            BufferedReader buffer = new BufferedReader(lector);
            String partidoReader;

            while ((partidoReader = buffer.readLine()) != null) {
                System.out.println(partidoReader);
            }
            buffer.close();
            System.out.println("All match have been read");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
