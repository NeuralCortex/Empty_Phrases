package com.fx.emp.controller.tabs;

import com.fx.emp.Globals;
import com.fx.emp.controller.PopulateInterface;
import com.fx.emp.hibernate.dao.PartDAO;
import com.fx.emp.hibernate.dao.PartDAOImpl;
import com.fx.emp.hibernate.entity.Part;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

/**
 *
 * @author pscha
 */
public class PartController implements Initializable, PopulateInterface {

    @FXML
    private Label lbPart;
    @FXML
    private HBox hBox;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRename;
    @FXML
    private Button btnDel;
    @FXML
    private TextField tfAdd;
    @FXML
    private TextField tfRename;
    @FXML
    private TableView<Part> table;

    private final PartDAO partDAO = new PartDAOImpl();
    private final Globals.PART part;
    private int width = 100;

    public PartController(Globals.PART part) {
        this.part = part;
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        hBox.setId("blue");

        lbPart.setText(bundle.getString("lb.part") + " " + part.ordinal() + ":");
        btnAdd.setText(bundle.getString("btn.add"));
        btnRename.setText(bundle.getString("btn.rename"));
        btnDel.setText(bundle.getString("btn.del"));
        
        btnAdd.getStyleClass().add("btn-blue");
        btnRename.getStyleClass().add("btn-blue");
        btnDel.getStyleClass().add("btn-indigo");
        
        tfAdd.setPromptText("Phrase");
        tfRename.setPromptText("Phrase");

        btnAdd.setPrefWidth(width);
        btnRename.setPrefWidth(width);

        TableColumn colPhrase = new TableColumn("Phrase Part");

        colPhrase.setCellValueFactory(new PropertyValueFactory<>("phrase"));

        table.getColumns().addAll(colPhrase);

        table.getSelectionModel().selectedItemProperty().addListener((ov, o, n) -> {
            tfRename.setText(n.getPhrase());
        });

        btnDel.setOnAction(e -> {
            Part part = table.getSelectionModel().getSelectedItem();
            if (part != null) {
                table.getItems().remove(part);
                partDAO.delPart(part);
            }
        });

        btnRename.setOnAction(e -> {
            Part part = table.getSelectionModel().getSelectedItem();
            if (part != null) {
                part.setPhrase(tfRename.getText());
                Part partDB = partDAO.renamePart(part);
                int idx = table.getSelectionModel().getSelectedIndex();
                table.getItems().set(idx, partDB);
                tfRename.setText("");
            }
        });

        btnAdd.setOnAction(e -> {
            if (tfAdd != null && !tfAdd.getText().isEmpty()) {
                Part part = new Part(this.part.ordinal(), tfAdd.getText());
                Part partDB = partDAO.addPart(part);
                table.getItems().add(partDB);
                tfAdd.setText("");
            }
        });
    }

    @Override
    public void populate() {
        List<Part> list = partDAO.getPartList(part);
        table.getItems().clear();
        table.setItems(FXCollections.observableArrayList(list));
    }

    @Override
    public void reset() {

    }

    @Override
    public void clear() {
        table.getItems().clear();
    }
}
