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

import static controller.AppController.INSTANCE;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button btnLogin;

    @FXML
    protected void onLoginButtonClick() throws Exception {

        String email = emailField.getText();
        String password = passwordField.getText();
        Usuario usuario = new Usuario(email,password,Rol.USUARIO_REGULAR);
        Usuario userLogin = INSTANCE.getModel().iniciarSesion(usuario);

        if (userLogin == null) {
            emailField.setText("");
            passwordField.setText("");
            mostrarMensaje("Notificación usuario", "Error", "Credenciales incorrectas", Alert.AlertType.ERROR);

        } else{

            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalViewController.class.getResource("/view/principal-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1400, 700);
            Stage stage = new Stage();
            stage.setTitle("USUARIO");
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