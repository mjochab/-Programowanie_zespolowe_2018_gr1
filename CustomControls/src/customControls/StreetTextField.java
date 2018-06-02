package customControls;

import com.sun.xml.internal.ws.util.StringUtils;

public class StreetTextField extends CityTextField implements ValidatedTextField {

    //protected String name = "Street";

    @Override
    public String getTextValidated() throws CustomControlsException {
        String result = getText().toLowerCase();
        result = StringUtils.capitalize(result);
        if (result.length() < 2) {
            throw new CustomControlsException("Street must have at least two characters!");
        }

        return result;
    }


}
