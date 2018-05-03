package sample.controller;

import java.net.URL;
import java.sql.ResultSet;
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
    private Button fetchFoods;

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

            //responsible for the food addition in DB
            callDbMethodForFood(food);

        });

        clearDayButton.setOnAction( event ->  {
            textArea.clear();

        });


        fetchFoods.setOnAction(event -> {
            ResultSet foodTable = fetchFoodFromDb();



            String foodName;
            Double foodGram;

            try {

                while (foodTable.next()) {
                    System.out.println(foodTable.getString("food"));
                    System.out.println(foodTable.getDouble("grams"));
                    foodName = foodTable.getString("food");
                    foodGram = foodTable.getDouble("grams");
                    textArea.appendText("food: "+foodName+" grams: "+foodGram+"\n");

                }
            }catch (Exception e){
                System.out.println("probem at while"+e );
            }
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



    public ResultSet fetchFoodFromDb(){

        ResultSet rs = DB.getFoodFromTable(userId);
        return  rs;
    }



}
