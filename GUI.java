import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TextField;
import javafx.scene.text.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ScrollBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.Vector;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.beans.property.SimpleStringProperty;

public class GUI extends Application {

    private static TableView<Vector> table = new TableView();

    public void start(Stage primaryStage) {

        Text title = new Text("☏ 311 CALLS MANAGER ☏");
        title.setId("title");
        Text te1 = new Text("ID:");
        te1.setId("te1");
        Text te2 = new Text("From(YYYY-MM-DD):");
        te2.setId("te2");
        Text te3 = new Text("To(YYYY-MM-DD):");
        te3.setId("te3");

        TextField tf1 = new TextField("");
        TextField tf2 = new TextField("");
        TextField tf3 = new TextField("");
        
        addTextLimiter(tf1,8);
        addTextLimiter(tf2,10);
        addTextLimiter(tf3,10);
        
        tf2.setPrefWidth(200);
        tf3.setPrefWidth(200);
        
        Button btn0=new Button("Search");
        btn0.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String id=tf1.getText();
                if(tf1.getText().equals("")) { //display error window if empty textfield
                		Alert a = new Alert(Alert.AlertType.ERROR);
                		a.setTitle("Error Message");
                		a.setHeaderText("Parameter not found");
                		a.setContentText("Please input an id.");
                		a.showAndWait();
                }	
                else	{
                		query("SELECT * FROM calls WHERE unique_key='" + id + "';");
                }	
            }
        });
        

        Button btn1 = new Button("Search");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String start=tf2.getText();
                String end=tf3.getText();
                if(tf2.getText().equals("")) {
	                	Alert a = new Alert(Alert.AlertType.ERROR);
	            		a.setTitle("Error Message");
	            		a.setHeaderText("Parameter not found");
	            		a.setContentText("Please input a starting date.");
	            		a.showAndWait();
                }
                else if(tf3.getText().equals("")) {
	                	Alert a = new Alert(Alert.AlertType.ERROR);
	            		a.setTitle("Error Message");
	            		a.setHeaderText("Parameter not found");
	            		a.setContentText("Please input an end date.");
	            		a.showAndWait();
                }
                else {
                		System.out.println(tf1.getText());
                    query("SELECT * FROM calls WHERE created_date BETWEEN '" + start + "' AND '" + end + "' ORDER BY Created_date ASC LIMIT 500;");
                    // query for all calls between start date and end date
                }
            }
        });

        Button btn2 = new Button("Reset");
        btn2.setId("Reset");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            		tf1.setText("");
            		tf2.setText("");
            		tf3.setText("");
            		query("SELECT * FROM calls ORDER BY Created_date DESC LIMIT 500;");
            		// resets table and textfields
            }
        });

        table.setEditable(false);

        TableColumn<Vector,String> idCol = new TableColumn("ID");
        idCol.setMinWidth(75);
        idCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vector, String>, ObservableValue<String>>() {
        	@Override
        		/*
        		 *  Alters the cell properties of the table to display a specific index of the data vector
        		 */
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vector, String> p) {
                if (p.getValue() != null) 
                    return new SimpleStringProperty(p.getValue().get(0).toString());
                else 
                    return new SimpleStringProperty("");
            }
        });

        TableColumn<Vector,String> openCol = new TableColumn("Open Date and Time");
        openCol.setMinWidth(180);
        openCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vector, String>, ObservableValue<String>>() {
        	@Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vector, String> p) {
                if (p.getValue() != null) 
                    return new SimpleStringProperty(p.getValue().get(1).toString());
                else 
                    return new SimpleStringProperty("");
            }
        });

        TableColumn<Vector,String> closeCol = new TableColumn("Closed Date and Time");
        closeCol.setMinWidth(180);
        closeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vector, String>, ObservableValue<String>>() {
        	@Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vector, String> p) {
                if (p.getValue() != null) 
                    return new SimpleStringProperty(p.getValue().get(2).toString());
                else 
                    return new SimpleStringProperty("");
            }
        });
        
        TableColumn<Vector,String> depCol = new TableColumn("Department");
        depCol.setMinWidth(100);
        depCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vector, String>, ObservableValue<String>>() {
        	@Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vector, String> p) {
                if (p.getValue() != null) 
                    return new SimpleStringProperty(p.getValue().get(3).toString());
                else 
                    return new SimpleStringProperty("");
            }
        });

        TableColumn<Vector,String> typeCol = new TableColumn("Type of Call");
        typeCol.setMinWidth(175);
        typeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vector, String>, ObservableValue<String>>() {
        	@Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vector, String> p) {
                if (p.getValue() != null) 
                    return new SimpleStringProperty(p.getValue().get(4).toString());
                else 
                    return new SimpleStringProperty("");
            }
        });
        
        TableColumn<Vector,String> zipCol = new TableColumn("Zip Code");
        zipCol.setMinWidth(100);
        zipCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vector, String>, ObservableValue<String>>() {
        	@Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vector, String> p) {
                if (p.getValue() != null) 
                    return new SimpleStringProperty(p.getValue().get(5).toString());
                else 
                    return new SimpleStringProperty("");
            }
        });

        TableColumn<Vector,String> addCol = new TableColumn("Address");
        
        TableColumn<Vector,String> streetCol = new TableColumn("Street");
        streetCol.setMinWidth(200);
        streetCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vector, String>, ObservableValue<String>>() {
        	@Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vector, String> p) {
                if (p.getValue() != null) 
                    return new SimpleStringProperty(p.getValue().get(6).toString());
                else 
                    return new SimpleStringProperty("");
            }
        });

        
        addCol.getColumns().addAll(streetCol,zipCol);

        TableColumn<Vector,String> statCol = new TableColumn("Status");
        statCol.setMinWidth(100);
        statCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vector, String>, ObservableValue<String>>() {
        	@Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vector, String> p) {
                if (p.getValue() != null) 
                    return new SimpleStringProperty(p.getValue().get(7).toString());
                else 
                    return new SimpleStringProperty("");
            }
        });

        table.getColumns().addAll(idCol,openCol,closeCol,depCol,typeCol,addCol,statCol);

        HBox top1 = new HBox(5);
        top1.getChildren().addAll(te1,tf1,btn0,te2,tf2,te3,tf3,btn1,btn2);
        top1.setAlignment(Pos.CENTER);
        top1.setSpacing(20);

        HBox top2 = new HBox(1);
        top2.getChildren().addAll(table);
        top2.setAlignment(Pos.CENTER);

        VBox root = new VBox();
        root.getChildren().addAll(new HBox(),title,top1,top2);
        root.setSpacing(50);
        root.setAlignment(Pos.TOP_CENTER);

	/* You can active this scrollbar using the getChildren function */
	
        ScrollBar hscroll = new ScrollBar();
        hscroll.setMin(0);
        hscroll.setMax(260);
        hscroll.setValue(100);
        hscroll.setOrientation(Orientation.HORIZONTAL);

	/* Setting the size of the window */
	    
        Scene scene = new Scene(root,1920,1080);
        scene.getStylesheets().add("GUI_CSS_Format.css");

        primaryStage.setTitle("311 Calls");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    /*
     * Restricts the amount of characters than can be inputted into textfields
     */
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
    public static void main(String[] args){
        	query("SELECT * FROM calls ORDER BY Created_date DESC LIMIT 500;");
        launch(args);
    }
    /*
     * Accepts a string sql command, retrieves the rows from the database as vectors, and displays them in the table
     */
    public static void query(String line){
    	 try {
     	 String url = "jdbc:postgresql://127.0.0.1:5438/postgres";
         Connection conn = DriverManager.getConnection(url,"ivan","NT0408");
         Statement stat = conn.createStatement();
         ResultSet rs = stat.executeQuery(line);
         ResultSetMetaData metaData = rs.getMetaData();
         ObservableList<Vector> data = FXCollections.observableArrayList(); // Database rows stored in vectors inside an observablelist
         int columns = 8;
         while (rs.next()) {
             Vector row = new Vector(columns);
             for (int i = 1; i <= 20; i++) {
             		if(i==1 || i==2 || i==3 || i==4 || i==6 || i==9 || i==10 || i==20) { //column indexes of the desired data fields
             			if(rs.getObject(i)!=null)
             				if(i==2 || i==3)
             					row.addElement(rs.getObject(i).toString().substring(0,rs.getObject(i).toString().length()-2));	 //cut off the millisecond portion of time
             				else
             					row.addElement(rs.getObject(i).toString());
             			else
             				row.addElement(new String("N/A")); // display N/A if data is blank
             		}
             }	
             data.add(row);
         }		
         table.setItems(data);
         rs.close();
         stat.close();
     }
     catch(SQLException e){
     		System.out.println(e.getMessage());
     }
   }
}
