package application;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class SampleController {

	private ArrayList<Employeedata> data;
	private ObservableList<Employeedata> dataList;

	@FXML
	private TableColumn<Employeedata, String> DateBirthColumn;

	@FXML
	private Button Delete;

	@FXML
	private TextField ID;

	@FXML
	private TableColumn<Employeedata, Integer> IDColumn;

	@FXML
	private TableColumn<Employeedata, String> NameColumn;

	@FXML
	private TextField Newdateofemployment;

	@FXML
	private TextField SearchFiled;

	@FXML
	private TableView<Employeedata> TableData;

	@FXML
	private Button Update;

	@FXML
	private Button add;

	@FXML
	private TextField addDateOfBirth;

	@FXML
	private TextField addName;

	@FXML
	private TextField adddateofemployment;

	@FXML
	private TextField addPassWord;

	@FXML
	private TableColumn<Employeedata, String> dateofemploymentColumn;

	@FXML
	private TextField newDateBirth;

	@FXML
	private TextField newName;

	@FXML
	private TextField oldID;

	@FXML
	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		TableData.setEditable(true);

		IDColumn.setCellFactory(TextFieldTableCell.<Employeedata, Integer>forTableColumn(new IntegerStringConverter()));
		IDColumn.setCellValueFactory(new PropertyValueFactory<Employeedata, Integer>("id"));

		NameColumn.setCellValueFactory(new PropertyValueFactory<Employeedata, String>("employee_name"));
		NameColumn.setCellFactory(TextFieldTableCell.<Employeedata>forTableColumn());
		NameColumn.setOnEditCommit((CellEditEvent<Employeedata, String> t) -> {
			((Employeedata) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setEmployee_name(t.getNewValue()); // display
			// only

			updateName(t.getRowValue().getId(), t.getNewValue());
		});

		DateBirthColumn.setCellValueFactory(new PropertyValueFactory<Employeedata, String>("birthday"));
		DateBirthColumn.setCellFactory(TextFieldTableCell.<Employeedata>forTableColumn());
		DateBirthColumn.setOnEditCommit((CellEditEvent<Employeedata, String> t) -> {
			((Employeedata) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setBirthday(t.getNewValue()); // display
													// only

			updateBDate(t.getRowValue().getId(), t.getNewValue());
		});

		dateofemploymentColumn
				.setCellValueFactory(new PropertyValueFactory<Employeedata, String>("date_of_employment"));
		dateofemploymentColumn.setCellFactory(TextFieldTableCell.<Employeedata>forTableColumn());
		dateofemploymentColumn.setOnEditCommit((CellEditEvent<Employeedata, String> t) -> {
			((Employeedata) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setDate_of_employment(t.getNewValue()); // display
			// only

			updateDateE(t.getRowValue().getId(), t.getNewValue());
		});

		getData();
		TableData.setItems(dataList);
	}

	public void getData() {
		String SQL = "select * from employee";
		try {
			Connector.a.connectDB();
			java.sql.Statement state = Connector.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				Employeedata em = new Employeedata(rs.getString(2).toString(), rs.getDate(3).toString(),
						rs.getDate(4).toString(), rs.getString(5).toString());
				em.setId(rs.getInt(1));
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

	public void updateName(int ID_num, String name) {

		try {
//			System.out.println("update  employee set employee_name = '" + name + "' where id = " + ID_num);
			Connector.a.connectDB();
			Connector.a
					.ExecuteStatement("update  employee set employee_name = '" + name + "' where id = " + ID_num + ";");
			Connector.a.connectDB().close();
//			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void updateBDate(int ID_num, String date) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			java.util.Date myDate = null;
			java.sql.Date sqlDate;
			try {
				myDate = formatter.parse(date);
//				System.out.println(myDate);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate = new java.sql.Date(myDate.getTime());
//			System.out.println(sqlDate);

//			System.out.println("update  employee set birthday = " + sqlDate + " where id = " + ID_num);
			Connector.a.connectDB();
			Connector.a
					.ExecuteStatement("update  employee set birthday = '" + sqlDate + "' where id = " + ID_num + ";");
			Connector.a.connectDB().close();
//			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void updateDateE(int ID_num, String date) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			java.util.Date myDate = null;
			java.sql.Date sqlDate;
			try {
				myDate = formatter.parse(date);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate = new java.sql.Date(myDate.getTime());
//			System.out.println("update  employee set date_of_employment = " + sqlDate + " where id = " + ID_num);
			Connector.a.connectDB();
			Connector.a.ExecuteStatement(
					"update  employee set date_of_employment = " + sqlDate + " where id = " + ID_num + ";");
			Connector.a.connectDB().close();
//			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void addOnAction(ActionEvent event) {

		try {
			Employeedata rc;
			rc = new Employeedata(addName.getText(), addDateOfBirth.getText(), adddateofemployment.getText(),
					addPassWord.getText());
			dataList.add(rc);
			insertData(rc);
			refresh();
			addName.clear();
			addDateOfBirth.clear();
			adddateofemployment.clear();
			addPassWord.clear();
		} catch (Exception e) {

		}

	}

	public void refresh() {
		TableData.getItems().clear();
		getData();
		TableData.setItems(dataList);

	}

	private void insertData(Employeedata rc) {

		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			java.util.Date myDate = null;
			java.sql.Date sqlDate;
			try {
				myDate = formatter.parse(rc.getBirthday());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			sqlDate = new java.sql.Date(myDate.getTime());

			java.util.Date myDate2 = null;
			java.sql.Date sqlDate2;
			try {
				myDate2 = formatter.parse(rc.getDate_of_employment());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate2 = new java.sql.Date(myDate.getTime());

//			System.out.println("Insert into employee (id,employee_name,birthday,date_of_employment) values("
//					+ rc.getId() + ",'" + rc.getEmployee_name() + "','" + sqlDate + "','" + sqlDate2 + "')");

			Connector.a.connectDB();
			String sql = "Insert into employee (employee_name,birthday,date_of_employment,emp_password) values(?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);
			ps.setString(4, rc.getEmp_password());
			ps.setString(1, rc.getEmployee_name());
			ps.setTimestamp(2, new java.sql.Timestamp(myDate.getTime()));
			ps.setTimestamp(3, new java.sql.Timestamp(myDate2.getTime()));
			ps.execute();
//			try {
//				Connector.a.ExecuteStatement(
//						"Insert into employee (id,employee_name,birthday,date_of_employment)values(" + rc.getId() + ",'"
//								+ rc.getEmployee_name() + "','" + sqlDate + "','" + sqlDate2 + "')");
//				Connector.a.connectDB().close();
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
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
			if (ID.getText() != null) {
				int id = Integer.parseInt(ID.getText());
				deleteRow(id);
			}
			ID.clear();
		} catch (Exception e) {

		}
		refresh();

	}

	private void deleteRow(int id) {
		try {
//			System.out.println("delete from  employee where id =" + id + ";");
			Connector.a.connectDB();
			Connector.a.ExecuteStatement("delete from  employee where id =" + id + ";");
			Connector.a.connectDB().close();
//			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void updateOnAction(ActionEvent event) {

		try {
			if (oldID.getText() != null) {
				int id = Integer.parseInt(oldID.getText());
				if (newName.getText().length() > 0) {
//					System.out.println(newName.getText());
					updateName(id, newName.getText());
				}
				if (newDateBirth.getText().length() > 0) {
//					System.out.println(newDateBirth.getText());
					updateBDate(id, newDateBirth.getText());
				}
				if (Newdateofemployment.getText().length() > 0) {
//					System.out.println(Newdateofemployment.getText());
					updateDateE(id, Newdateofemployment.getText());
				}
				refresh();

				oldID.clear();
				newName.clear();
				newDateBirth.clear();
				Newdateofemployment.clear();

			}
		} catch (Exception e) {

		}
		refresh();

	}

}
