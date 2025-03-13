package Seance.Model;
import java.util.Date;
public class Seance {

    private int idSeance;
    private int idMembre;
    private int idEntraineur;
    private Date dateHeure;


    public Seance() {
        this.idSeance = 0;
        this.idMembre = 0;
        this.idEntraineur = 0;
        this.dateHeure = null;
    }

    public Seance(int idSeance, int idMembre, int idEntraineur, Date dateHeure) {
        this.idSeance = idSeance;
        this.idMembre = idMembre;
        this.idEntraineur = idEntraineur;
        this.dateHeure = dateHeure;
    }


    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public int getIdEntraineur() {
        return idEntraineur;
    }

    public void setIdEntraineur(int idEntraineur) {
        this.idEntraineur = idEntraineur;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "idSeance=" + idSeance +
                ", idMembre=" + idMembre +
                ", idEntraineur=" + idEntraineur +
                ", dateHeure=" + dateHeure +
                '}';
    }
}
