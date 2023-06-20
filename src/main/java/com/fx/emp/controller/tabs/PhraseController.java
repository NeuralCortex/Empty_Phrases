package com.fx.emp.controller.tabs;

import com.fx.emp.Globals;
import com.fx.emp.cell.NumberCell;
import com.fx.emp.cell.PhraseCell;
import com.fx.emp.controller.PopulateInterface;
import com.fx.emp.hibernate.dao.PartDAO;
import com.fx.emp.hibernate.dao.PartDAOImpl;
import com.fx.emp.hibernate.entity.Part;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

/**
 *
 * @author pscha
 */
public class PhraseController implements Initializable, PopulateInterface {

    @FXML
    private HBox hBox;
    @FXML
    private Label lbGen;
    @FXML
    private Button btnGen;
    @FXML
    private TableView<Part> table;

    private final PartDAO partDAO = new PartDAOImpl();
    private final int numberOfElements = 6;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        hBox.setId("hec-background-blue");
        lbGen.setId("hec-text-white");

        lbGen.setText("");
        btnGen.setText(bundle.getString("btn.gen"));

        TableColumn colNr = new TableColumn("Nr.");
        TableColumn colPhrase = new TableColumn("Phrasen");
        
        colNr.setMaxWidth(300);

        colNr.setCellValueFactory(new PropertyValueFactory<>("part"));
        colPhrase.setCellValueFactory(new PropertyValueFactory<>("phrase"));

        colNr.setCellFactory((param) -> {
            return new NumberCell();
        });
        colPhrase.setCellFactory((param) -> {
            return new PhraseCell();
        });

        table.getColumns().addAll(colNr,colPhrase);

        btnGen.setOnAction(e -> {
            genRandomData();
        });
    }

    private void genRandomData() {
        List<Part> list0 = partDAO.getPartList(Globals.PART.PART_0);
        List<Part> list1 = partDAO.getPartList(Globals.PART.PART_1);
        List<Part> list2 = partDAO.getPartList(Globals.PART.PART_2);
        List<Part> list3 = partDAO.getPartList(Globals.PART.PART_3);

        list0 = generateRandomList(list0, numberOfElements);
        list1 = generateRandomList(list1, numberOfElements);
        list2 = generateRandomList(list2, numberOfElements);
        list3 = generateRandomList(list3, numberOfElements);

        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < numberOfElements; i++) {
            String str0 = "";
            String str1 = "";
            String str2 = "";
            String str3 = "";

            if (i < list0.size()) {
                str0 = list0.get(i).getPhrase();
            }
            if (i < list1.size()) {
                str1 = list1.get(i).getPhrase();
            }
            if (i < list2.size()) {
                str2 = list2.get(i).getPhrase();
            }
            if (i < list3.size()) {
                str3 = list3.get(i).getPhrase();
            }

            stringList.add(str0 + " " + str1 + " " + str2 + str3);
        }

        List<Part> listErg = new ArrayList<>();
        int count = 0;
        for (String phrase : stringList) {
            listErg.add(new Part(count++, phrase));
        }

        table.getItems().clear();
        table.setItems(FXCollections.observableArrayList(listErg));
    }

    private List<Part> generateRandomList(List<Part> list, int size) {
        Random rand = new Random();
        while (list.size() > size) {
            int randomIndex = rand.nextInt(list.size());
            list.remove(randomIndex);
        }
        return list;
    }

    @Override
    public void populate() {
        genRandomData();
    }

    @Override
    public void reset() {

    }

    @Override
    public void clear() {

    }
}
