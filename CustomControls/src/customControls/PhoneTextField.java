package customControls;

import javafx.scene.control.TextField;

public class PhoneTextField extends TextField implements ValidatedTextField{

    int stringLen = 0;

    @Override
    public void replaceText(int start, int end, String text) {
        stringLen = getText().length();
        if (text.matches("\\d") && stringLen < 9 || text.isEmpty()) {
            setStyle("-fx-text-fill: black;");
            super.replaceText(start, end, text);
        }

    }


    public String getTextValidated() throws CustomControlsException {
        if(getText().length() < 9) {
            setStyle("-fx-text-fill: red;" +
                    "-fx-text-box-border: red ;");
            throw new CustomControlsException("Phone number must have 9 digits!");
        }

        return getText();
    }




}
