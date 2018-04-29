package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.database.DB;
import sample.model.Food;


public class UserController {

    private int userId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField kCalTextField;

    @FXML
    private TextField foodTextField;

    @FXML
    private Button clearDayButton;

    @FXML
    private Button addFoodButton;

    @FXML
    private TextArea textArea;

    @FXML
    private Label usersLabel;


    @FXML
    void initialize() {

        Food food = new Food();

        addFoodButton.setOnAction(event -> {
            food.setGrams(Double.parseDouble(kCalTextField.getText()));
            food.setName(foodTextField.getText());

           callDbMethodForFood(food);

        });

        clearDayButton.setOnAction( event ->  {
            textArea.clear();

        });

    } //end of init




    public void setUserId(int id) {
        this.userId = id;

        usersLabel.setText("user :"+ this.userId);
    }

    public  int getUserId(){
        return userId;
    }

    //this inserts the food in DB for the user thas is logged in atm
    public void callDbMethodForFood(Food food){
        food.setId(userId);

        DB.insertFoodInDb(food);
    }




}
