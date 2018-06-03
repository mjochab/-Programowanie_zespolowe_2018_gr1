package customControls;

import javafx.scene.control.TextField;

public class PeselTextField extends TextField implements ValidatedTextField{

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[0-9]") && getText().length() < 11 || text.isEmpty()) {
                super.replaceText(start, end, text);
            } else {
                //last value, which i removed
                String removedDigit = getText().substring(getText().length()-1);
                String s = removeLastDigit(getText());
                setText(s + removedDigit);
                super.positionCaret(getText().length());
            }


    }

    private String removeLastDigit(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public String getTextValidated() throws CustomControlsException {
        if(getText().length() < 11) {
            throw new CustomControlsException("PESEL must have 11 digits!");
        }

        return getText();
    }


}
