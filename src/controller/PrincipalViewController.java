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
    @FXML
    private TextField tfBuscar;
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
                Button btnEdit, btnRemove, btnInfo;
                btnEdit = new Button();
                btnRemove = new Button();
                btnInfo = new Button();

                if(tareaI.isCompletada()){
                    String estilo = tarea.getStyle();
                    estilo += "-fx-text-fill: green;";
                    tarea.setStyle(estilo);
                }

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

                tarea.setStyle("-fx-font-family: 'SimSun'; -fx-font-size: 20;");

                btnRemove.setGraphic(view);
                btnEdit.setGraphic(view1);
                btnInfo.setGraphic(view2);


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

                btnInfo.setOnMouseClicked( (event) -> {
                    if(tareaSeleccionada != null){
                        mostrarMensaje("INFO ACTIVIDAD", tareaSeleccionada.getNombre(), tareaSeleccionada.getDescripcion()+"\n"+"Es obligatoria: "+tareaSeleccionada.isObligatoriedad()+"\n"+"Tiempo max: "+tareaSeleccionada.getTiempo()+" mins", Alert.AlertType.INFORMATION );
                    }else{
                        mostrarMensaje("DETALLE ACTIVIDAD", "Acción fallida", "seleccione un proceso para ver la información", Alert.AlertType.WARNING);
                    }
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

                hbox.setMargin(btnInfo, new Insets(0, 0, 0, 30)); // Márgen derecho para el botón 1
                hbox.setMargin(btnEdit, new Insets(0, 0, 0, 10));
                hbox.setMargin(btnRemove, new Insets(0, 0, 0, 10));

                hbox.setLayoutY(taskCurrentY);
                taskCurrentY += hbox.getPrefHeight() + 20;

                hbox.getChildren().addAll(tarea, btnInfo,btnEdit, btnRemove);
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

    public void onClickBuscar() {

        String filtro = tfBuscar.getText();

        if( filtro.equals("") || filtro == null){
            mostrarMensaje("BUSQUEDAD", "Error en la busquedad", "Ingrese una palabra para filtrar", Alert.AlertType.ERROR);
            refrescarVentana();
        }else{
            Object objectoEncontrado = INSTANCE.getModel().buscar(filtro);
            contentProccess.getChildren().clear();
            contentActivies.getChildren().clear();
            contentTaks.getChildren().clear();
            proccessCurrentY = 0;
            activiesCurrentY = 0;
            taskCurrentY = 0;
            if(objectoEncontrado == null){
                mostrarMensaje("BUSQUEDAD", "Error en la busquedad","No se ha encontrado ninguna coincidencia", Alert.AlertType.ERROR);
            }else{
                if(objectoEncontrado instanceof Proceso){
                    mostrarProceso((Proceso) objectoEncontrado);
                }else if(objectoEncontrado instanceof Actividad) {
                    mostrarActividad((Actividad) objectoEncontrado);
                }else{
                    mostrarTarea((Tarea) objectoEncontrado);
                }
            }
        }
    }

    private void mostrarProceso(Proceso objectoEncontrado) {
    }

    private void mostrarTarea(Tarea objectoEncontrado) {
        HBox hbox = new HBox();
        Label tarea = new Label(objectoEncontrado.getNombre());
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

        hbox.setLayoutY(taskCurrentY);
        taskCurrentY += hbox.getPrefHeight() + 30;

        hbox.setMargin(btnEdit, new Insets(0, 0, 0, 50)); // Márgen derecho para el botón 1
        hbox.setMargin(btnRemove, new Insets(0, 0, 0, 10));

        hbox.setLayoutY(taskCurrentY);
        taskCurrentY += hbox.getPrefHeight() + 20;

        hbox.getChildren().addAll(tarea, btnEdit, btnRemove);
        contentTaks.getChildren().add(hbox);
    }

    private void mostrarActividad(Actividad objectoEncontrado) {

        HBox hbox = new HBox();
        Label actividadView = new Label(objectoEncontrado.getNombre());
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
    }
    public void onClickRunProccess() {
        if(procesoSelecionado != null){

            mostrarMensaje("INICIAR PROCESO", "Proceso iniciado", "Se ha iniciado el proceso", Alert.AlertType.INFORMATION);
            try {
                INSTANCE.getModel().iniciarProceso(procesoSelecionado);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else{
            mostrarMensaje("INICIAR PROCESO", "Proceso no inciado", "Seleccione un proceso para inciar", Alert.AlertType.WARNING);
        }

        refrescarVentana();
    }

    public void onClickExportar(ActionEvent actionEvent) {

        INSTANCE.getModel().exportarProcesosACSV("archivo.csv");
        mostrarMensaje("EXPOTACIÓN", "Acción existosa", "Se ha esportado correctamente", Alert.AlertType.INFORMATION);
    }

    public void onClickImportar(ActionEvent actionEvent) {
    }
}
