/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Harding
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TodoList extends Application {

  public static void main(String[] args) {
    // Launch the program
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    // Start the program using TodoList.fxml
    try {
      Parent root = FXMLLoader.load(getClass().getResource("TodoList.fxml"));
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setTitle("To-do Lister");
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
