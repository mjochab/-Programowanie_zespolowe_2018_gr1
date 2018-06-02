package customControls;

import com.sun.xml.internal.ws.util.StringUtils;
import javafx.scene.control.TextField;

public class NameTextField extends TextField {


    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }

    public String getTextFormatted() {
        String result = getText().toLowerCase();
        result = StringUtils.capitalize(result);

        return result;
    }


}
