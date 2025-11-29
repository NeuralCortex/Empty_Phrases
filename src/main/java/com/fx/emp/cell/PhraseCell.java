package com.fx.emp.cell;

import com.fx.emp.hibernate.entity.Part;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.text.Font;

/**
 *
 * @author pscha
 */
public class PhraseCell extends TableCell<Part, String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
            setStyle("");
        } else if (item != null && !empty) {
            Font font = new Font("Arial", 30);

            Label label = new Label(item);
            label.setFont(font);

            setGraphic(label);
            setText(null);
        }
    }
}
