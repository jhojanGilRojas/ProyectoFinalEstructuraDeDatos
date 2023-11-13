package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class PrincipalViewController {

    @FXML
    private AnchorPane contentProccess;
    @FXML
    private AnchorPane contentActivies;
    @FXML
    private AnchorPane contentTaks;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnAddActivity;
    @FXML
    private Button btnAddTask;
    @FXML
    private Button btnLogout;
    @FXML
    private Pane contentMain;
    private double proccessCurrentY = 0;
    private double activiesCurrentY = 0;
    private double taskCurrentY = 0;
    private int n = 0;
    int changeColor = 0;

    @FXML
    void initialize() {
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
        stage.show();
        Pane newPane = new Pane();
        newPane.setPrefSize(100, 100); // Tamaño preferido del nuevo Pane
        newPane.setStyle("-fx-background-color: gray;");
        n = n+1;
        Label text = new Label("Proceso "+n );

        // Posicionar el nuevo Pane debajo del último
        newPane.setLayoutY(proccessCurrentY);
        proccessCurrentY += newPane.getPrefHeight() + 10; // 10 es el espacio entre los Pane
        newPane.setId("pane"+n);
        newPane.getChildren().add(text);
        // Agregar el nuevo Pane al Pane con margen
        contentProccess.setLeftAnchor(newPane, 50.0);
        contentProccess.getChildren().add(newPane);


        newPane.setOnMouseClicked(event -> {

            if(changeColor == 0){
                newPane.setStyle("-fx-background-color: lightblue");
                changeColor = 1;
            }else{
                newPane.setStyle("-fx-background-color: gray");
                changeColor = 0;
            }
            System.out.println(newPane.getId());
        });
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
}
