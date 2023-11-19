package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Insercion;

public class CrudActividadController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnCrear;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;

    @FXML
    private ComboBox cbInsercion;

    @FXML
    void initialize(){
        cbInsercion.getItems().setAll(Insercion.values());
    }
    @FXML
    void onCancelarClick(ActionEvent event) {

    }

    @FXML
    void onCrearClick(ActionEvent event) {

    }

}
