package com.fx.emp.cell;

import com.fx.emp.hibernate.entity.Part;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author pscha
 */
public class NumberCell extends TableCell<Part, Long>{
    
     @Override
    protected void updateItem(Long item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
            setStyle("");
        } else if (item != null && !empty) {
            Font font=new Font("Arial",50);
            
            Label label = new Label(item.toString());
            label.setFont(font);
            VBox vBox = new VBox();

            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(label);

            setGraphic(vBox);
            setText(null);
        }
    }
}
