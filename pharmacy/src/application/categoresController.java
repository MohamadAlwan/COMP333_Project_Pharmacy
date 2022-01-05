package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


public class categoresController {

	private ArrayList<Categores> data;
	private ObservableList<Categores> dataList;
	
	
    @FXML
    private Button Delete;

    @FXML
    private TextField ID;

    @FXML
    private TableColumn<Categores, Integer> IDColumn;

    @FXML
    private TableColumn<Categores, String> NameColumn;

    @FXML
    private TextField New_Number_of_Items;

    @FXML
    private TextField SearchFiled;

    @FXML
    private TableView<Categores> TableData;

    @FXML
    private Button Update;

    @FXML
    private Button add;

    @FXML
    private TextField addID;

    @FXML
    private TextField add_Category_Name;

    @FXML
    private TextField add_Number_of_Items;

    @FXML
    private TextField newID;

    @FXML
    private TextField newName;

    @FXML
    private TableColumn<Categores, Integer> numItemsColumn;

    @FXML
    private TextField oldID;

    @FXML
    void addOnAction(ActionEvent event) {
    	
    try {
   		Categores rc;
   		rc = new Categores(Integer.parseInt(addID.getText()), add_Category_Name.getText(), Integer.parseInt(add_Number_of_Items.getText()));
   		dataList.add(rc);
   		insertData(rc);
    	refresh();
   		addID.clear();
   		add_Category_Name.clear();
   		add_Number_of_Items.clear();
    	} catch (Exception e) {

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
			Connector.a.ExecuteStatement("delete from  categores where cat_id =" + id + ";");
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
				
				if (newID.getText().length() > 0) {
//					System.out.println(newName.getText());
					updateID(id, newID.getText());
				}
				
				if (newName.getText().length() > 0) {
//					System.out.println(newDateBirth.getText());
					updateName(id, newName.getText());
				}
				
				if (New_Number_of_Items.getText().length() > 0) {
//					System.out.println(newDateBirth.getText());
					updateNItems(id, New_Number_of_Items.getText());
				}
				
				refresh();

				oldID.clear();
				newName.clear();
				newName.clear();

			}
		} catch (Exception e) {

		}
		refresh();

	}



    
    
    
    
    private void insertData(Categores rc) {

		try {
			
			
			Connector.a.connectDB();
			String sql = "Insert into categores (cat_id, categores_name, number_of_item) values(?,?,?)";
			PreparedStatement ps = (PreparedStatement) Connector.a.connectDB().prepareStatement(sql);
			
			ps.setInt(1, rc.getCat_id());
			ps.setString(2, rc.getCategores_name());
			ps.setInt(3, rc.getNumber_of_item());
			ps.execute();

			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    
    
    public void refresh() {
		TableData.getItems().clear();
		getData();
		TableData.setItems(dataList);

	}
    

@FXML
	public void initialize() {
	data = new ArrayList<>();
	dataList = FXCollections.observableArrayList(data);
	TableData.setEditable(true);

	IDColumn.setCellFactory(TextFieldTableCell.<Categores, Integer>forTableColumn(new IntegerStringConverter()));
	IDColumn.setCellValueFactory(new PropertyValueFactory<Categores, Integer>("cat_id"));

	NameColumn.setCellValueFactory(new PropertyValueFactory<Categores, String>("categores_name"));
	NameColumn.setCellFactory(TextFieldTableCell.<Categores>forTableColumn());
	NameColumn.setOnEditCommit((CellEditEvent<Categores, String> t) -> {
		((Categores) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCategores_name(t.getNewValue()); // display
		// only

		updateName(t.getRowValue().getCat_id(), t.getNewValue());
	});

	numItemsColumn.setCellFactory(TextFieldTableCell.<Categores, Integer>forTableColumn(new IntegerStringConverter()));
	numItemsColumn.setCellValueFactory(new PropertyValueFactory<Categores, Integer>("number_of_item"));
	numItemsColumn.setOnEditCommit((CellEditEvent<Categores, Integer> t) -> {
		((Categores) t.getTableView().getItems().get(t.getTablePosition().getRow()))
				.setNumber_of_item(t.getNewValue()); // display
												// only

		updateNItems(t.getRowValue().getCat_id(), Integer.toString(t.getNewValue()));
	});


	getData();
	TableData.setItems(dataList);
}



	public void getData() {
	String SQL = "select * from categores";
	try {
		Connector.a.connectDB();
		java.sql.Statement state = Connector.a.connectDB().createStatement();
		ResultSet rs = state.executeQuery(SQL);
		while (rs.next()) {
			Categores em = new Categores(rs.getInt(1), rs.getString(2) , rs.getInt(3));
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
					.ExecuteStatement("update  categores set categores_name = '" + name + "' where cat_id = " + ID_num + ";");
			Connector.a.connectDB().close();
//			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	
	public void updateNItems(int ID_num, String New_Number_of_Items) {

		try {
//			System.out.println("update  employee set employee_name = '" + name + "' where id = " + ID_num);
			Connector.a.connectDB();
			Connector.a
					.ExecuteStatement("update  categores set number_of_item = '" + New_Number_of_Items + "' where cat_id = " + ID_num + ";");
			Connector.a.connectDB().close();
//			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}


	public void updateID(int ID_num, String id) {

		try {
//			System.out.println("update  employee set employee_name = '" + name + "' where id = " + ID_num);
			Connector.a.connectDB();
			Connector.a
					.ExecuteStatement("update  categores set cat_id = '" + id + "' where cat_id = " + ID_num + ";");
			Connector.a.connectDB().close();
//			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	
	
	
	
}
