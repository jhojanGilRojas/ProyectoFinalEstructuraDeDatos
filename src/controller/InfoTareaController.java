package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Actividad;
import model.Obligatoriedad;
import model.Tarea;

public class InfoTareaController {

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
    private Tarea tareaSeleccionada;
    private Actividad actividadSeleccionada;

    @FXML
    void initialize(){
        cbObligatoriedad.getItems().addAll(Obligatoriedad.values());
        tareaSeleccionada = PrincipalViewController.getTareaSeleccionada();
        actividadSeleccionada = PrincipalViewController.getActividadSeleccionada();


        tfNombre.setText(tareaSeleccionada.getNombre());
        tfDescripcion.setText(tareaSeleccionada.getDescripcion());
        tfTiempo.setText(String.valueOf(actividadSeleccionada.getTiempoMax()));
        if(tareaSeleccionada.isObligatoriedad()){
            cbObligatoriedad.setValue(Obligatoriedad.SI);
        }else{
            cbObligatoriedad.setValue(Obligatoriedad.NO);
        }
    }

    public void onClickActualizar(){
        String nombre = tfNombre.getText();
        String descripcion  = tfDescripcion.getText();
        Obligatoriedad obligatoriedad = cbObligatoriedad.getValue();
        int tiempo = Integer.parseInt(tfTiempo.getText());
        Tarea nuevaTarea;
        if( obligatoriedad.equals(Obligatoriedad.SI)){
            nuevaTarea = new Tarea(nombre, descripcion, true, tiempo);
        }else{
            nuevaTarea = new Tarea(nombre, descripcion, false, tiempo);
        }
        Tarea tareaActualizada = actividadSeleccionada.actualizarTarea(nuevaTarea, nombre );
        actividadSeleccionada.calcularTiempoMax();

        if(tareaActualizada != null){
            mostrarMensaje("ACTUALIZACION TAREA", "Accion existosa", "Se ha actulizado la tarea correctamente", Alert.AlertType.INFORMATION);
        }else{
            mostrarMensaje("ACTUALIZACION TAREA", "Accion fallida", "No se ha actualizado la tarea", Alert.AlertType.ERROR);
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
