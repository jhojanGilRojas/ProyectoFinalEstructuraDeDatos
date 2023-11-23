package controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import resources.Cola;

import static controller.AppController.INSTANCE;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class PrincipalViewController {

    @FXML
    private AnchorPane contentProccess;
    @FXML
    private AnchorPane contentActivies;
    @FXML
    private AnchorPane contentTaks;
    @FXML
    private ScrollPane scrollProceso;
    @FXML
    private ScrollPane scrollactividad;
    @FXML
    private ScrollPane scrolltarea;
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
    private Button btnBuscar;
    @FXML
    private Button btnRunProccess;
    @FXML
    private Button btnExportar;
    @FXML
    private Button btnImportar;

    @FXML
    private Button btnCrudUsuarios;
    @FXML
    private Pane contentMain;
    @FXML
    Pane paneSeleccionado;
    static Proceso procesoSelecionado;

    static Actividad actividadSeleccionada;
    static Tarea tareaSeleccionada;

    private double proccessCurrentY = 0;
    private double activiesCurrentY = 0;
    private double taskCurrentY = 0;
    private int n = 0;
//   private  int i = 0;
    int changeColor = 0;

    int changeColorActivity = 0;
    int changeColorTask = 0;

    @FXML
    void initialize() {
        ArrayList<Proceso> procesosData = INSTANCE.getModel().getListaProcesosArray();
        for (Proceso proceso : procesosData) {
            if (proceso != null) {
                Pane newPane = new Pane();
                newPane.setPrefSize(230, 150); // Tamaño preferido del nuevo Pane
                Label name;
                Label id;
                Label timeMin;
                Label timeMax;
                newPane.setStyle("-fx-background-color: #dbdad7;");
                n = n + 1;
                Label text = new Label("Proceso " + n);
                name = new Label("Nombre: " + proceso.getNombre());
                id = new Label("Id: " + proceso.getId());
                timeMin = new Label(("Tiempo mínimo: " + proceso.getTiempoMinimo()));
                timeMax = new Label(("Tiempo máximo: " + proceso.getTiempoMaximo()));

                //estilos
                text.setStyle("-fx-font-family: 'SimSun'; -fx-font-size: 24");
                name.setStyle("-fx-font-family: 'SimSun'; -fx-font-size: 24");
                id.setStyle("-fx-font-family: 'SimSun'; -fx-font-size: 24");
                timeMax.setStyle("-fx-font-family: 'SimSun'; -fx-font-size: 24");
                timeMin.setStyle("-fx-font-family: 'SimSun'; -fx-font-size: 24");


                // Posicionar el nuevo Pane debajo del último
                newPane.setLayoutY(proccessCurrentY);
                proccessCurrentY += newPane.getPrefHeight() + 10; // 10 es el espacio entre los Pane
                newPane.setId("pane" + n);
                newPane.getChildren().addAll(name, id, timeMin, timeMax);
                name.setLayoutY(15);
                id.setLayoutY(50);
                timeMin.setLayoutY(80);
                timeMax.setLayoutY(110);

                ;
                // Agregar el nuevo Pane al Pane con margen
                contentProccess.setLeftAnchor(newPane, 30.0);
                contentProccess.getChildren().add(newPane);

                newPane.setOnMouseClicked(event -> {
                    if (changeColor == 0) {
                        changeColor = 1;
                        newPane.setStyle("-fx-background-color:  #1397D4");
                        name.setTextFill(javafx.scene.paint.Color.WHITE);
                        id.setTextFill(javafx.scene.paint.Color.WHITE);
                        timeMax.setTextFill(javafx.scene.paint.Color.WHITE);
                        timeMin.setTextFill(javafx.scene.paint.Color.WHITE);

                        paneSeleccionado = newPane;
                        procesoSelecionado = proceso;

                        int cantidadActividades = procesoSelecionado.getListaActividades().getTamano();
                        if(cantidadActividades != 0){
                            for (int i = 0; i < cantidadActividades; i++) {
                                Actividad actividad = procesoSelecionado.getListaActividades().obtenerNodo(i).getValorNodo();
                                HBox hbox = new HBox();
                                Label actividadView = new Label(actividad.getNombre());
                                Button btnEdit, btnRemove, btnInfo;
                                btnEdit = new Button();
                                btnRemove = new Button();
                                btnInfo = new Button();

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

                                Image img2 = new Image("/resources/info.png");
                                ImageView view2 = new ImageView(img2);
                                view2.setFitHeight(20);
                                view2.setFitWidth(20);
                                view2.setPreserveRatio(true);

                                btnRemove.setGraphic(view);
                                btnEdit.setGraphic(view1);
                                btnInfo.setGraphic(view2);

                                actividadView.setStyle("-fx-font-family: 'SimSun'; -fx-font-size: 20;");

                                hbox.setLayoutY(activiesCurrentY);
                                activiesCurrentY += hbox.getPrefHeight() + 30;

                                hbox.setMargin(btnInfo, new Insets(0, 0, 0, 30)); // Márgen derecho para el botón 1
                                hbox.setMargin(btnEdit, new Insets(0, 0, 0, 10));
                                hbox.setMargin(btnRemove, new Insets(0, 0, 0, 10));

                                hbox.setLayoutY(activiesCurrentY);
                                activiesCurrentY += hbox.getPrefHeight() + 20;

                                hbox.getChildren().addAll(actividadView, btnInfo,btnEdit, btnRemove);
                                contentActivies.getChildren().add(hbox);

                                btnEdit.setOnMouseClicked( (event23) -> {
                                    if(actividadSeleccionada != null){
                                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/infoActividad.fxml"));
                                        Scene scene;
                                        try {
                                            scene = new Scene(fxmlLoader.load(), 520, 470);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                        Stage stage = new Stage();
                                        stage.setTitle("INFO ACTIVIDAD");
                                        stage.initModality(Modality.APPLICATION_MODAL);
                                        stage.setScene(scene);
                                        stage.showAndWait();

                                    }else{
                                        mostrarMensaje("DETALLE ACTIVIDAD", "Acción fallida", "seleccione un proceso para editar", Alert.AlertType.WARNING);
                                    }
                                });

                                btnRemove.setOnMouseClicked( (event23) ->{
                                    if(actividadSeleccionada != null){
                                        Stage dialogo = new Stage();
                                        dialogo.initModality(Modality.APPLICATION_MODAL);
                                        dialogo.setTitle("Eliminacion de actividad");

                                        Label mensajeLabel = new Label("¿Seguro quiere eliminar la actividad?");


                                        Button aceptarBtn = new Button("Aceptar");
                                        Button cancelarBtn = new Button("Cancelar");

                                        aceptarBtn.setOnAction(e -> {
                                            procesoSelecionado.eliminarActividad(actividadSeleccionada);
                                            mostrarMensaje("ELIMINACION ACTIVIDAD", "Accion existosa", "Se ha eliminado la actividad", Alert.AlertType.INFORMATION);
                                            dialogo.close();

                                            refrescarVentana();


                                        });

                                        cancelarBtn.setOnAction(e -> {
                                            dialogo.close();
                                        });

                                        HBox hboxButtons = new HBox(10);
                                        hboxButtons.getChildren().addAll(aceptarBtn, cancelarBtn);
                                        hboxButtons.setAlignment(Pos.CENTER);

                                        VBox layout = new VBox(10);
                                        layout.getChildren().addAll(mensajeLabel, hboxButtons);
                                        layout.setAlignment(Pos.CENTER);

                                        Scene scene = new Scene(layout, 250, 150);
                                        dialogo.setScene(scene);
                                        dialogo.showAndWait();
                                    }else{
                                        mostrarMensaje("DETALLE ACTIVIDAD", "Acción fallida", "seleccione un proceso para eliminar", Alert.AlertType.WARNING);
                                    }

                                });

                                btnInfo.setOnMouseClicked( (event23)-> {
                                    if(actividadSeleccionada != null){
                                        mostrarMensaje("INFO ACTIVIDAD", actividadSeleccionada.getNombre(), actividadSeleccionada.getDescripcion()+"\n"+"Es obligatoria: "+actividadSeleccionada.isObligatoriedad()+"\n"+"Tiempo max: "+actividadSeleccionada.getTiempoMax()+" mins", Alert.AlertType.INFORMATION );
                                    }else{
                                        mostrarMensaje("DETALLE ACTIVIDAD", "Acción fallida", "seleccione un proceso para ver la información", Alert.AlertType.WARNING);
                                    }
                                });

                                actividadView.setOnMouseClicked((event13) -> {
                                    if(changeColorActivity == 0){
                                        changeColorActivity = 1;

                                        String estiloActual = actividadView.getStyle();
                                        estiloActual += "-fx-text-fill: #1397d4;";
                                        actividadView.setStyle(estiloActual);
                                        actividadSeleccionada = actividad;
                                        llenarTareas(actividadSeleccionada);
                                    }else{
                                        changeColorActivity = 0;
                                        String estiloActual = actividadView.getStyle();
                                        estiloActual += "-fx-text-fill: black;";
                                        actividadView.setStyle(estiloActual);

                                        actividadSeleccionada = null;
                                        contentTaks.getChildren().clear();
                                        taskCurrentY = 0;
                                    }
                                });
                            }
                        }else{
                            mostrarMensaje("MOSTRAR ACTIVIDADES", "NO HAY DATOS", "el proceso seleccionado no tiene actividades", Alert.AlertType.INFORMATION);
                        }
                    }else{
                        contentActivies.getChildren().clear();
                        contentTaks.getChildren().clear();
                        procesoSelecionado = null;
                        paneSeleccionado = null;
                        newPane.setStyle("-fx-background-color: #dbdad7");
                        name.setTextFill(javafx.scene.paint.Color.BLACK);
                        id.setTextFill(javafx.scene.paint.Color.BLACK);
                        timeMax.setTextFill(javafx.scene.paint.Color.BLACK);
                        timeMin.setTextFill(javafx.scene.paint.Color.BLACK);
                        changeColor = 0;
                        activiesCurrentY = 0;
                    }
//                    System.out.println(newPane.getId());
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

        Image img2 = new Image("/resources/buscar.png");
        ImageView view2 = new ImageView(img2);
        view2.setFitHeight(20);
        view2.setFitWidth(20);
        view2.setPreserveRatio(true);

        Image img3 = new Image("/resources/on.png");
        ImageView view3 = new ImageView(img3);
        view3.setFitHeight(20);
        view3.setFitWidth(20);
        view3.setPreserveRatio(true);

        Image img4 = new Image("/resources/exportar.png");
        ImageView view4 = new ImageView(img4);
        view4.setFitHeight(20);
        view4.setFitWidth(20);
        view4.setPreserveRatio(true);

        Image img5 = new Image("/resources/importar.png");
        ImageView view5 = new ImageView(img5);
        view5.setFitHeight(20);
        view5.setFitWidth(20);
        view5.setPreserveRatio(true);

        btnAddActivity.setGraphic(view);
        btnAddTask.setGraphic(view1);
        btnBuscar.setGraphic(view2);
        btnRunProccess.setGraphic(view3);
        btnExportar.setGraphic(view4);
        btnImportar.setGraphic(view5);
    }

    private void refrescarVentana() {
        contentProccess.getChildren().clear();
        contentActivies.getChildren().clear();
        contentTaks.getChildren().clear();
        proccessCurrentY = 0;
        activiesCurrentY = 0;
        taskCurrentY = 0;
        initialize();
    }

    private void llenarTareas(Actividad actividad) {
        ArrayList<Tarea> listaTareas = actividad.convertirCola(actividad.getTareas());

        int cantidadTareas = listaTareas.size();

        if (cantidadTareas != 0){
            for (int j = 0; j < cantidadTareas; j++) {
                Tarea tareaI = listaTareas.get(j);

                HBox hbox = new HBox();
                Label tarea = new Label(listaTareas.get(j).getNombre());
                Button btnEdit, btnRemove;
                btnEdit = new Button();
                btnRemove = new Button();

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

                btnRemove.setGraphic(view);
                btnEdit.setGraphic(view1);


                btnEdit.setOnMouseClicked( (event) -> {
                    if(tareaSeleccionada != null){
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/InfoTarea.fxml"));
                        Scene scene;
                        try {
                            scene = new Scene(fxmlLoader.load(), 520, 470);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Stage stage = new Stage();
                        stage.setTitle("INFO TAREA");
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(scene);
                        stage.showAndWait();

                    }else{
                        mostrarMensaje("DETALLE TAREA", "Acción fallida", "seleccione una tarea para editar", Alert.AlertType.WARNING);
                    }
                });

                btnRemove.setOnMouseClicked( (event) ->{
                    if(tareaSeleccionada != null){
                        Stage dialogo = new Stage();
                        dialogo.initModality(Modality.APPLICATION_MODAL);
                        dialogo.setTitle("Eliminacion de tarea");

                        Label mensajeLabel = new Label("¿Seguro quiere eliminar la tarea?");


                        Button aceptarBtn = new Button("Aceptar");
                        Button cancelarBtn = new Button("Cancelar");

                        aceptarBtn.setOnAction(e -> {
                            //procesoSelecionado.eliminarActividad(actividadSeleccionada);
                            mostrarMensaje("ELIMINACION TAREA", "Accion existosa", "Se ha eliminado la tarea", Alert.AlertType.INFORMATION);
                            dialogo.close();

                            refrescarVentana();
                        });

                        cancelarBtn.setOnAction(e -> {
                            dialogo.close();
                        });

                        HBox hboxButtons = new HBox(10);
                        hboxButtons.getChildren().addAll(aceptarBtn, cancelarBtn);
                        hboxButtons.setAlignment(Pos.CENTER);

                        VBox layout = new VBox(10);
                        layout.getChildren().addAll(mensajeLabel, hboxButtons);
                        layout.setAlignment(Pos.CENTER);

                        Scene scene = new Scene(layout, 250, 150);
                        dialogo.setScene(scene);
                        dialogo.showAndWait();



                    }else{
                        mostrarMensaje("DETALLE ACTIVIDAD", "Acción fallida", "seleccione un tarea para eliminar", Alert.AlertType.WARNING);
                    }

                    actividadSeleccionada.calcularTiempoMax();

                });

                tarea.setOnMouseClicked( (event) -> {
                    if(changeColorTask == 0){
                        changeColorTask = 1;

                        String estiloActual = tarea.getStyle();
                        estiloActual += "-fx-text-fill: #1397d4;";
                        tarea.setStyle(estiloActual);
                        tareaSeleccionada = tareaI;
                    }else{
                        changeColorTask = 0;
                        String estiloActual = tarea.getStyle();
                        estiloActual += "-fx-text-fill: black;";
                        tarea.setStyle(estiloActual);
                        tareaSeleccionada = null;
                    }
                });

                hbox.setLayoutY(taskCurrentY);
                taskCurrentY += hbox.getPrefHeight() + 30;

                hbox.setMargin(btnEdit, new Insets(0, 0, 0, 50)); // Márgen derecho para el botón 1
                hbox.setMargin(btnRemove, new Insets(0, 0, 0, 10));

                hbox.setLayoutY(taskCurrentY);
                taskCurrentY += hbox.getPrefHeight() + 20;

                hbox.getChildren().addAll(tarea, btnEdit, btnRemove);
                contentTaks.getChildren().add(hbox);
            }
        }else{
            mostrarMensaje("MOSTRAR TAREAS", "NO HAY DATOS", "la actividad seleccionada no tiene tareas", Alert.AlertType.INFORMATION);
        }
    }

    /////////////////////////////////////CRUD PROCESOS//////////////////////////////////////////////////////////////
    public void onClickCrearProceso( ) throws IOException {

        if (INSTANCE.getModel().getUsuarioLogueado().getRol().equals(Rol.ADMINISTRADOR)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/crearProceso-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 470);
            Stage stage = new Stage();
            stage.setTitle("INFO ACTIVIDAD");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            refrescarVentana();
        } else {
            mostrarMensaje("Informacion rol","ERROR","Solo los administradores pueden crear procesos", Alert.AlertType.ERROR);
        }

    }

    public void onClickActualizarProceso() throws IOException {
        if (INSTANCE.getModel().getUsuarioLogueado().getRol().equals(Rol.ADMINISTRADOR)) {
            if (paneSeleccionado != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/infoProceso.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 520, 470);
                Stage stage = new Stage();
                stage.setTitle("INFO ACTIVIDAD");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                refrescarVentana();
            } else {
                mostrarMensaje("Informacion proceso", "ERROR", "Seleccione el proceso que desea editar", Alert.AlertType.ERROR);
            }
        }else {
            mostrarMensaje("Informacion Rol","ERROR","Solo los administradores pueden crear procesos", Alert.AlertType.ERROR);
        }
    }
    public  void onEditarProceso() throws IOException {
        if (INSTANCE.getModel().getUsuarioLogueado().getRol().equals(Rol.ADMINISTRADOR)) {
            if (paneSeleccionado != null) {

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
                        btnCrear, titleLabel2, labelName, labelId, labelTimeMin, labelTimeMax,
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
                    if (validarDatos(name, id, timeMax, timeMin)) {
                        Proceso procesoEditado = new Proceso(id, name);
                        procesoSelecionado = INSTANCE.getModel().actualizarProceso(procesoEditado, procesoSelecionado.getId());
                        mostrarMensaje("Informacion proceso", "", "El proceso ha sido actualizado.", Alert.AlertType.INFORMATION);
                        stage.close();
                    }
                });
                btnCancelar.setOnAction(actionEvent1 -> {
                    stage.close();
                });

            } else {
                mostrarMensaje("Informacion proceso", "ERROR", "Seleccione el proceso que desea editar", Alert.AlertType.ERROR);
            }
        }else {
            mostrarMensaje("Informacion Rol","ERROR","Solo los administradores pueden crear procesos", Alert.AlertType.ERROR);

        }
    }
    public void  onEliminarProceso() throws Exception {
        if (INSTANCE.getModel().getUsuarioLogueado().getRol().equals(Rol.ADMINISTRADOR)) {
        if (paneSeleccionado != null) {
            if (mostrarMensajeConfirmacion("¿ Desea eliminar este proceso?")) {
                INSTANCE.getModel().eliminarProceso(procesoSelecionado);
                mostrarMensaje("Informacion proceso", "", "El proceso ha sido eliminado.", Alert.AlertType.INFORMATION);
                paneSeleccionado.getChildren().clear();
                paneSeleccionado.setStyle("-fx-background-color: write");
                paneSeleccionado = null;
                refrescarVentana();
            }

        } else {
            mostrarMensaje("Informacion proceso", "ERROR", "Seleccione el proceso que desea eliminar", Alert.AlertType.ERROR);
        }
    }else {
            mostrarMensaje("Informacion Rol","ERROR","Solo los administradores pueden crear procesos", Alert.AlertType.ERROR);

        }
    }

    //////////////////////////////////////////////CRUD ACTIVIDAD//////////////////////////////////////////////////
    public void onClickAddActivity() throws IOException {
        if (procesoSelecionado != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/crearActividad-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 730, 500);
            Stage stage = new Stage();
            stage.setTitle("CREAR ACTIVIDAD");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            refrescarVentana();

        } else {
            mostrarMensaje("Informacion proceso", "ERROR", "Seleccione el proceso que le desea añadir una actividad", Alert.AlertType.WARNING);
        }
    }


/////////////////////////////////////////////CRUD TAREA////////////////////////////////////////////////

    public void onClickAddTask() throws IOException {
        if (actividadSeleccionada != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/crearTarea-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 650, 500);
            Stage stage = new Stage();
            stage.setTitle("CREAR TAREA");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            refrescarVentana();
        } else {
            mostrarMensaje("Informacion proceso", "ERROR", "Seleccione la actividad que le desea añadir una tarea", Alert.AlertType.WARNING);
        }
    }



    public void onLogoutAction(javafx.event.ActionEvent actionEvent) throws IOException {
        INSTANCE.getModel().setUsuarioLogueado(null);
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.initOwner(btnLogout.getScene().getWindow());
        btnLogout.getScene().getWindow().hide();
        stage.show();
    }
    public void onCrudUsuarios(javafx.event.ActionEvent actionEvent) throws IOException {

        if (INSTANCE.getModel().getUsuarioLogueado().getRol().equals(Rol.ADMINISTRADOR)) {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/view/crudUsuarios-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1400, 700);
            Stage stage = new Stage();
            stage.setTitle("Usuarios");
            stage.setScene(scene);
            stage.initOwner(btnCrudUsuarios.getScene().getWindow());
            btnCrudUsuarios.getScene().getWindow().hide();
            stage.show();
        } else {
            mostrarMensaje("Informacion rol", "ERROR", "Solo los administradores modificar usuarios", Alert.AlertType.ERROR);

        }
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
    private boolean mostrarMensajeConfirmacion(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();

        if (((Optional<?>) action).get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public static Proceso getProcesoSelecionado() {
        return procesoSelecionado;
    }

    public static Actividad getActividadSeleccionada() {
        return actividadSeleccionada;
    }

    public static Tarea getTareaSeleccionada() {
        return tareaSeleccionada;
    }

    public void onClickBuscar(ActionEvent actionEvent) {
    }

    public void onClickRunProccess(ActionEvent actionEvent) {
    }

    public void onClickExportar(ActionEvent actionEvent) {
    }

    public void onClickImportar(ActionEvent actionEvent) {
    }
}
