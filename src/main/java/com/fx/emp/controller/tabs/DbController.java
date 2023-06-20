package com.fx.emp.controller.tabs;

import com.fx.emp.Globals;
import com.fx.emp.controller.PopulateInterface;
import com.fx.emp.hibernate.dao.PartDAO;
import com.fx.emp.hibernate.dao.PartDAOImpl;
import com.fx.emp.tools.HelperFunctions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author pscha
 */
public class DbController implements Initializable, PopulateInterface {

    @FXML
    private BorderPane borderPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private HBox hBox;
    @FXML
    private Label lbPart;
    @FXML
    private Button btnInit;

    private final PartDAO partDAO = new PartDAOImpl();
    private BorderPane borderPart0;
    private BorderPane borderPart1;
    private BorderPane borderPart2;
    private BorderPane borderPart3;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        hBox.setId("hec-background-blue");
        lbPart.setId("hec-text-white");

        lbPart.setText(bundle.getString("lb.edit.msg"));
        btnInit.setText(bundle.getString("btn.init"));

        borderPart0 = HelperFunctions.createTab(bundle, Globals.FXML_PART_PATH, new PartController(Globals.PART.PART_0));
        borderPart1 = HelperFunctions.createTab(bundle, Globals.FXML_PART_PATH, new PartController(Globals.PART.PART_1));
        borderPart2 = HelperFunctions.createTab(bundle, Globals.FXML_PART_PATH, new PartController(Globals.PART.PART_2));
        borderPart3 = HelperFunctions.createTab(bundle, Globals.FXML_PART_PATH, new PartController(Globals.PART.PART_3));

        gridPane.addRow(0, borderPart0, borderPart1, borderPart2, borderPart3);

        btnInit.setOnAction(e -> {
            partDAO.deleteAll();
            partDAO.createDB();
            loadData();
        });
    }

    private void loadData() {
        ((PartController) borderPart0.getCenter().getUserData()).populate();
        ((PartController) borderPart1.getCenter().getUserData()).populate();
        ((PartController) borderPart2.getCenter().getUserData()).populate();
        ((PartController) borderPart3.getCenter().getUserData()).populate();
    }

    @Override
    public void populate() {
        loadData();
    }

    @Override
    public void reset() {

    }

    @Override
    public void clear() {

    }
}
