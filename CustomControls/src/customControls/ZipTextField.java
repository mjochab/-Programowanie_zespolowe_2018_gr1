package customControls;

import com.sun.xml.internal.ws.util.StringUtils;
import javafx.scene.control.TextField;

public class ZipTextField extends TextField implements ValidatedTextField {

    @Override
    public void replaceText(int start, int end, String text) {

        if (text.matches("[\\d-]") && getText().length()<6 || text.isEmpty()) {
            super.replaceText(start, end, text);

        }


    }

    public String getTextValidated() throws CustomControlsException {
        String result = getText();
        if (!result.matches("\\d{2}-\\d{3}")) {
            throw new CustomControlsException("Wrong zip code format!");
        }

        return result;
    }
}
