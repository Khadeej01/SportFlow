package Seance.Model;

import java.util.Date;

public class Seance {
    private int id;
    private int membreId;
    private int entraineurId;
    private Date dateHeure;


    public Seance() {}

    public Seance(int id, int membreId, int entraineurId, Date dateHeure) {
        this.id = id;
        this.membreId = membreId;
        this.entraineurId = entraineurId;
        this.dateHeure = dateHeure;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMembreId() { return membreId; }
    public void setMembreId(int membreId) { this.membreId = membreId; }
    public int getEntraineurId() { return entraineurId; }
    public void setEntraineurId(int entraineurId) { this.entraineurId = entraineurId; }
    public Date getDateHeure() { return dateHeure; }
    public void setDateHeure(Date dateHeure) { this.dateHeure = dateHeure; }

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", membreId=" + membreId +
                ", entraineurId=" + entraineurId +
                ", dateHeure=" + dateHeure +
                '}';
    }
}