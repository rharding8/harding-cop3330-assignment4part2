/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Harding
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleListControllerTest {

  @Test
  void addToList() {
    // Instantiate SingleListController (SLC)
    // Set fields to some set of test data
    // Add to list
    // Compare item retrieved from list to Item created from the test data
  }

  @Test
  void removeFromList() {
    // Instantiate SingleListController (SLC)
    // Create ItemList with some test data
    // Set itemList in SLC to the test list
    // Remove an item from both the base list and the list in SLC
    // Compare lists
  }

  @Test
  void updateItem() {
    // Instantiate SingleListController (SLC)
    // Create ItemList with some test data
    // Set itemList in SLC to test list
    // Edit an item in itemList
    // Compare item to a test item of the expected data
  }

  @Test
  void initList() {
    // Instantiate SingleListController (SLC)
    // Create ItemList with some test data
    // Call initList with the test list
    // Assert the lists are equal, the titles are equal, and the set of items are equal
  }

  @Test
  void refresh() {
    // Instantiate SingleListController (SLC)
    // Set fields to some set of test data
    // Call refresh()
    // Assert that default values have been restored.
  }

  @Test
  void sortItemListComplete() {
    // Instantiate SingleListController (SLC)
    // Create ItemList with some test data
    // Call initList with the test list
    // Assert the lists are equal, the titles are equal, and the set of items are equal
    // Create a new test list equal to the old but only with complete items
    // Call sortItemList()
    // Assert that sortedList is equal to the second test list
  }

  @Test
  void sortItemListIncomplete() {
    // Instantiate SingleListController (SLC)
    // Create ItemList with some test data
    // Call initList with the test list
    // Assert the lists are equal, the titles are equal, and the set of items are equal
    // Create a new test list equal to the old but only with incomplete items
    // Call sortItemList()
    // Assert that sortedList is equal to the second test list
  }

  @Test
  void unSortItemList() {
    // Instantiate SingleListController (SLC)
    // Create ItemList with some test data
    // Call initList with the test list
    // Assert the lists are equal, the titles are equal, and the set of items are equal
    // Create a new test list equal to the old but only with complete items
    // Call sortItemList()
    // Assert that sortedList is equal to the second test list
    // Call sortItemList() again
    // Assert that sortedList is now equal to the first test list
  }
}