package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class orderesController {
	@FXML
	private TableColumn<?, ?> counter;

	@FXML
	private TableColumn<?, ?> itemCategory;

	@FXML
	private TableColumn<?, ?> itemName;

	@FXML
	private TableColumn<?, ?> itemParcode;

	@FXML
	private TableColumn<?, ?> itemPrice;

	@FXML
	private TableColumn<?, ?> itemQuantity;

	@FXML
	private TableColumn<?, ?> itembyEmployee;

	@FXML
	private TableColumn<?, ?> Action;

	@FXML
	private Button addItem;

	@FXML
	private Button addinsurance;

	@FXML
	private Button btnback;

	@FXML
	private Button cancelOrdre;

	@FXML
	private ToggleButton cashOrder;

	@FXML
	private Button deleteItem;

	@FXML
	private Label discount;

	@FXML
	private ToggleButton insuranceOrder;

	@FXML
	private Label orderID;

	@FXML
	private Label price;

	@FXML
	private Label priceBefore;

	@FXML
	private Button print;

	@FXML
	private TextField search;

	@FXML
	void CancelOrder(ActionEvent event) {

	}

	@FXML
	void addNew(ActionEvent event) {

	}

	@FXML
	void addinsurance(ActionEvent event) {

	}

	@FXML
	void back(ActionEvent event) {

	}

	@FXML
	void cashOrder(ActionEvent event) {

	}

	@FXML
	void deleteItem(ActionEvent event) {

	}

	@FXML
	void insuranceOrder(ActionEvent event) {

	}

	@FXML
	void print(ActionEvent event) {

	}

	@FXML
	public void initialize() {
	}

}
