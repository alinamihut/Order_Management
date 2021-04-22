package presentation;

import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * View Class - contains methods related to the GUI
 */
public class View {
    /**
     * displays an alert on screen
     * @param s
     */
    public static void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Message");
        alert.setHeaderText(s);
        alert.show();
    }

    /**
     * generates the header of the table by extracting through reflection the object properties and
     *  then populates the table with the values of the elements from the list.
     * @param listOfObjects
     * @param table
     */
    public static void createTable(ArrayList<?> listOfObjects, TableView table){
        table.getItems().clear();
        table.getColumns().clear();
        int size = listOfObjects.getClass().getDeclaredFields().length;
        int columnIndex = 0;
        for (Field field : listOfObjects.get(0).getClass().getDeclaredFields()){
            TableColumn<List<Object>, Object> column = new TableColumn<>(field.getName());
            int finalColumnIndex = columnIndex;
            column.setCellValueFactory( new PropertyValueFactory<List<Object>, Object>(field.getName()));
            columnIndex++;
            table.getColumns().add(column);
        }

       for (Object obj: listOfObjects){
           table.getItems().add(obj);
       }

    }




}
