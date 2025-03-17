package Entraineur.Model;

import User.Model.User;

public class Entraineur extends User {
    private String specialite;


    public Entraineur(int id, String nom, String email, String password, String specialite) {
        super(id, nom, email, password);
        this.specialite = specialite;
    }


    public Entraineur() {
        super();
    }


    public Entraineur(int id, String nom, String email, String password) {
        super(id, nom, email, password);
        this.specialite = null; // Valeur par défaut si non spécifiée
    }


    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

    @Override
    public String getRole() {
        return "entraineur";
    }

    @Override
    public String toString() {
        return "Entraineur{" +
                "specialite='" + specialite + '\'' +
                "} " + super.toString();
    }
}