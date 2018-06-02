package customControls;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField implements ValidatedTextField {

    @Override
    public void replaceText(int start, int end, String text) {

        if (text.matches("[\\d]") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }


    }

    public String getTextValidated() {
        String result = getText().toLowerCase();
        int len = result.length();

        if (len < 1 || result.isEmpty() || result.equals("")) {
            System.out.println("Nie dziala");
        }

        return result;
    }

}
