package Admin.Model;
import User.Model.User;

public class Admin extends User {
    private String role;


    public Admin() {
        super();
        this.role = "admin";
    }


    public Admin(int id, String nom, String email, String password, String role) {
        super(id, nom, email, password);
        this.role = role;
    }

    public Admin(int id, String nom, String email, String password) {
    }


    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
