package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Rol;
import model.Usuario;


import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button btnLogin;

    @FXML
    protected void onLoginButtonClick() throws IOException {

        String email = emailField.getText();
        String password = passwordField.getText();

        Usuario userLogin = INSTANCE.getModel().autenticar(email, password);

        if(userLogin == null){
            emailField.setText("");
            passwordField.setText("");
            mostrarMensaje("Notificación usuario","Error","Credenciales incorrectas", Alert.AlertType.ERROR);

        }else if(userLogin.getRol() == Rol.U_REGULAR){
            Parent parent = FXMLLoader.load(MainApp.class.getResource("principal-view.fxml"));
            Scene scene = new Scene(parent, 1360, 760);
            Stage stage = new Stage();
            stage.setTitle("USUARIO");
            stage.setScene(scene);
            stage.initOwner(btnLogin.getScene().getWindow());
            btnLogin.getScene().getWindow().hide();

            stage.show();
        }else{
            Parent parent = FXMLLoader.load(MainApp.class.getResource("principal-view.fxml"));
            Scene scene = new Scene(parent, 1360, 760);
            Stage stage = new Stage();
            stage.setTitle("ADMINISTRADOR");
            stage.setScene(scene);
            stage.initOwner(btnLogin.getScene().getWindow());
            btnLogin.getScene().getWindow().hide();
            stage.show();
        }

    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

}