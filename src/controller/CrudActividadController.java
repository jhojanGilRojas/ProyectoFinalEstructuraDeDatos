package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Actividad;
import model.Obligatoriedad;
import model.Proceso;
import static controller.AppController.INSTANCE;
import java.io.IOException;

public class CrudActividadController {

    Proceso procesoSelecionado;
    Actividad actividadSeleccionada;
    Actividad nuevaActividad;
    int posicion = 0;
    @FXML
    private Button btnCancelar;


    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfName;

    @FXML
    private ComboBox<Obligatoriedad> cbObligatoriedad;

    @FXML
    private Label LabelInsercion;

    @FXML
    private MenuItem mItemAgregarFinal;

    @FXML
    private MenuItem mItemAgregarInicio;

    @FXML
    private MenuItem mItemAgregarPosicion;

    @FXML
    private MenuItem mItemAgregarPostUltima;

    @FXML
    private MenuButton mbtnInsercion;
    @FXML
    void initialize(){
        procesoSelecionado=PrincipalViewController.getProcesoSelecionado();
        cbObligatoriedad.getItems().setAll(Obligatoriedad.values());

    }
    @FXML
    void onAgregarFinal(ActionEvent event) throws IOException {
        String name = tfName.getText();
        String descripcion = tfDescription.getText();
        boolean obligatoriedad = false;
        obligatoriedad = recuperarBoolean(cbObligatoriedad.getValue());

        if (validarDatos(name,descripcion,obligatoriedad)){
            Actividad actividad = procesoSelecionado.crearActividadAlFinal(name,descripcion,obligatoriedad);
            if (actividad!=null){
                mostrarMensaje("Notificación actividad","Información","La Actividad ha sido creada correctamente", Alert.AlertType.INFORMATION);
                FXMLLoader fxmlLoader = new FXMLLoader(PrincipalViewController.class.getResource("/view/principal-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1400, 700);
                Stage stage = new Stage();
                stage.setTitle("USUARIO");
                stage.setScene(scene);
                stage.initOwner(mbtnInsercion.getScene().getWindow());
                mbtnInsercion.getScene().getWindow().hide();
                stage.show();
            }else{
                mostrarMensaje("Notificación actividad","ERROR","La Actividad ya existe", Alert.AlertType.ERROR);
                limpiarCampos();
            }
        }
    }

    @FXML
    void onAgregarPosicion() throws IOException {
        String name = tfName.getText();
        String descripcion = tfDescription.getText();
        boolean obligatoriedad = false;
        obligatoriedad = recuperarBoolean(cbObligatoriedad.getValue());

        if (validarDatos(name,descripcion,obligatoriedad)){
            nuevaActividad = new Actividad(name,descripcion,obligatoriedad);
            Pane root = new Pane();
            root.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            root.setPrefSize(986, 708);
            root.setStyle("-fx-background-color: #1397D4;");
            root.setPadding(new javafx.geometry.Insets(0, 0, 0, 30));

            Label titleLabel = new Label("Proccess Management Systems-UQ");
            titleLabel.setLayoutX(268);
            titleLabel.setLayoutY(14);
            titleLabel.setTextFill(javafx.scene.paint.Color.WHITE);
            titleLabel.setStyle("-fx-font-size: 30;");
            root.getChildren().add(titleLabel);

            Pane contentPane = new Pane();
            contentPane.setLayoutX(102);
            contentPane.setLayoutY(78);
            contentPane.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            contentPane.setPrefSize(782, 552);
            contentPane.setStyle("-fx-background-color: #ffffff;");
            root.getChildren().add(contentPane);

            Label creationLabel = new Label("Creación de actividad");
            creationLabel.setLayoutX(186);
            creationLabel.setLayoutY(23);
            creationLabel.setPrefSize(410, 35);
            creationLabel.setTextFill(javafx.scene.paint.Color.valueOf("#1397d4"));
            creationLabel.setStyle("-fx-font-size: 30;");
            contentPane.getChildren().add(creationLabel);

            Button cancelButton = new Button("CANCELAR");
            cancelButton.setLayoutX(466);
            cancelButton.setLayoutY(422);
            cancelButton.setPrefSize(184, 41);
            cancelButton.setStyle("-fx-background-color: #1397D4; -fx-font-size: 25;");

            contentPane.getChildren().add(cancelButton);

            Label insertionLabel = new Label();
            insertionLabel.setLayoutX(33);
            insertionLabel.setLayoutY(288);
            insertionLabel.setPrefSize(300, 39);
            insertionLabel.setStyle("-fx-font-size: 22;");
            contentPane.getChildren().add(insertionLabel);

            Button addButton = new Button("AGREGAR");
            addButton.setLayoutX(157);
            addButton.setLayoutY(422);
            addButton.setPrefSize(184, 43);
            addButton.setStyle("-fx-background-color: #1397D4; -fx-font-size: 25;");

            contentPane.getChildren().add(addButton);

            Label infoLabel = new Label("Elige la actividad, para agregar la nueva actividad posterior a esta");
            infoLabel.setLayoutX(99);
            infoLabel.setLayoutY(113);
            infoLabel.setPrefSize(584, 83);
            infoLabel.setWrapText(true);
            infoLabel.setStyle("-fx-font-size: 22;");
            contentPane.getChildren().add(infoLabel);

            ComboBox<Actividad> cbCargarActividades = new ComboBox<>();
            cbCargarActividades.setLayoutX(241);
            cbCargarActividades.setLayoutY(269);
            cbCargarActividades.setPrefSize(300, 39);
            cbCargarActividades.setStyle("-fx-background-color: #1397D4;");
            contentPane.getChildren().add(cbCargarActividades);
            cbCargarActividades.getItems().setAll(procesoSelecionado.getListaActividadesArray());


            Stage stage = new Stage();
            Scene scene = new Scene(root, 986, 708);
            stage.setScene(scene);
            stage.initOwner(mbtnInsercion.getScene().getWindow());
            mbtnInsercion.getScene().getWindow().hide();
            stage.show();

            cancelButton.setOnAction(event -> {
                stage.close();

            });
            addButton.setOnAction(event -> {
                actividadSeleccionada = cbCargarActividades.getValue();
                if (actividadSeleccionada!=null) {

                    posicion = procesoSelecionado.buscarActividadIndice(actividadSeleccionada);
                    Actividad actividad = procesoSelecionado.crearActividadPosicionDeterminada(nuevaActividad, posicion);
                    if (actividad != null) {
                        mostrarMensaje("Notificación actividad", "Información", "La Actividad ha sido creada correctamente", Alert.AlertType.INFORMATION);
                        stage.close();
                        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalViewController.class.getResource("/view/principal-view.fxml"));
                        Scene scene1 = null;
                        try {
                            scene1 = new Scene(fxmlLoader.load(), 1400, 700);
                            Stage stage1 = new Stage();
                            stage1.setTitle("USUARIO");
                            stage1.setScene(scene1);
                            stage1.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    } else {
                        mostrarMensaje("Notificación actividad", "ERROR", "La Actividad ya existe", Alert.AlertType.ERROR);
                        limpiarCampos();
                    }
                }
                else{
                    mostrarMensaje("Notificación actividad", "ERROR", "Selecione una actividad", Alert.AlertType.ERROR);

                }
            });

        }
    }

    @FXML
    void onAgregarPostUltima(ActionEvent event) throws IOException {

        String name = tfName.getText();
        String descripcion = tfDescription.getText();
        boolean obligatoriedad = false;
        obligatoriedad = recuperarBoolean(cbObligatoriedad.getValue());

        if (validarDatos(name,descripcion,obligatoriedad)){
            Actividad actividad1 = new Actividad(name,descripcion,obligatoriedad);
            Actividad actividad = procesoSelecionado.crearActividadDespuesDeUltima(actividad1);
            if (actividad!=null){
                mostrarMensaje("Notificación actividad","Información","La Actividad ha sido creada correctamente", Alert.AlertType.INFORMATION);
                FXMLLoader fxmlLoader = new FXMLLoader(PrincipalViewController.class.getResource("/view/principal-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1400, 700);
                Stage stage = new Stage();
                stage.setTitle("USUARIO");
                stage.setScene(scene);
                stage.initOwner(mbtnInsercion.getScene().getWindow());
                mbtnInsercion.getScene().getWindow().hide();
                stage.show();
            }else{
                mostrarMensaje("Notificación actividad","ERROR","La Actividad ya existe", Alert.AlertType.ERROR);
                limpiarCampos();
            }
        }
    }

    @FXML
    void onAgregrarInicio(ActionEvent event) throws IOException {
    String name = tfName.getText();
    String descripcion = tfDescription.getText();
    boolean obligatoriedad = false;
    obligatoriedad = recuperarBoolean(cbObligatoriedad.getValue());

    if (validarDatos(name,descripcion,obligatoriedad)){
        Actividad actividad = procesoSelecionado.crearActividad(name,descripcion,obligatoriedad);
        if (actividad!=null){
            mostrarMensaje("Notificación actividad","Información","La Actividad ha sido creada correctamente", Alert.AlertType.INFORMATION);
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalViewController.class.getResource("/view/principal-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1400, 700);
            Stage stage = new Stage();
            stage.setTitle("USUARIO");
            stage.setScene(scene);
            stage.initOwner(mbtnInsercion.getScene().getWindow());
            mbtnInsercion.getScene().getWindow().hide();
            stage.show();
        }else{
            mostrarMensaje("Notificación actividad","ERROR","La Actividad ya existe", Alert.AlertType.ERROR);
            limpiarCampos();
        }
    }
    }

    private boolean recuperarBoolean(Obligatoriedad cbObligatoriedad) {
        if (cbObligatoriedad == Obligatoriedad.SI){
            return true;
        }
        return false;
    }


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
    private void limpiarCampos() {
        tfName.setText("");
        tfDescription.setText("");
        cbObligatoriedad=null;
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
    private boolean validarDatos(String name, String descripcion, boolean obligatoriedad) {
        String mensaje = "";

        if (name == null || name.equals(""))
            mensaje += "El nombre es invalido \n";

        if (descripcion == null || descripcion.equals(""))
            mensaje += "La descripción es invalida \n";


        if (mensaje.equals("")) {
            return true;
        } else {
            mostrarMensaje("Notificación Usuario","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

}
