package controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import model.DAO.LibroDAO;
import model.DAO.PersonajeDAO;
import model.DataObject.Libro;
import model.DataObject.Personaje;

public class EditPersonajesController {
	@FXML
    private MenuItem MIVolver;
	
	@FXML
	private ComboBox<Libro> CBLibros;

	@FXML
	private ComboBox<Personaje> CBPersonajes;

	@FXML
	private TextField TFTAlineamiento;

	@FXML
	private TextField TFTNombre;

	@FXML
	private Button buttBorrar;

	@FXML
	private Button buttCrear;

	@FXML 
	private Button buttAñadir;

	@FXML
	private TextField txtDescripcion;

	private PersonajeDAO pdao = new PersonajeDAO();
	private LibroDAO ldao = new LibroDAO(); 
	private ObservableList<Personaje> personajes = FXCollections.observableArrayList();
	private ObservableList<Libro> libros = FXCollections.observableArrayList();

	@FXML
	void initialize() {

		this.personajes.setAll(pdao.getAll()); 
		this.libros.setAll(ldao.getAll());
		buttBorrar.setDisable(true);
		CBPersonajes.setItems(personajes);
		CBLibros.setItems(libros);
		CBPersonajes.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
			buttBorrar.setDisable(false);
		});
		CBLibros.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {
			buttBorrar.setDisable(false);
		});

	}

	@FXML
	void añadePersonaje(ActionEvent event) {
		Boolean confirmacion=utils.Dialog.showConfirm("Confirmación", "¿Quieres añadir al libro "+CBPersonajes.getValue().getNombre()+" ?",
				"Vas a añadir: "+CBPersonajes.getValue().getNombre()); 
		if(CBPersonajes.getValue() != null && CBLibros.getValue() != null  && confirmacion) {			
			try {
				ldao.addCharactertoBook(CBPersonajes.getValue(),CBLibros.getValue());
			} catch (Exception e) {
				e.printStackTrace();
				buttBorrar.setDisable(true);
				utils.Dialog.showError("Añadir Personaje", "Ha surgido un error al añadirlo al capítulo", "");
			}
		}
	}

	@FXML
	void borrarPersonaje(ActionEvent event) {
		Boolean confirmacion=utils.Dialog.showConfirm("Confirmación", "¿Quieres borra el personaje?",
				"Vas a borrar: "+CBPersonajes.getValue().getNombre()); 
		if(CBPersonajes.getValue() != null && CBLibros.getValue() != null  && confirmacion) {			
			try {
				ldao.deleteCharacterFromBook(CBPersonajes.getValue(), CBLibros.getValue());
			} catch (Exception e) {
				e.printStackTrace();
				buttBorrar.setDisable(true);
				utils.Dialog.showError("Borrar Personaje", "Ha surgido un error al borrarlo", "");
			}
		}
	}

	@FXML
	void crearEditarPersonaje(ActionEvent event) {
		if (CBPersonajes.getValue() != null) {
			Personaje p = new Personaje();
			p.setNombre(TFTNombre.getText());
			p.setDescripcion(txtDescripcion.getText());
			p.setAlineamiento(TFTAlineamiento.getText());
			p.addLibro(MainLibrosController.currentBook);
			try {
				pdao.editar(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (CBPersonajes.getValue() == null) {
			Personaje p = new Personaje();
			p.setNombre(TFTNombre.getText());
			p.setDescripcion(txtDescripcion.getText());
			p.setAlineamiento(TFTAlineamiento.getText());
			p.addLibro(MainLibrosController.currentBook);
			try {
				pdao.crear(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	@FXML
    private void switchToLibro()  {
        try {
			App.setRoot("MainLibros");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
