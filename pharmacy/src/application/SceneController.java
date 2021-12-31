package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SceneController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField UserName;

	@FXML
	private TextField PassWord;
	@FXML
	private Button btnLogin;

	@FXML
	void btnClick(ActionEvent event) {

		try {
			PreparedStatement st = Connector.a.connectDB()
					.prepareStatement("select * from employee where employee_name = ? AND emp_password = ? ");
			st.setString(1, UserName.getText());
			st.setString(2, PassWord.getText());
			ResultSet r1 = st.executeQuery();
			if (UserName.getText().equals("") || PassWord.getText().equals("")) {

				showDialog("error", "you entered a wrong data", null, AlertType.ERROR);

			} else {
				if (r1.next()) {
					if (r1.getString(2).equals(UserName.getText()) && (r1.getString(5).equals(PassWord.getText()))) {
						try { // open new stage
							Stage stage;
							Parent root;
							stage = (Stage) btnLogin.getScene().getWindow();
							stage.close();
							root = FXMLLoader.load(getClass().getResource("Start.fxml"));
							Scene scene = new Scene(root, 741, 484);
							stage.setScene(scene);
							stage.setTitle("Chose One");
							stage.show();
							
						} catch (IOException e1) {
							showDialog("error", "you entered a wrong data", null, AlertType.ERROR);
						}
					} else {
						Alert al = new Alert(AlertType.ERROR);
						al.setContentText("wrong input");
						al.showAndWait();
						
					}

				}
			}

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@FXML
	void initialize() {
		assert UserName != null : "fx:id=\"UserName\" was not injected: check your FXML file 'Scene.fxml'.";
		assert PassWord != null : "fx:id=\"PassWord\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void showDialog(String title, String header, String body, AlertType type) {
		Alert alert = new Alert(type); // infotrmation or error or..
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(body);

		alert.show();

	}
}
