package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Obligatoriedad;
import model.Tarea;

import java.io.IOException;

import static controller.AppController.INSTANCE;

public class CrudTareaController {

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnCrearTarea;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextArea tfDescripcion;
    @FXML
    private TextField tfTiempo;

    @FXML
    private ComboBox<Obligatoriedad> cbObligatoriedad;

    @FXML
    void initialize(){
        //cbObligatoriedad.setItems(FXCollections.observableArrayList(Obligatoriedad.values()));
        cbObligatoriedad.getItems().setAll(Obligatoriedad.values());
    }

    public void onClickCrearTarea( ) throws IOException {

        String nombre = tfNombre.getText();
        String descripcion = tfDescripcion.getText();
        int tiempo = Integer.parseInt(tfTiempo.getText());
        Obligatoriedad obligatoriedad = cbObligatoriedad.getValue();

        boolean obligatoriedadBoolean = recuperarBoolean(obligatoriedad);

        if( nombre.equals("") || descripcion.equals("") || obligatoriedad == null){
            mostrarMensaje("Creación de tarea", "datos invalidos", "Llene todos los campos", Alert.AlertType.WARNING);
        }else{
            PrincipalViewController.getActividadSeleccionada().crearTarea(new Tarea(nombre, descripcion, obligatoriedadBoolean));
            mostrarMensaje("Creación de tarea", "datos validos", "Se ha creado correctamente la tarea", Alert.AlertType.INFORMATION);
            Stage stage = new Stage();
            stage.initOwner(btnCrearTarea.getScene().getWindow());
            btnCrearTarea.getScene().getWindow().hide();
        }
    }


    public void onClickCancelar( ) throws IOException {
        Stage stage = new Stage();
        stage.initOwner(btnCancelar.getScene().getWindow());
        btnCancelar.getScene().getWindow().hide();
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean recuperarBoolean(Obligatoriedad cbObligatoriedad) {
        /*if (cbObligatoriedad == Obligatoriedad.SI){
            return true;
        }
        return false;*/

        return (cbObligatoriedad == Obligatoriedad.SI)?true:false;
    }

}
