package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.model.User;

import static sample.database.DB.insertUserInDb;
import static sample.database.DB.validateLogin;


public class ControllerSignUp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpPassword;

    @FXML
    private TextField signUpUserName;

    @FXML
    private TextField signUpConfirm;

    @FXML
    private Button SignUpSignButton;

    @FXML
    private Label SuccessLabel;


    @FXML
    void fcfcfc(ActionEvent event) {



    }

    @FXML
    void initialize() {





        SignUpSignButton.setOnAction(e -> {
            String username,pass,pass2;
            Boolean status;
            User user = new User();

            username = signUpUserName.getText().trim();
            pass = signUpPassword.getText().trim();
            pass2 = signUpConfirm.getText().trim();


            if ( !username.isEmpty() && !pass.isEmpty() && !pass2.isEmpty()) {

                if (pass.equals(pass2)) {

                    //success opote pername ta dedomena stin bash kai allazoume skhnh??..
                    System.out.println("password match");

                    if (!validateLogin(user)) {

                        //this is new..
                        user.setUsername(username);
                        user.setPassword(pass);

                        status = insertUserInDb(user);

                        System.out.println("user added");
                    } else {
                        System.out.println("change name or password");
                    }
                }else{
                    System.out.println("passwords don't match");
                }

            }else{
                System.out.println("more info plz");
            }




        });





    }






}


