package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class addItem2OrderController {

	@FXML
	private TextField Item_name;

	@FXML
	private TextField item_parcode;
	@FXML
	private Spinner<?> item_quant;

	@FXML
	private Button addToCart;

	@FXML
	private ToggleButton byItemName;

	@FXML
	private ToggleButton byParcode;

	@FXML
	private Button cancelItem;

	@FXML
	void addToCart(ActionEvent event) {

	}

	@FXML
	void byItemName(ActionEvent event) {

	}

	@FXML
	void byParcode(ActionEvent event) {

	}

	@FXML
	void cancelItem(ActionEvent event) {

	}

}
