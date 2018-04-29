package sample.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.database.DB;
import sample.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLOutput;


public class Controller {

    private int userId;

    @FXML
    private Button buttonSignUp;

    @FXML
    private TextField password;

    @FXML
    private TextField userName;

    @FXML
    private Button buttonSignIn;

    @FXML
    void initialize() {



        buttonSignIn.setOnAction(e -> {
            try {
                User user = new User();
                user.setUsername(userName.getText().trim());
                user.setPassword(password.getText().trim());


                if (DB.validateLogin(user)){
                    // change scene if this is true
                    userId = DB.getUserId(user);
                    System.out.println("Controller: users name: "+user.getUsername()+"users id: "+ userId);

                    buttonSignIn.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/view/user.fxml"));
                    loader.load();

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));

                    UserController userController = loader.getController();
                    userController.setUserId(userId);

                    stage.showAndWait();

                }else{
                    System.out.println("please type your username and password");

                }

            }catch (Exception ex){
                System.out.println("problem ..."+ ex);
            }
        });

        buttonSignUp.setOnAction( event -> {
            buttonSignIn.getScene().getWindow().hide();
            FXMLLoader loaderr = new FXMLLoader();
            loaderr.setLocation(getClass().getResource("/sample/view/SignUp.fxml"));
            try {
                loaderr.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loaderr.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();


        });


    } //end of init






}

