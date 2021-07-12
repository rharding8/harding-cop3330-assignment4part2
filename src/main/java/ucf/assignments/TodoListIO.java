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
    // If path leads to directory
      // Set path to a new file named "list.title".json
    // Write items in the given list to the file given by path, using Json code
    // Flush writer to ensure it writes correctly
    // If there is an exception, print stack trace to console
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
    // Create a new ItemList, using the filename of the file given by the path as the title
    // Read a JSon file into an ArrayList of items
    // Use those items to create the items in ItemList as an observableArrayList
    // Print stack trace if the file can't be found
    // Return the list
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
