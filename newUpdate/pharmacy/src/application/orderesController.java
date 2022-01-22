package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.IntegerStringConverter;

public class orderesController {

	public static int orderId;
	private double priceToShow=0;
	private ArrayList<invoiceData> data;
	private ObservableList<invoiceData> dataList;
	@FXML
	private TableView<invoiceData> TableData;
	boolean isCreatOrder = false;
	@FXML
	private TableColumn<invoiceData, Number> counter;

	@FXML
	private TableColumn<invoiceData, Integer> itemCategory;

	@FXML
	private TableColumn<invoiceData, String> itemName;

	@FXML
	private TableColumn<invoiceData, Integer> itemParcode;

	@FXML
	private TableColumn<invoiceData, Double> itemPrice;

	@FXML
	private TableColumn<invoiceData, Integer> itemQuantity;

	@FXML
	private TableColumn<ordersData, Integer> itembyEmployee;

	@FXML
	private Button addItem;

	@FXML
	private Button addinsurance;

	@FXML
	private Button btnback;

	@FXML
	private Button cancelOrdre;

	@FXML
	private ImageView imgDis;

	@FXML
	private ImageView imgInsh;

	@FXML
	private ImageView imgPr;

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

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  orderes where order_id =" + orderId + ";");
			Connector.a.ExecuteStatement("delete from  invoice where order_id =" + orderId + ";");

			Connector.a.connectDB().close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) cancelOrdre.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Start.fxml"));
			Scene scene = new Scene(root, 901, 649);
			stage.setScene(scene);
			stage.setTitle("Chose One");
			stage.show();

		} catch (IOException e1) {

		}
	}

	@FXML
	void addNew(ActionEvent event) {

		Scene scene;
		Stage stage = null;
		try { // open new stage
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("addItem2Order.fxml"));
			scene = new Scene(fxmlLoader.load(), 394, 598);
			stage = new Stage();
			stage.setTitle("New Window");
			stage.setScene(scene);
			stage.show();

		} catch (IOException e1) {
		}

		stage.setOnHidden(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent paramT) {
				
				data = new ArrayList<>();
				dataList = FXCollections.observableArrayList(data);
				TableData.setEditable(true);
				counter.setSortable(false);
				counter.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(TableData.getItems().indexOf(column.getValue())+1));
				
				itemParcode.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("par_code"));
				itemParcode
						.setCellFactory(TextFieldTableCell.<invoiceData, Integer>forTableColumn(new IntegerStringConverter()));
		
				itemName.setCellValueFactory(new PropertyValueFactory<invoiceData, String>("itemName"));
		
				itemQuantity.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("quantity"));
		
				itemCategory.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("itemCat"));
		
				itemPrice.setCellValueFactory(new PropertyValueFactory<invoiceData, Double>("full_sale_price"));
		
//				itembyEmployee.setCellValueFactory(new PropertyValueFactory<ordersData, Integer>("empId"));
				
				getData();
				price.setText(priceToShow+"");

				TableData.setItems(dataList);


			}
		});
		
//		System.out.println("refresh!!!!!!!!!!!");
	}

	@FXML
	void addinsurance(ActionEvent event) {

		try { // open new stage
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("addinshorunce2order.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 394, 598);
			Stage stage = new Stage();
			stage.setTitle("New Window");
			stage.setScene(scene);
			stage.show();

		} catch (IOException e1) {
		}
	}

	@FXML
	void back(ActionEvent event) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  orderes where order_id =" + orderId + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) btnback.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Start.fxml"));
			Scene scene = new Scene(root, 901, 649);
			stage.setScene(scene);
			stage.setTitle("Chose One");
			stage.show();

		} catch (IOException e1) {

		}
	}

	@FXML
	void cashOrder(ActionEvent event) {

		priceBefore.setVisible(false);
		discount.setVisible(false);
		addinsurance.setVisible(false);
		imgInsh.setVisible(false);
		imgPr.setVisible(false);
		imgDis.setVisible(false);

	}

	@FXML
	void deleteItem(ActionEvent event) {
		ObservableList<invoiceData> selectedRows = TableData.getSelectionModel().getSelectedItems();
		ArrayList<invoiceData> rows = new ArrayList<>(selectedRows);
		if (rows.size() == 0) {
			return;
		}
		deleteRow(rows.get(0));
		
		
//		data = new ArrayList<>();
//		dataList = FXCollections.observableArrayList(data);
//		TableData.setEditable(true);
//		counter.setSortable(false);
//		counter.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(TableData.getItems().indexOf(column.getValue())+1));
//		
//		itemParcode.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("par_code"));
//		itemParcode
//				.setCellFactory(TextFieldTableCell.<invoiceData, Integer>forTableColumn(new IntegerStringConverter()));
//
//		itemName.setCellValueFactory(new PropertyValueFactory<invoiceData, String>("itemName"));
//
//		itemQuantity.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("quantity"));
//
//		itemCategory.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("itemCat"));
//
//		itemPrice.setCellValueFactory(new PropertyValueFactory<invoiceData, Double>("full_sale_price"));
//
//		itembyEmployee.setCellValueFactory(new PropertyValueFactory<ordersData, Integer>("empId"));
//
//		getData();
//		TableData.setItems(dataList);
	}

	private void deleteRow(invoiceData row) {
		priceToShow-=row.getFull_sale_price()*row.getQuantity();
		try {
			Connector.a.connectDB();
			System.out.println("delete from invoice where  order_id=" +row.getOrder_id()  + " and par_code="+row.getPar_code()+";");
			Connector.a.ExecuteStatement("delete from invoice where  order_id=" +row.getOrder_id()  + " and par_code="+row.getPar_code()+";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		initialize();
	}

	@FXML
	void insuranceOrder(ActionEvent event) {

		priceBefore.setVisible(true);
		discount.setVisible(true);
		addinsurance.setVisible(true);
		imgInsh.setVisible(true);
		imgPr.setVisible(true);
		imgDis.setVisible(true);
	}

	@FXML
	void print(ActionEvent event) {

	}

	@FXML
	void refresh(ActionEvent event) {

		initialize();
	}

	@FXML
	public void initialize() {

		if (!isCreatOrder) {
			PreparedStatement st2;
			try {

				Connector.a.connectDB();
				String sql = "insert into orderes(id) value(?);";
				PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);
				ps.setInt(1, SceneController.empId);
				ps.execute();
				st2 = Connector.a.connectDB().prepareStatement(" select MAX(order_id) from orderes;");
				ResultSet r2 = st2.executeQuery();
				if (r2.next()) {
					orderId = r2.getInt(1);
				}

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			isCreatOrder = true;
		}
		orderID.setText(String.valueOf(orderId));
		priceBefore.setVisible(false);
		discount.setVisible(false);
		addinsurance.setVisible(false);
		imgInsh.setVisible(false);
		imgPr.setVisible(false);
		imgDis.setVisible(false);

		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		TableData.setEditable(true);
		counter.setSortable(false);
		counter.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(TableData.getItems().indexOf(column.getValue())+1));
		
		itemParcode.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("par_code"));
		itemParcode
				.setCellFactory(TextFieldTableCell.<invoiceData, Integer>forTableColumn(new IntegerStringConverter()));

		itemName.setCellValueFactory(new PropertyValueFactory<invoiceData, String>("itemName"));

		itemQuantity.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("quantity"));

		itemCategory.setCellValueFactory(new PropertyValueFactory<invoiceData, Integer>("itemCat"));

		itemPrice.setCellValueFactory(new PropertyValueFactory<invoiceData, Double>("full_sale_price"));

//		itembyEmployee.setCellValueFactory(new PropertyValueFactory<ordersData, Integer>("empId"));
		getData();
		price.setText(priceToShow+"");

		TableData.setItems(dataList);


	}

	public void getData() {
		priceToShow=0;
		String SQL = "select * from invoice where order_id=" + orderId + ";";

		try {
//			System.out.println("ok 22222");
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);

			while (rs.next()) {
				String SQL2 = "select * from item where par_code=" + rs.getInt(4) + ";";
				java.sql.Statement state2 = Connector.a.connectDB().createStatement();
				ResultSet rs2 = state2.executeQuery(SQL2);
//				System.out.println("ok!!");
//				invoiceData it = null;
				if (rs2.next()) {
//					System.out.println("yes!!");
					invoiceData it = new invoiceData(orderId, rs.getInt(1), rs.getDouble(2), rs.getDouble(3),
							rs.getInt(4), rs2.getInt(8), rs2.getString(1));
					dataList.add(it);
					priceToShow+=rs.getDouble(2)*rs.getInt(1);
//					System.out.println(it.toString());
				}

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

}
