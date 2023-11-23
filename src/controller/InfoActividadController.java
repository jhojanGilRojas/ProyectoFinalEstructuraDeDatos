package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Actividad;
import model.Obligatoriedad;

import static controller.AppController.INSTANCE;
public class InfoActividadController {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextArea tfDescripcion;
    @FXML
    private ComboBox<Obligatoriedad> cbObligatoriedad;
    @FXML
    private TextField tfTiempo;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnCancelar;



    private Actividad actividadSeleccionada;

    @FXML
    void initialize(){
        cbObligatoriedad.getItems().addAll(Obligatoriedad.values());
        actividadSeleccionada = PrincipalViewController.getActividadSeleccionada();
        tfNombre.setText(actividadSeleccionada.getNombre());
        tfDescripcion.setText(actividadSeleccionada.getDescripcion());
        tfTiempo.setText(String.valueOf(actividadSeleccionada.getTiempoMax()));

        tfTiempo.setEditable(false);

        if(actividadSeleccionada.isObligatoriedad()){
            cbObligatoriedad.setValue(Obligatoriedad.SI);
        }else{
            cbObligatoriedad.setValue(Obligatoriedad.NO);
        }
    }

    public void onClickActualizar() {
        String nombre = tfNombre.getText();
        String descripcion  = tfDescripcion.getText();
        Obligatoriedad obligatoriedad = cbObligatoriedad.getValue();
        Actividad nuevaActividad;
        if( obligatoriedad.equals(Obligatoriedad.SI)){
            nuevaActividad = new Actividad(nombre, descripcion, true);
        }else{
            nuevaActividad = new Actividad(nombre, descripcion, false);
        }

        Actividad actividadActualizada = PrincipalViewController.getProcesoSelecionado().actualizarActividad(nuevaActividad, actividadSeleccionada.getNombre());

        if(actividadActualizada != null){
            mostrarMensaje("ACTUALIZACION ACTIVIDAD", "Accion existosa", "Se ha actulizado la actividad correctamente", Alert.AlertType.INFORMATION);
        }else{
            mostrarMensaje("ACTUALIZACION ACTIVIDAD", "Accion fallida", "No se ha actualizado la actividad", Alert.AlertType.ERROR);
        }

        Stage stage = new Stage();
        stage.initOwner(btnActualizar.getScene().getWindow());
        btnActualizar.getScene().getWindow().hide();

    }

    public void onClickCancelar() {
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
}
