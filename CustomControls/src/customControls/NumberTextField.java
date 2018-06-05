package customControls;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField implements ValidatedTextField {

    @Override
    public void replaceText(int start, int end, String text) {

        if (text.matches("[\\d]") || text.isEmpty()) {
            setStyle("-fx-text-fill: black;");
            super.replaceText(start, end, text);
        }


    }

    public String getTextValidated() throws CustomControlsException {
        String result = getText().toLowerCase();
        int len = result.length();

        if (len < 1 || result.isEmpty() || result.equals("")) {
            setStyle("-fx-text-fill: red;" +
                    "-fx-text-box-border: red ;");
            throw new CustomControlsException("Missing number.");
        }

        return result;
    }

}
