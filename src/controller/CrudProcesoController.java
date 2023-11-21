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
    void onCancelarClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.initOwner(btnCancelar.getScene().getWindow());
        btnCancelar.getScene().getWindow().hide();
    }

    @FXML
    void onCrearClick() throws IOException {
        String name = tfName.getText();
        String id = tfId.getText();
        if (validarDatos(name, id)) {
            Proceso proceso = INSTANCE.getModel().crearProceso(id, name);
            if (proceso==null){
                limpiarCampos();
                mostrarMensaje("Notificación Proceso","Datos invalidos","Ya existe un proceso con el mismo id", Alert.AlertType.WARNING);
            }
        }else{
         limpiarCampos();
        }
        Stage stage = new Stage();
        stage.initOwner(btnCrear.getScene().getWindow());
        btnCancelar.getScene().getWindow().hide();
    }

    private void limpiarCampos() {
        tfId.setText(null);
        tfName.setText(null);
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean validarDatos(String name, String id) {
        String mensaje = "";

        if (name == null || name.equals(""))
            mensaje += "El nombre es invalido \n";

        if (id == null || id.equals(""))
            mensaje += "el id es invalido \n";

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

