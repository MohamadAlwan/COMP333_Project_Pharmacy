package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private Button btnItem;

    @FXML
    private Button btnOrder;

    @FXML
    private Button btnProv;

    @FXML
    private Button btnCat;

    @FXML
    private Button btnEmp;

    @FXML
    private Button btnInsh;

    @FXML
    void Cat(ActionEvent event) {
    	try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) btnCat.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("categores.fxml"));
			Scene scene = new Scene(root, 930, 670);
			stage.setScene(scene);
			stage.setTitle("categores");
			stage.show();
			
		} catch (IOException e1) {
		}
    }

    @FXML
    void Emp(ActionEvent event) {
    	try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) btnEmp.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root, 930, 670);
			stage.setScene(scene);
			stage.setTitle("Employee");
			stage.show();
			
		} catch (IOException e1) {
		}
    }

    @FXML
    void Insh(ActionEvent event) {
    	try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) btnInsh.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("inshurance_company.fxml"));
			Scene scene = new Scene(root, 930, 670);
			stage.setScene(scene);
			stage.setTitle("inshurance company");
			stage.show();
			
		} catch (IOException e1) {
		}
    }

    @FXML
    void Item(ActionEvent event) {
    	try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) btnItem.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("item.fxml"));
			Scene scene = new Scene(root, 930, 670);
			stage.setScene(scene);
			stage.setTitle("item");
			stage.show();
			
		} catch (IOException e1) {
		}
    }

    @FXML
    void Order(ActionEvent event) {
    	try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) btnOrder.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("orderes.fxml"));
			Scene scene = new Scene(root, 930, 670);
			stage.setScene(scene);
			stage.setTitle("orderes");
			stage.show();
			
		} catch (IOException e1) {
		}
    }

    @FXML
    void Prov(ActionEvent event) {
    	try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) btnProv.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("provide_company.fxml"));
			Scene scene = new Scene(root, 930, 670);
			stage.setScene(scene);
			stage.setTitle("provide company");
			stage.show();
			
		} catch (IOException e1) {
		}
    }

}
