package Entraineur.Model;

import User.Model.User;
public class Entraineur extends User {
    private String specialite;


    public Entraineur() {
        super();
        this.specialite = "";
    }


    public Entraineur(int id, String nom, String email, String password, String specialite) {
        super(id, nom, email, password);
        this.specialite = specialite;
    }


    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

    @Override
    public String toString() {
        return "Entraineur{" +
                "specialite='" + specialite + '\'' +
                "} " + super.toString();
    }
}
