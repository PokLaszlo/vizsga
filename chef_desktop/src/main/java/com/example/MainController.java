package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class MainController {

    @FXML
    private TableView<Koltseg> KoltsegTable;

    @FXML
    private TableColumn<Koltseg, String> categoryColumn;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TableColumn<Koltseg, String> chefNameColumn;

    @FXML
    private TextField chefNameTextField;

    @FXML
    private TableColumn<Koltseg, String> commentColumn;

    @FXML
    private TextArea commentTextField;

    @FXML
    private TableColumn<Koltseg, Integer> costColumn;

    @FXML
    private TextField costTextField;

    @FXML
    private TableColumn<Koltseg, LocalDate> dateColumn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<Koltseg, Integer> idColumn;

    private ArrayList<Koltseg> koltsegek = FileReader.readChefKoltsegek2025CSV();
    public ObservableList<Koltseg> observableKoltsegek = FXCollections.observableArrayList(koltsegek);

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        chefNameColumn.setCellValueFactory(new PropertyValueFactory<>("chefname"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        setChomboBox();
        datePicker.setValue(LocalDate.now());
        KoltsegTable.setItems(observableKoltsegek);
    }

    @FXML
    void onClickAddButton(ActionEvent event) {
        String chefname = chefNameTextField.getText();
        LocalDate date = datePicker.getValue();
        String category = categoryComboBox.getValue();
        int price = Integer.parseInt(costTextField.getText());
        String comment = commentTextField.getText();
        Koltseg koltseg = new Koltseg(koltsegek.size() + 2, chefname, date, category, price, comment);
        koltsegek.add(koltseg);
        observableKoltsegek.add(koltseg);
        FileWriting.writeChefKoltsegek2025CSV(koltseg);
        clearFields();
    }

    @FXML
    void setChomboBox() {
        HashMap<String, Integer> map = new HashMap<>();
        for (Koltseg koltseg : koltsegek) {
            String category = koltseg.getCategory();
            map.merge(category, 1, Integer::sum);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            categoryComboBox.getItems().add(entry.getKey());
        }
    }

    @FXML
    void onClickChosenKoltseg(MouseEvent event) {
        Koltseg koltseg = KoltsegTable.getSelectionModel().getSelectedItem();
        chefNameTextField.setText(koltseg.getChefname());
        datePicker.setValue(koltseg.getDate());
        categoryComboBox.setValue(koltseg.getCategory());
        costTextField.setText(String.valueOf(koltseg.getPrice()));
        commentTextField.setText(koltseg.getComment());
    }

    private void clearFields(){
        chefNameTextField.clear();
        datePicker.setValue(LocalDate.now());
        categoryComboBox.setValue(null);
        costTextField.clear();
        commentTextField.clear();
    }

}
