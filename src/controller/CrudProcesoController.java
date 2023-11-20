package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Actividad;
import model.Proceso;
import model.Usuario;
import resources.ListaDoble;
import static controller.AppController.INSTANCE;
import java.io.IOException;

public class CrudProcesoController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnCrear;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfTimeMax;

    @FXML
    private TextField tfTimeMin;

    @FXML
    void onCancelarClick(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalViewController.class.getResource("/view/principal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1400, 700);
        Stage stage = new Stage();
        stage.setTitle("USUARIO");
        stage.setScene(scene);
        stage.initOwner(btnCancelar.getScene().getWindow());
        btnCancelar.getScene().getWindow().hide();
        stage.show();

    }

    @FXML
    void onCrearClick(ActionEvent event) throws IOException {
        String name = tfName.getText();
        String id = tfId.getText();
        String timeMax = tfTimeMax.getText();
        String timeMin = tfTimeMin.getText();
        if (validarDatos(name, id, timeMax, timeMin)) {
            Proceso proceso = INSTANCE.getModel().crearProceso(id, name, Integer.parseInt(timeMin), Integer.parseInt(timeMax));
            if (proceso!=null){
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalViewController.class.getResource("/view/principal-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1400, 700);
            Stage stage = new Stage();
            stage.setTitle("USUARIO");
            stage.setScene(scene);
            stage.initOwner(btnCrear.getScene().getWindow());
            btnCrear.getScene().getWindow().hide();
            stage.show();}
            else {
                limpiarCampos();
                mostrarMensaje("Notificación Proceso","Datos invalidos","Ya existe un proceso con el mismo id", Alert.AlertType.WARNING);
            }
        }
        else {
         limpiarCampos();
        }
    }

    private void limpiarCampos() {
        tfId.setText(null);
        tfName.setText(null);
        tfTimeMin.setText(null);
        tfTimeMax.setText(null);
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean validarDatos(String name, String id, String timeMax, String timeMin) {
        String mensaje = "";

        if (name == null || name.equals(""))
            mensaje += "El nombre es invalido \n";

        if (id == null || id.equals(""))
            mensaje += "el id es invalido \n";

        if (timeMax == null || !esNumero(timeMax))
            mensaje += "El tiempo maximo es invalida \n";

        if (timeMin == null || !esNumero(timeMin))
            mensaje += "El tiempo minimo es invalida \n";


        if (mensaje.equals("")) {
            return true;
        } else {
            mostrarMensaje("Notificación Proceso","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
                }
        }
    public static boolean esNumero(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
}

