package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Proceso;
import org.w3c.dom.Text;

import static controller.AppController.INSTANCE;

public class InfoProcesoController {
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTiempoMin;
    @FXML
    private TextField tfTiempoMax;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnActualizar;
    private Proceso procesoSeleccionado;

    @FXML
    void initialize(){
        procesoSeleccionado = PrincipalViewController.getProcesoSelecionado();

        tfNombre.setText(procesoSeleccionado.getNombre());
        tfId.setText(procesoSeleccionado.getId());
        tfTiempoMin.setText(String.valueOf(procesoSeleccionado.getTiempoMinimo()));
        tfTiempoMax.setText(String.valueOf(procesoSeleccionado.getTiempoMaximo()));

        tfTiempoMin.setEditable(false);
        tfTiempoMax.setEditable(false);

    }
    public void onClickActualizar() {

        String nombre = tfNombre.getText();
        String id = tfId.getText();
        int tiempoMin = Integer.parseInt(tfTiempoMin.getText());
        int tiempoMax = Integer.parseInt(tfTiempoMax.getText());

        Proceso procesoActualizado = new Proceso(id,nombre, tiempoMin, tiempoMax);
        Proceso proceso = INSTANCE.getModel().actualizarProceso(procesoActualizado,procesoSeleccionado.getId());

        if(proceso != null){
            mostrarMensaje("ACTUALIZACION DE PROCESO", "Accion existosa","Se ha actualizado el proceso correctamente", Alert.AlertType.INFORMATION);
        }else{
            mostrarMensaje("ACTUALIZACION DE PROCESO", "Accion fallida", "No se ha actualizado el proceso", Alert.AlertType.WARNING);
        }
        Stage stage = new Stage();
        stage.initOwner(btnActualizar.getScene().getWindow());
        btnActualizar.getScene().getWindow().hide();
    }

    public void onClickCancelar( ) {
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
