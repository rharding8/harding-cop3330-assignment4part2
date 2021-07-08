/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Harding
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoListIOTest {

  @Test
  void saveList() {
    // Create an ItemList to save
    // Call static saveList() function with a path to output file
    // Check that the output file now exists
  }

  @Test
  void saveAllLists() {
    // Create a list of ItemLists
    // Call static saveAllLists() function with a path to the output folder
    // Assert every list has a file
  }

  @Test
  void loadList() {
    // Create an ItemList to save
    // Call static saveList() function with a path to output file
    // Check that the output file now exists
    // Load an ItemList out of the output file
    // Assert it equals the original
  }

  @Test
  void loadMultipleLists() {
    // Create a list of ItemLists
    // Call static saveAllLists() function with a path to output folder
    // Assert every list has a file
    // Load all lists into a new list
    // Assert that this list contains the same elements as the original
  }
}