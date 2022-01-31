package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.PreparedStatement.ParseInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class inshuranceController {
	

	private ArrayList<inshuranceData> data;
	private ObservableList<inshuranceData> dataList;
	

	@FXML
	private TextField searchID;

	@FXML
	private Button add;

	@FXML
	private TextField addID;

	@FXML
	private TextField addName;

	@FXML
	private Button delete;

	@FXML
	private TextField deleteID;

	@FXML
	private Button update;

	@FXML
	private TextField updateOldID;

	@FXML
	private TextField updateNewID;

	@FXML
	private TextField updateName;

	@FXML
	private TextField updateCompanyName;

	@FXML
	private TextField addCompanyName;

	@FXML
	private TableColumn<inshuranceData, Integer> customerID;

	@FXML
	private TableColumn<inshuranceData, String> customerName;

	@FXML
	private TableColumn<inshuranceData, String> companyName;

	@FXML
	private TableView<inshuranceData> TableData;
	

	public void refresh() {
		TableData.getItems().clear();
		getData();
		TableData.setItems(dataList);

	}
	
	public void getData() {
		String SQL = "select * from inshurance";
		try {
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				inshuranceData em = new inshuranceData(Integer.parseInt(rs.getString(1).toString()),
						rs.getString(2).toString(), rs.getString(3).toString());
				dataList.add(em);
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
	
	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		TableData.setEditable(true);

		customerID.setCellFactory(TextFieldTableCell.<inshuranceData, Integer>forTableColumn(new IntegerStringConverter()));
		customerID.setCellValueFactory(new PropertyValueFactory<inshuranceData, Integer>("customerID"));
		customerID.setOnEditCommit((CellEditEvent<inshuranceData, Integer> t) -> {
			((inshuranceData) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setCustomerID(t.getNewValue());

			updateID(t.getRowValue().getCustomerID(), t.getNewValue());

		});
		
		

		customerName.setCellValueFactory(new PropertyValueFactory<inshuranceData, String>("coustumerName"));
		customerName.setCellFactory(
				TextFieldTableCell.<inshuranceData>forTableColumn());
		customerName.setOnEditCommit((CellEditEvent<inshuranceData, String> t) -> {
			((inshuranceData) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setCoustumerName(t.getNewValue());

			updateName(t.getRowValue().getCustomerID(), t.getNewValue());

		});

		companyName.setCellValueFactory(new PropertyValueFactory<inshuranceData, String>("inshurance_companyName"));
		companyName.setCellFactory(
				TextFieldTableCell.<inshuranceData>forTableColumn());
		companyName.setOnEditCommit((CellEditEvent<inshuranceData, String> t) -> {
			((inshuranceData) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setCoustumerName(t.getNewValue());

			updateName(t.getRowValue().getCustomerID(), t.getNewValue());

		});

		getData();
		TableData.setItems(dataList);
	}
	
	public void updateID(int oldID, int newID) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update inshurance set coustumerID  = " + newID
					+ " where coustumerID = " + oldID + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void updateName(int id, String name) {

		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("update inshurance set coustumerName = '" + name
					+ "' where coustumerID = " + id + ";");
			Connector.a.connectDB().close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
//
//	public void updateCompanyName(int id, String name) {
//
//		try {
//			Connector.a.connectDB();
//			Connector.a.ExecuteStatement("update inshurance set inshurance_companyName = '" + name
//					+ "' where coustumerID = " + id + ";");
//			Connector.a.connectDB().close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//
//	}
	
	@FXML
	void updateOnAction(ActionEvent event) {

		try {
			if (updateOldID.getText() != null) {
				int id = Integer.parseInt(updateOldID.getText());
				if (updateNewID.getText().length() > 0) {
//					System.out.println(newName.getText());
					updateID(id, Integer.parseInt(updateNewID.getText()));
				}
				if (updateName.getText().length() > 0) {
//					System.out.println(newDateBirth.getText());
					updateName(id, updateName.getText());
				}
//				if (updateCompanyName.getText().length() > 0) {
//					System.out.println(Newdateofemployment.getText());
//					updateCompanyName(id, updateCompanyName.getText());
//				}
				initialize();

				updateOldID.clear();
				updateNewID.clear();
				updateName.clear();
				updateCompanyName.clear();

			}
		} catch (Exception e) {

		}
		initialize();

	}
	
	private void insertData(inshuranceData rc) {

		try {

			Connector.a.connectDB();
			String sql = "Insert into inshurance (coustumerID, coustumerName, inshurance_companyName)"
					+ " values(?,?,?)";
			PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);
			ps.setString(1, String.valueOf(rc.getCustomerID()));
			ps.setString(2, rc.getCoustumerName());
			ps.setString(3, rc.getInsurance_companyName());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void addOnAction(ActionEvent event) {

		try {
			inshuranceData rc;
			rc = new inshuranceData(Integer.parseInt(addID.getText()), addName.getText(), addCompanyName.getText());
			dataList.add(rc);
			insertData(rc);
			refresh();
			addID.clear();
			addName.clear();
			addCompanyName.clear();
		} catch (Exception e) {

		}

	}
	
	private void deleteRow(int id) {
		try {
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  inshurance where coustumerID =" + id + ";");
			Connector.a.connectDB().close();
//			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void deleteOnAction(ActionEvent event) {
     
		try {
			if (deleteID.getText() != null) {
				int id = Integer.parseInt(deleteID.getText());
				deleteRow(id);
			}
			deleteID.clear();
		} catch (Exception e) {

		}
		initialize();

	}
	
}
