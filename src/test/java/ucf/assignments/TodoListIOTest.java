/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Harding
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TodoListIOTest {

  @Test
  void saveList() {
    // Create an ItemList to save and add some test data
    // Call static saveList() function with a path to output file (In Resources)
    // Check that the output file now exists
    ItemList list = new ItemList("TestOutput");
    list.addItem(new Item("TestingTesting", "2021-07-11", false));
    TodoListIO.saveList(list, getClass().getResource("./").getPath());
    assertTrue(new File(getClass().getResource("TestOutput.json").getPath()).exists());
  }

  @Test
  void loadList() {
    // Load TestInput.json into a new ItemList
    // Assert ItemList's contents are equal to known values
    ItemList list = TodoListIO.loadList(getClass().getResource("TestInput.json").getPath());
    assertEquals("TestingTesting", list.getItems().get(0).getDescription());
    assertEquals("2021-07-11", list.getItems().get(0).getDate());
    assertTrue(list.getItems().get(0).isComplete());
  }

  @Test
  void saveAndLoadList() {
    // Create an ItemList to save
    // Call static saveList() function with a path to output file
    // Check that the output file now exists
    // Load an ItemList out of the output file
    // Assert the contents of this list equal the contents of the original
    ItemList list = new ItemList("TestOutput");
    list.addItem(new Item("TestingTesting", "2021-07-11", false));
    TodoListIO.saveList(list, getClass().getResource("./").getPath());
    assertTrue(new File(getClass().getResource("TestOutput.json").getPath()).exists());
    ItemList newList = TodoListIO.loadList(getClass().getResource("TestOutput.json").getPath());
    assertEquals(list.getTitle(), newList.getTitle());
    assertEquals(list.getItems().get(0).getDescription(), newList.getItems().get(0).getDescription());
    assertEquals(list.getItems().get(0).getDate(), newList.getItems().get(0).getDate());
    assertEquals(list.getItems().get(0).isComplete(), newList.getItems().get(0).isComplete());
  }
}