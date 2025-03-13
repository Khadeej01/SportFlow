package Membre.Model;


import User.Model.User;

import java.util.Date;


public class Membre extends User {
    private Date dateNaissance;
    private String sportPratique;


    public Membre() {
        super();
        this.dateNaissance = null;
        this.sportPratique = "";
    }


    public Membre(int id, String nom, String email, String password, Date dateNaissance, String sportPratique) {
        super(id, nom, email, password);
        this.dateNaissance = dateNaissance;
        this.sportPratique = sportPratique;
    }


    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getSportPratique() { return sportPratique; }
    public void setSportPratique(String sportPratique) { this.sportPratique = sportPratique; }

    @Override
    public String toString() {
        return "Membre{" +
                "dateNaissance=" + dateNaissance +
                ", sportPratique='" + sportPratique + '\'' +
                "} " + super.toString();
    }
}