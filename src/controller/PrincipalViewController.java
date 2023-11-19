package controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Proceso;

import static controller.AppController.INSTANCE;
import java.io.IOException;
import java.util.ArrayList;

public class PrincipalViewController {

    @FXML
    private AnchorPane contentProccess;
    @FXML
    private AnchorPane contentActivies;
    @FXML
    private AnchorPane contentTaks;
    @FXML
    private Button btnCrearProceso;
    @FXML
    private Button btnAddActivity;
    @FXML
    private Button btnAddTask;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnEliminarProceso;
    @FXML
    private Button btnEditarProceso;

    @FXML
    private Button btnCrudUsuarios;
    @FXML
    private Pane contentMain;
    @FXML
    Pane paneSeleccionado;
    Proceso procesoSelecionado;
    private double proccessCurrentY = 0;
    private double activiesCurrentY = 0;
    private double taskCurrentY = 0;
    private int n = 0;
    int changeColor = 0;

    @FXML
    void initialize() {

        ArrayList<Proceso> procesosData = INSTANCE.getModel().getListaProcesosArray();
        for (Proceso proceso:procesosData) {
            if (proceso != null){
                Pane newPane = new Pane();
                newPane.setPrefSize(100, 100); // Tamaño preferido del nuevo Pane
                Label name;
                Label id;
                Label timeMin;
                Label timeMax;
                newPane.setStyle("-fx-background-color: gray;");
                n = n+1;
                Label text = new Label("Proceso "+n );
                name = new Label("Nombre: "+proceso.getNombre());
                id = new Label("Id: "+proceso.getId());
                timeMin = new Label(("Tiempo mínimo: "+proceso.getTiempoMinimo()));
                timeMax = new Label(("Tiempo máximo: "+proceso.getTiempoMaximo()));


                // Posicionar el nuevo Pane debajo del último
                newPane.setLayoutY(proccessCurrentY);
                proccessCurrentY += newPane.getPrefHeight() + 10; // 10 es el espacio entre los Pane
                newPane.setId("pane"+n);
                newPane.getChildren().addAll(text,name,id,timeMin,timeMax);
                name.setLayoutY(15);
                id.setLayoutY(40);
                timeMin.setLayoutY(60);
                timeMax.setLayoutY(80);

                ;
                // Agregar el nuevo Pane al Pane con margen
                contentProccess.setLeftAnchor(newPane, 50.0);
                contentProccess.getChildren().add(newPane);

                newPane.setOnMouseClicked(event -> {

                    if(changeColor == 0){
                        newPane.setStyle("-fx-background-color: lightblue");
                        paneSeleccionado = newPane;
                        procesoSelecionado = proceso;

                        changeColor = 1;
                    }else{
                        newPane.setStyle("-fx-background-color: gray");
                        changeColor = 0;
                    }
                    System.out.println(newPane.getId());
                });
            }

        }
        Image img = new Image("/resources/addActivity.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(30);
        view.setFitWidth(30);
        view.setPreserveRatio(true);

        Image img1 = new Image("/resources/addActivity.png");
        ImageView view1 = new ImageView(img1);
        view1.setFitHeight(30);
        view1.setFitWidth(30);
        view1.setPreserveRatio(true);

        btnAddActivity.setGraphic(view);
        btnAddTask.setGraphic(view1);

    }

    public void onClickCrearProceso( ) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(CrudProcesoController.class.getResource("/view/crearProceso-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        Stage stage = new Stage();
        stage.setTitle("Crear procesos");
        stage.setScene(scene);
        stage.initOwner(btnCrearProceso.getScene().getWindow());
        btnCrearProceso.getScene().getWindow().hide();
        stage.show();
//        Pane newPane = new Pane();
//        newPane.setPrefSize(100, 100); // Tamaño preferido del nuevo Pane
//        newPane.setStyle("-fx-background-color: gray;");
//        n = n+1;
//        Label text = new Label("Proceso "+n );
//
//        // Posicionar el nuevo Pane debajo del último
//        newPane.setLayoutY(proccessCurrentY);
//        proccessCurrentY += newPane.getPrefHeight() + 10; // 10 es el espacio entre los Pane
//        newPane.setId("pane"+n);
//        newPane.getChildren().add(text);
//        // Agregar el nuevo Pane al Pane con margen
//        contentProccess.setLeftAnchor(newPane, 50.0);
//        contentProccess.getChildren().add(newPane);
//
//
//        newPane.setOnMouseClicked(event -> {
//
//            if(changeColor == 0){
//                newPane.setStyle("-fx-background-color: lightblue");
//                changeColor = 1;
//            }else{
//                newPane.setStyle("-fx-background-color: gray");
//                changeColor = 0;
//            }
//            System.out.println(newPane.getId());
//        });

    }
    public  void onEditarProceso() throws IOException {
        if (paneSeleccionado != null){

            Pane rootPane = new Pane();
            rootPane.setMaxHeight(Double.NEGATIVE_INFINITY);
            rootPane.setMaxWidth(Double.NEGATIVE_INFINITY);
            rootPane.setMinHeight(Double.NEGATIVE_INFINITY);
            rootPane.setMinWidth(Double.NEGATIVE_INFINITY);
            rootPane.setPrefHeight(708.0);
            rootPane.setPrefWidth(986.0);
            rootPane.setStyle("-fx-background-color: #1397D4;");

            Label titleLabel = new Label("Proccess Management Systems-UQ");
            titleLabel.setLayoutX(268.0);
            titleLabel.setLayoutY(14.0);
            titleLabel.setTextFill(javafx.scene.paint.Color.WHITE);
            titleLabel.setFont(new javafx.scene.text.Font("SimSun", 30.0));

            Pane innerPane = new Pane();
            innerPane.setFocusTraversable(true);
            innerPane.setLayoutX(102.0);
            innerPane.setLayoutY(78.0);
            innerPane.setMaxHeight(Double.NEGATIVE_INFINITY);
            innerPane.setMaxWidth(Double.NEGATIVE_INFINITY);
            innerPane.setMinHeight(Double.NEGATIVE_INFINITY);
            innerPane.setMinWidth(Double.NEGATIVE_INFINITY);
            innerPane.setPrefHeight(552.0);
            innerPane.setPrefWidth(782.0);
            innerPane.setStyle("-fx-background-color: #ffffff;");

            Button btnCrear = new Button("ACTUALIZAR");
            btnCrear.setId("btnCrear");
            btnCrear.setEllipsisString("");
            btnCrear.setLayoutX(145.0);
            btnCrear.setLayoutY(422.0);
            btnCrear.setMnemonicParsing(false);
            btnCrear.setPrefHeight(41.0);
            btnCrear.setPrefWidth(184.0);
            btnCrear.setStyle("-fx-background-color: #1397D4;");
            btnCrear.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            btnCrear.setTextFill(javafx.scene.paint.Color.WHITE);
            btnCrear.setWrapText(true);
            btnCrear.setFont(new javafx.scene.text.Font("SimSun", 25.0));

            Label titleLabel2 = new Label("Actualizanción de proceso");
            titleLabel2.setAlignment(javafx.geometry.Pos.CENTER);
            titleLabel2.setLayoutX(186.0);
            titleLabel2.setLayoutY(21.0);
            titleLabel2.setPrefHeight(35.0);
            titleLabel2.setPrefWidth(410.0);
            titleLabel2.setText("Actualizanción de proceso");
            titleLabel2.setTextFill(javafx.scene.paint.Color.web("#1397d4"));
            titleLabel2.setFont(new javafx.scene.text.Font("SimSun", 30.0));

            Label labelName = new Label("Nombre");
            labelName.setLayoutX(119.0);
            labelName.setLayoutY(90.0);
            labelName.setFont(new javafx.scene.text.Font("SimSun", 22.0));

            Label labelId = new Label("ID");
            labelId.setLayoutX(144.0);
            labelId.setLayoutY(159.0);
            labelId.setFont(new javafx.scene.text.Font("SimSun", 22.0));

            Label labelTimeMin = new Label("Tiempo mínimo");
            labelTimeMin.setLayoutX(89.0);
            labelTimeMin.setLayoutY(226.0);
            labelTimeMin.setFont(new javafx.scene.text.Font("SimSun", 22.0));

            Label labelTimeMax = new Label("Tiempo máximo");
            labelTimeMax.setLayoutX(89.0);
            labelTimeMax.setLayoutY(292.0);
            labelTimeMax.setFont(new javafx.scene.text.Font("SimSun", 22.0));

            TextField tfName = new TextField();
            tfName.setAlignment(javafx.geometry.Pos.CENTER);
            tfName.setLayoutX(316.0);
            tfName.setLayoutY(81.0);
            tfName.setPrefHeight(39.0);
            tfName.setPrefWidth(300.0);
            tfName.setStyle("-fx-background-color: #1397D4;");
            tfName.setFont(new javafx.scene.text.Font(18.0));

            TextField tfId = new TextField();
            tfId.setAlignment(javafx.geometry.Pos.CENTER);
            tfId.setLayoutX(316.0);
            tfId.setLayoutY(150.0);
            tfId.setPrefHeight(39.0);
            tfId.setPrefWidth(300.0);
            tfId.setStyle("-fx-background-color: #1397D4;");
            tfId.setFont(new javafx.scene.text.Font(18.0));

            TextField tfTimeMin = new TextField();
            tfTimeMin.setAlignment(javafx.geometry.Pos.CENTER);
            tfTimeMin.setLayoutX(316.0);
            tfTimeMin.setLayoutY(218.0);
            tfTimeMin.setPrefHeight(39.0);
            tfTimeMin.setPrefWidth(300.0);
            tfTimeMin.setStyle("-fx-background-color: #1397D4;");
            tfTimeMin.setFont(new javafx.scene.text.Font(18.0));

            TextField tfTimeMax = new TextField();
            tfTimeMax.setAlignment(javafx.geometry.Pos.CENTER);
            tfTimeMax.setLayoutX(316.0);
            tfTimeMax.setLayoutY(283.0);
            tfTimeMax.setPrefHeight(39.0);
            tfTimeMax.setPrefWidth(300.0);
            tfTimeMax.setStyle("-fx-background-color: #1397D4;");
            tfTimeMax.setFont(new javafx.scene.text.Font(18.0));

            Button btnCancelar = new Button("CANCELAR");
            btnCancelar.setId("btnCancelar");
            btnCancelar.setEllipsisString("");
            btnCancelar.setLayoutX(466.0);
            btnCancelar.setLayoutY(422.0);
            btnCancelar.setMnemonicParsing(false);
            btnCancelar.setPrefHeight(41.0);
            btnCancelar.setPrefWidth(184.0);
            btnCancelar.setStyle("-fx-background-color: #1397D4;");
            btnCancelar.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            btnCancelar.setTextFill(javafx.scene.paint.Color.WHITE);
            btnCancelar.setWrapText(true);
            btnCancelar.setFont(new javafx.scene.text.Font("SimSun", 25.0));

            innerPane.getChildren().addAll(
                    btnCrear, titleLabel2,labelName, labelId, labelTimeMin, labelTimeMax,
                    tfName, tfId, tfTimeMin, tfTimeMax, btnCancelar
            );
            tfName.setText(procesoSelecionado.getNombre());
            tfId.setText(procesoSelecionado.getId());
            tfTimeMin.setText(String.valueOf(procesoSelecionado.getTiempoMinimo()));
            tfTimeMax.setText(String.valueOf(procesoSelecionado.getTiempoMaximo()));

            rootPane.getChildren().addAll(titleLabel, innerPane);
            Stage stage = new Stage();
            Scene scene = new Scene(rootPane, 986, 708);
            stage.setScene(scene);
            stage.show();

            btnCrear.setOnAction(actionEvent -> {
                String name = tfName.getText();
                String id = tfId.getText();
                String timeMax = tfTimeMax.getText();
                String timeMin = tfTimeMin.getText();
                if(validarDatos(name,id,timeMax,timeMin)){
                    Proceso procesoEditado = new Proceso(id, name, Integer.parseInt(timeMin), Integer.parseInt(timeMax));
                    procesoSelecionado = INSTANCE.getModel().actualizarProceso(procesoEditado,procesoSelecionado.getId());
                    mostrarMensaje("Informacion proceso","","El proceso ha sido actualizado.", Alert.AlertType.INFORMATION);
                    stage.close();
            }});
            btnCancelar.setOnAction(actionEvent1 -> {
                stage.close();
            });

        }else {
            mostrarMensaje("Informacion proceso","ERROR","Seleccione el proceso que desea editar", Alert.AlertType.ERROR);
        }
    }
    public void  onEliminarProceso() throws Exception {
        if (paneSeleccionado != null){
            INSTANCE.getModel().eliminarProceso(procesoSelecionado);
            mostrarMensaje("Informacion proceso","","El proceso ha sido eliminado.", Alert.AlertType.INFORMATION);
            paneSeleccionado.getChildren().clear();
            paneSeleccionado.setStyle("-fx-background-color: write");
            paneSeleccionado= null;

        }
        else {
            mostrarMensaje("Informacion proceso","ERROR","Seleccione el proceso que desea eliminar", Alert.AlertType.ERROR);
        }

    }
    public void onClickAddActivity() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(CrudActividadController.class.getResource("/view/crearActividad-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        Stage stage = new Stage();
        stage.setTitle("Crear procesos");
        stage.setScene(scene);
        stage.show();
        HBox hbox = new HBox();
        Label actividad = new Label("Actividad");
        Button btnEdit, btnRemove;
        btnEdit = new Button();
        btnRemove = new Button();
        RadioButton levelTask = new RadioButton();


        Image img = new Image("/resources/eliminar.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setFitWidth(20);
        view.setPreserveRatio(true);

        Image img1 = new Image("/resources/editar.png");
        ImageView view1 = new ImageView(img1);
        view1.setFitHeight(20);
        view1.setFitWidth(20);
        view1.setPreserveRatio(true);

        actividad.setStyle("-fx-font-family: 'SimSun'; -fx-font-size: 20;");

        btnRemove.setGraphic(view);
        btnEdit.setGraphic(view1);
        levelTask.setPrefHeight(20);
        levelTask.setPrefWidth(20);

        hbox.setLayoutY(activiesCurrentY);
        activiesCurrentY += hbox.getPrefHeight() + 30;

        hbox.setMargin(btnEdit, new Insets(0, 0, 0, 50)); // Márgen derecho para el botón 1
        hbox.setMargin(btnRemove, new Insets(0, 0, 0, 10));

        hbox.setLayoutY(activiesCurrentY);
        activiesCurrentY += hbox.getPrefHeight() + 20;

        hbox.getChildren().addAll(levelTask, actividad, btnEdit, btnRemove);
        contentActivies.getChildren().add(hbox);

        levelTask.setOnMouseClicked( (event) -> {
            actividad.setStyle("-fx-font-family: 'SimSun'; -fx-font-size: 20; -fx-text-fill: #1397D4");
            levelTask.setDisable(true);
        });


    }

    public void onClickAddTask() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(CrudTareaController.class.getResource("/view/crearTarea-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        Stage stage = new Stage();
        stage.setTitle("Crear procesos");
        stage.setScene(scene);
        stage.show();
        HBox hbox = new HBox();
        Label tarea = new Label("Tarea");
        Button btnEdit, btnRemove;
        btnEdit = new Button();
        btnRemove = new Button();
        RadioButton levelTask = new RadioButton();


        Image img = new Image("/resources/eliminar.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setFitWidth(20);
        view.setPreserveRatio(true);

        Image img1 = new Image("/resources/editar.png");
        ImageView view1 = new ImageView(img1);
        view1.setFitHeight(20);
        view1.setFitWidth(20);
        view1.setPreserveRatio(true);

        tarea.setStyle("-fx-font-family: 'SimSun'; -fx-font-size: 20;");

        //levelTask.setStyle("-fx-background-color: gray");
        levelTask.setPrefHeight(20);
        levelTask.setPrefWidth(20);

        btnRemove.setGraphic(view);
        btnEdit.setGraphic(view1);

        hbox.setLayoutY(taskCurrentY);
        taskCurrentY += hbox.getPrefHeight() + 20;

        hbox.setMargin(btnEdit, new Insets(0, 0, 0, 50)); // Márgen derecho para el botón 1
        hbox.setMargin(btnRemove, new Insets(0, 0, 0, 10));
        hbox.setLayoutY(taskCurrentY);
        taskCurrentY += hbox.getPrefHeight() + 20;

        hbox.getChildren().addAll(levelTask, tarea, btnEdit, btnRemove);
        contentTaks.getChildren().add(hbox);

        levelTask.setOnMouseClicked( (event) -> {
            tarea.setStyle("-fx-font-family: 'SimSun'; -fx-font-size: 20; -fx-text-fill: #1397D4");
            levelTask.setDisable(true);
        });
    }


    public void onLogoutAction(javafx.event.ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.initOwner(btnLogout.getScene().getWindow());
        btnLogout.getScene().getWindow().hide();
        stage.show();
    }
    public void onCrudUsuarios(javafx.event.ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/crudUsuarios-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1400, 700);
        Stage stage = new Stage();
        stage.setTitle("Usuarios");
        stage.setScene(scene);
        stage.initOwner(btnCrudUsuarios.getScene().getWindow());
        btnCrudUsuarios.getScene().getWindow().hide();
        stage.show();
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
