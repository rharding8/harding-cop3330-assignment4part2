/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Harding
 */

package ucf.assignments;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import java.io.*;
import java.util.ArrayList;

public class TodoListIO {
  public static void saveList(ItemList list, String path) {
    // Convert list into Json code
    // Save as Json file to file given by path
    File file = new File(path);
    if (file.isDirectory()) {
      path = path + "/" + list.getTitle() + ".json";
    }
    try {
      Gson gson = new Gson();
      FileWriter writer = new FileWriter(path);
      gson.toJson(list.getItems(), writer);
      writer.flush();
      writer.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static ItemList loadList(String path) {
    // Convert Json file given back into an ItemList
    // Return ItemList
    ItemList list = new ItemList((new File(path).getName()).replaceFirst("[.][^.]+$", ""));
    try {
      Gson gson = new Gson();
      FileReader reader = new FileReader(path);
      ArrayList<Item> items = gson.fromJson(reader, new TypeToken<ArrayList<Item>>() {}.getType());
      list.setItems(FXCollections.observableArrayList(items));
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return list;
  }
}
