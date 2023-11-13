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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return userId.equals(usuario.userId) && password.equals(usuario.password);
    }

}
