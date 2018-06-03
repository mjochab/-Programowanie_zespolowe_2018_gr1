package views.admin;


import com.sun.xml.internal.ws.util.StringUtils;
import customControls.CustomControlsException;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestTextField extends TextField{


    @Override
    public void replaceText(int start, int end, String text) {

        if (text.matches("[-]") || text.isEmpty()) {

        }


    }

    public String getTextValidated() {
        return null;
    }


}
