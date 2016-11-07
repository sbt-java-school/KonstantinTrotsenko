package ru.sbt.home.view.base.textFields;

import javafx.scene.control.TextField;

/**
 * Класс - обертка для TextField
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class NumberTextField extends TextField {
    public NumberTextField() {
        setMinWidth(30);
        setMaxWidth(30);
    }

    public void replaceText(int start, int end, String text) {
        if (!text.matches("[A-Za-zА-Яа-я]") && !text.matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")) {
            super.replaceText(start, end, text);
        }
    }

    public void replaceSelection(String text) {
        if (!text.matches("[A-Za-zА-Яа-я]") && !text.matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")) {
            super.replaceSelection(text);
        }
    }
}
