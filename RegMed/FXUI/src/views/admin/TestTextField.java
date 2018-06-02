package views.admin;


import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestTextField extends TextField {



    public String getTextValidated() {

        Pattern emailRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = emailRegex.matcher(getText());

        if (!matcher.matches()) {

        }

        return getText();
    }



//    boolean chainCheck  = false;

//    @Override
//    public void replaceText(int start, int end, String text) {
//
//        if (emailContainsApe() == false && chainCheck == false && itIsLastChain(text) == false&& text.matches("[a-zA-Z0-9._%+-]") || text.isEmpty()) {
//            super.replaceText(start, end, text);
//            System.out.println("firstChain");
//        } else
//
//        if (emailContainsApe() == false && chainCheck == false && itIsLastChain(text) == false && text.matches("@") || text.isEmpty()){
//            super.replaceText(start, end, text);
//        } else
//
//        if (emailContainsApe() && chainCheck == false && text.matches("[a-zA-Z0-9.-]")) {
//            super.replaceText(start, end, text);
//            System.out.println("secondChain");
//            char[] asd = getText().toCharArray();
//            if ( asd[asd.length-1] == '.' ) {
//                chainCheck = true;
//            }
//        } else
//
//
//
//
//
//        if (chainCheck == true && emailContainsApe() && itIsLastChain(text) && text.matches("[a-zA-Z]")) {
//            int chainLen = lastChainLength();
//            System.out.println(chainLen);
//            if (chainLen < 4) {
//                super.replaceText(start, end, text);
//            }
//
//            System.out.println("From lastChain");
//        }
//
//
//
//    }
//
//
//
//    private boolean emailContainsApe() {
//        if (getText().contains("@"))
//        {
//            return true;
//        }
//        return false;
//    }
//
//
//    private boolean itIsLastChain(String text) {
//        if (emailContainsApe() && text.equals(".")) {
//            System.out.println("Go to last chain");
//            return true;
//        }
//
//        return false;
//    }
//
//    private int lastChainLength() {
//
//        int lastDotIndex = getText().lastIndexOf(".");
//        String chainSubstring = getText().substring(lastDotIndex, getText().length());
//        return chainSubstring.length();
//    }




}
