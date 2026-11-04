package models;

import java.util.UUID;

public class Equipo {
    private String id;
    private Tecnico tecnicoEquipo;
    private Jugador[] plantilla;
    private String teamName;

    public Equipo(){}

    public Equipo(Tecnico tecnicoEquipo, Jugador[] plantilla, String teamName) {
        this.setId();
        this.tecnicoEquipo = tecnicoEquipo;
        this.plantilla = plantilla;
        this.teamName = teamName;
    }

    public Tecnico getTecnicoEquipo() {
        return tecnicoEquipo;
    }

    public void setTecnicoEquipo(Tecnico tecnicoEquipo) {
        this.tecnicoEquipo = tecnicoEquipo;
    }

    public Jugador[] getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(Jugador[] plantilla) {
        this.plantilla = plantilla;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString().substring(0, 4);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String equipoToCSV() {
        StringBuilder players = new StringBuilder("{");
        for (int i = 0; i < plantilla.length; i++) {
            players.append(plantilla[i].getId());
            if (i < plantilla.length - 1) {
                players.append("<>");
            }
        }
        players.append("}");
        return this.getId() + "," + this.getTeamName() + "," + this.getTecnicoEquipo().getName() + "," + players;
    }
}
