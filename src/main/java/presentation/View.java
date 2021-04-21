package presentation;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class View {
    public static void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Message");
        alert.setHeaderText(s);
        alert.show();
    }

    public static void createTable(ArrayList<?> listOfObjects, TableView table){
        table.getItems().clear();
        table.getColumns().clear();
        int size = listOfObjects.getClass().getDeclaredFields().length;
        int columnIndex = 0;
        for (Field field : listOfObjects.get(0).getClass().getDeclaredFields()){
            System.out.println( field.getName());
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
