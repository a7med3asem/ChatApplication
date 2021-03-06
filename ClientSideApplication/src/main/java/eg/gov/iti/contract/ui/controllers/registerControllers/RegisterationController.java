package eg.gov.iti.contract.ui.controllers.registerControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.clientServerDTO.enums.Gender;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;


public class RegisterationController implements Initializable {
    @FXML
    private JFXTextField  nameText;
    @FXML
    private JFXTextField phoneText;
    @FXML
    private JFXDatePicker birthText ;
    @FXML
    private JFXTextField  emailText;
    @FXML
    private JFXButton registerButton;
    @FXML
    private Label wrongPhone;
    @FXML
    private Label wrongUserName;
    @FXML
    private Label wrongEmail;
    @FXML
    private Label wrongPassword;
    @FXML
    private Label wrongConfirm;
    @FXML
    private  Label DateCheck;
    @FXML
    private  Label GenderCheck;
    @FXML
    private JFXPasswordField passwordText;
    @FXML
    private JFXPasswordField confirmText;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;


    private boolean checkConfirmPass=false;
    UserDto newUser;


    public Gender genderDetermination(){
        if(male.isSelected()){return Gender.MALE;}
        else
        {return Gender.FEMALE;}

    }

    @FXML
    public boolean phoneValidation() {
        if (phoneText.getText().matches("^01[0125]{1}(\\-)?[^0\\D]{1}\\d{7}$")) {

            wrongPhone.setText("");
            phoneText.setUnFocusColor(Color.rgb(218, 228, 238));

        } else {

            wrongPhone.setText("Please add right phone number");
            phoneText.setUnFocusColor(Color.RED);

        }
        return phoneText.getText().matches("^01[0125]{1}(\\-)?[^0\\D]{1}\\d{7}$");
    }



    @FXML
    public boolean userNameValidation() {

        String txt1 = nameText.getText();

        String txt =txt1.replaceAll("\\s+", " ");

        if ((txt.isEmpty() ||txt != " ") && txt.matches("^[a-zA-Z_-][ a-zA-Z0-9_-]{6,14}$")) {
            wrongUserName.setText("");
            nameText.setUnFocusColor(Color.rgb(218, 228, 238));
        } else {
            wrongUserName.setText("Please add a valid user name");
            nameText.setUnFocusColor(Color.RED);
        }

        return ((txt.isEmpty() ||txt != " ") && txt.matches("^[a-zA-Z_-][ a-zA-Z0-9_-]{6,14}$"));

    }

    @FXML
    public boolean validateEmail() {
        if (!emailText.getText().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            wrongEmail.setText("E-mail Is Not Valid");
            emailText.setUnFocusColor(Color.RED);


        } else {
            wrongEmail.setText("");
            emailText.setUnFocusColor(Color.rgb(218, 228, 238));

        }
        return emailText.getText().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
    }


    @FXML
    public boolean strongPassword() {
        if (!passwordText.getText().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W\\_])[a-zA-Z0-9\\W\\_]{8,15}$")) {
            wrongPassword.setText("Please add a strong password");
            passwordText.setUnFocusColor(Color.RED);
        } else {
            wrongPassword.setText("");
            passwordText.setUnFocusColor(Color.rgb(218, 228, 238));

        }
        return passwordText.getText().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W\\_])[a-zA-Z0-9\\W\\_]{8,15}$");
    }


    public boolean validatePasswordMatch() {

        if(strongPassword()){

            if (confirmText.getText().equals(passwordText.getText())) {
                checkConfirmPass = true;
                confirmText.setUnFocusColor(Color.rgb(218, 228, 238));
                wrongConfirm.setText("");
            } else {
                wrongConfirm.setText("Please add right confirmation password");
                checkConfirmPass = false;
                confirmText.setUnFocusColor(Color.RED);
            }

        }return checkConfirmPass;}

    public boolean genderSelected() {
        if (male.isSelected() || female.isSelected()) {
            GenderCheck.setText("");
            return true;
        } else {
            GenderCheck.setText("Please select your gender");
            return false;
        }
    }

    public boolean dateSelected() {
        if (birthText.getValue() != null) {
            DateCheck.setText("");
            return true;
        } else {
            DateCheck.setText("Please add your birth date");
            return false;
        }
    }


    public boolean userDataValid() {


            return phoneValidation() && userNameValidation() && validateEmail() && validateEmail() && validatePasswordMatch() && strongPassword() && genderSelected() && dateSelected();

    }
    public void register()  {

        if (userDataValid()) {
            System.out.println("success");
        }}


    public void registeration(UserDto newUser) {

        if (userDataValid()) {
            newUser.setFullName(nameText.getText());
            newUser.setEmail(emailText.getText());
            newUser.setPassword(passwordText.getText());
            newUser.setPhoneNumber(phoneText.getText());
            newUser.setUserGender(genderDetermination());
            newUser.setDateOfBirth(Date.valueOf(birthText.getValue()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        registerButton.setOnAction((event) -> {
            register();
            newUser = new UserDto();

                registeration(newUser);



        });

    }

}

