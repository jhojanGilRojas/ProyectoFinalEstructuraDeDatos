package model;

public class Usuario {
    private String userId;
    private String password;

    private Rol rol;

    public Usuario(String userId, String password, Rol rol) {
        this.userId = userId;
        this.password = password;
        this.rol = rol;
    }

    private void autenticarse(String userId, String password, Rol rol){

        if (this.userId.equals(userId) && this.password.equals(password) && this.rol.equals(rol)){
            System.out.println("Usuario Autenticado");
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}