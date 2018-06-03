package customControls;

import com.sun.xml.internal.ws.util.StringUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CityTextField extends TextField implements ValidatedTextField {

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ\\x20]") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }


    @Override
    public String getTextValidated() throws CustomControlsException {
        String result = getText().toLowerCase();
        result = StringUtils.capitalize(result);
        if (result.length() < 2) {
            throw new CustomControlsException("City must have at least two characters!");
        }

        return result;
    }
}
