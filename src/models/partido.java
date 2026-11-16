package models;
import java.util.UUID;

public class partido {
    private String Id;
    private String  equipoLocal;
    private String equipoVisitante;

    public partido(String equipoLocal, String equipoVisitante) {
        this.setId();
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }
    public void setId(String Id) {
        this.Id = Id;
    }
    public void setId() {
        this.Id = UUID.randomUUID().toString().substring(0, 2);
    }

    public String partidoToCSV() {
        return Id + "," + equipoLocal + "," + equipoVisitante;
    }
}
