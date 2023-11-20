package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Rol;
import model.Usuario;
import static controller.AppController.INSTANCE;



import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public class CrudUsuariosController {

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<Usuario,String > columnContraseña;

    @FXML
    private TableColumn<Usuario, Rol> columnRol;

    @FXML
    private TableColumn<Usuario, String> columnUsuario;

    @FXML
    private TableView<Usuario> tblUsuarios;

    @FXML
    private TextField tfContrasena;

    @FXML
    private ComboBox<Rol> tfRol;

    @FXML
    private TextField tfUsuario;
    @FXML
    private ObservableList <Usuario> listaUsuariosData = FXCollections.observableArrayList();
    private Usuario usuarioSelecionado;
    @FXML
    void initialize(){

        tblUsuarios.setItems(getListaUsuariosData());
        columnUsuario.setCellValueFactory(new PropertyValueFactory<>("userId"));
        columnContraseña.setCellValueFactory(new  PropertyValueFactory<>("password"));
        columnRol.setCellValueFactory(new PropertyValueFactory<>("Rol"));

        tfRol.getItems().setAll(Rol.values());

        tblUsuarios.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> llenarCampos(newValue));

        Image img = new Image("/resources/hacia-atras.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(30);
        view.setFitWidth(30);
        view.setPreserveRatio(true);
        btnVolver.setGraphic(view);
    }
    @FXML
    void onCrearClick(ActionEvent event) {
        String user = tfUsuario.getText();
        String password = tfContrasena.getText();
        Rol rol = tfRol.getValue();
        if (validarDatos(user,password,rol)){
            Usuario usuario = INSTANCE.getModel().crearCuentasDeUsuario(user,password,rol);
            if (usuario!=null) {
                tblUsuarios.getItems().clear();
                tblUsuarios.setItems(getListaUsuariosData());
                limpiarCampos();
                mostrarMensaje("Notificación de Administrador", "Información", "El usuario ha sido creado correctamente", Alert.AlertType.INFORMATION);
            }
            else {
                mostrarMensaje("Notificación de Administrador", "Error", "El usuario ya existe.", Alert.AlertType.WARNING);
                limpiarCampos();
            }
        }else {
            limpiarCampos();
        }
    }

    @FXML
    void onEditarClick(ActionEvent event) {
        String user = tfUsuario.getText();
        String password = tfContrasena.getText();
        Rol rol = tfRol.getValue();
        if (validarDatos(user,password,rol)){
            Usuario usuarioNuevo = new Usuario(user,password,rol);
            Usuario usuarioActualizado =INSTANCE.getModel().actualizarUsuario(usuarioNuevo,usuarioSelecionado.getUserId());

            if (usuarioActualizado!= null){
            limpiarCampos();
            tblUsuarios.refresh();
            mostrarMensaje("Notificación de Administrador","Información","El usuario ha sido actualizado correctamente", Alert.AlertType.INFORMATION);}
            else {
                llenarCampos(usuarioSelecionado);
                mostrarMensaje("Notificación de Administrador", "Error", "El usuario ya existe.", Alert.AlertType.WARNING);
            }
        }else {
            mostrarMensaje("Informacion administrador","ERROR","Seleccione el usuario que desea editar", Alert.AlertType.ERROR);
            limpiarCampos();
        }
    }

    @FXML
    void onEliminarClick(ActionEvent event) {
        if (mostrarMensajeConfirmacion("¿ Desea eliminar este usuario ?")) {
            if (usuarioSelecionado != null) {
                INSTANCE.getModel().eliminarUsuario(usuarioSelecionado.getUserId());
                mostrarMensaje("Notificación de Administrador", "Información", "El usuario ha sido eliminado correctamente", Alert.AlertType.INFORMATION);
                tblUsuarios.getItems().clear();
                tblUsuarios.setItems(listaUsuariosData);
                limpiarCampos();
            }else {
                mostrarMensaje("Informacion administrador","ERROR","Seleccione el usuario que desea eliminar", Alert.AlertType.ERROR);
            }
        }
        else {
            limpiarCampos();
        }
    }
    @FXML
    void onVolverClick(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalViewController.class.getResource("/view/principal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1400, 700);
        Stage stage = new Stage();
        stage.setTitle("USUARIO");
        stage.setScene(scene);
        stage.initOwner(btnVolver.getScene().getWindow());
        btnVolver.getScene().getWindow().hide();
        stage.show();
    }
    public ObservableList<Usuario> getListaUsuariosData() {
        listaUsuariosData.addAll((INSTANCE.getModel().getListaUsuariosArray()));
        return listaUsuariosData;
    }
    private boolean validarDatos(String user, String password, Rol rol) {
        String mensaje = "";

        if (user == null || user.equals(""))
            mensaje += "El usuario es invalido \n";

        if (password == null || password.equals(""))
            mensaje += "La contraseña es invalida \n";

        if (rol == null) {
            mensaje += "El rol es invalido \n";
        }

        if (mensaje.equals("")) {
            return true;
        } else {
            mostrarMensaje("Notificación Usuario","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private void limpiarCampos() {
        tfUsuario.setText("");
        tfContrasena.setText("");
        tfRol.setValue(null);
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
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
    private void llenarCampos(Usuario usuario) {
        usuarioSelecionado = usuario;
        if (usuario != null) {

            tfUsuario.setText(usuario.getUserId());
            tfContrasena.setText(usuario.getPassword());
            tfRol.setValue(usuario.getRol());

        }
    }
}
