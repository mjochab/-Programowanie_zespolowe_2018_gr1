package customControls;


import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailTextField extends TextField implements ValidatedTextField {



    @Override
    public String getTextValidated() throws CustomControlsException {

        Pattern emailRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailRegex.matcher(getText());

        if (!matcher.matches()) {
            throw new CustomControlsException("Wrong email format!");
        }

        return getText();
    }
}
