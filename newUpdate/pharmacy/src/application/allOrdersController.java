package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class allOrdersController {

	public static boolean isOpen = false;
	public static int ordId;

	private ArrayList<billData> data;
	private ObservableList<billData> dataList;
	@FXML
	private TableView<billData> TableData;

	@FXML
	private Button btnback;

	@FXML
	private TableColumn<billData, Integer> orderId;
	@FXML
	private TableColumn<billData, Integer> byEmp;
	@FXML
	private TableColumn<billData, Double> price;
	@FXML
	private TableColumn<billData, String> date;
	@FXML
	private TableColumn<billData, String> bill_type;

	@FXML
	private Button deleteItem;

	@FXML
	private Button openOrder;

	@FXML
	private TextField ordeID;

	@FXML
	private Button search;

	@FXML
	private Button showStattics;

	@FXML
	private TableView<billData> tableData;



	@FXML
	public void initialize() {

		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		TableData.setEditable(true);

		orderId.setCellValueFactory(new PropertyValueFactory<billData, Integer>("order_id"));
		date.setCellValueFactory(new PropertyValueFactory<billData, String>("order_date"));
		price.setCellValueFactory(new PropertyValueFactory<billData, Double>("full_price"));
		byEmp.setCellValueFactory(new PropertyValueFactory<billData, Integer>("emp_id"));
		bill_type.setCellValueFactory(new PropertyValueFactory<billData, String>("bill_type"));
		getData();
		TableData.setItems(dataList);

	}

	public void getData() {
		String SQL = "select * from bill;";
		try {
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {

				billData it = new billData(rs.getInt(1), rs.getDate(2).toString(), rs.getDouble(3), rs.getDouble(4),
						rs.getInt(6), rs.getString(5));

				dataList.add(it);

			}
			rs.close();
			state.close();
			Connector.a.connectDB().close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void back(ActionEvent event) {
		try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) btnback.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Start.fxml"));
			Scene scene = new Scene(root, 901, 649);
			stage.setScene(scene);
			stage.setTitle("Pharmacy");
			stage.show();

		} catch (IOException e1) {

		}
	}

	@FXML
	void deleteItem(ActionEvent event) {

	}

	@FXML
	void openOrder(ActionEvent event) {

		isOpen = true;
		ordId = Integer.parseInt(ordeID.getText());

		try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) openOrder.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("orderes.fxml"));
			Scene scene = new Scene(root, 951, 781);
			stage.setScene(scene);
			stage.setTitle("Orderes");
			stage.show();

		} catch (IOException e1) {
		}
	}

	@FXML
	void showStattics(ActionEvent event) {

		Scene scene;
		Stage stage = null;
		try { // open new stage
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("staticsOrder.fxml"));
			scene = new Scene(fxmlLoader.load(), 422, 428);
			stage = new Stage();
			stage.setTitle("New Window");
			stage.setScene(scene);
			stage.show();

		} catch (IOException e1) {
		}
	}

	@FXML
	void search(ActionEvent event) {

	}

}
