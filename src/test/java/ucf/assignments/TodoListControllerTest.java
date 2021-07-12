/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Harding
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoListControllerTest {
  
  @Test
  void addToList() {
    // Instantiate TodoListController
    // Set fields to some set of test data
    // Add to list
    // Compare item retrieved from list to Item created from the test data
    TodoListController controller = new TodoListController();
    controller.itemList = new ItemList("List");
    controller.addToList("Test", "2021-07-11", true);
    Item actual = controller.itemList.getItems().get(0);
    assertEquals("Test", actual.getDescription());
    assertEquals("2021-07-11", actual.getDate());
    assertTrue(actual.isComplete());
  }

  @Test
  void removeFromList() {
    // Instantiate TodoListController
    // Create ItemList with some test data
    // Set itemList in TodoListController to the test list
    // Remove an item from both the base list and the list in TodoListController
    // Compare lists
    TodoListController controller = new TodoListController();
    ItemList original = new ItemList("List");
    Item itemA = new Item("Test1", "Date1", false);
    Item itemB = new Item("Test2", "Date2", true);
    Item itemC = new Item("Test3", "Date3", false);
    original.addItem(itemA);
    original.addItem(itemB);
    original.addItem(itemC);
    controller.itemList = new ItemList("List");
    controller.itemList.setItems(FXCollections.observableArrayList(original.getItems()));
    controller.removeFromList(itemC);
    original.removeItem(itemC);
    for (int i = 0; i < original.getItems().size(); i++) {
      assertEquals(original.getItems().get(i), controller.itemList.getItems().get(i));
    }
  }

  @Test
  void updateItem() {
    // Instantiate TodoListController
    // Create ItemList with some test data
    // Set itemList in TodoListController to test list
    // Edit an item in itemList
    // Compare item to a test item of the expected data
    TodoListController controller = new TodoListController();
    controller.itemList = new ItemList("List");
    controller.addToList("Test", "2021-07-11", false);
    Item actual = controller.itemList.getItems().get(0);
    assertEquals("Test", actual.getDescription());
    assertEquals("2021-07-11", actual.getDate());
    assertFalse(actual.isComplete());
    controller.updateItem(actual, null, null, true);
    assertTrue(actual.isComplete());
  }

  @Test
  void sortComplete() {
    TodoListController controller = new TodoListController();
    controller.itemList = new ItemList("List");
    Item itemA = new Item("Test1", "Date1", false);
    Item itemB = new Item("Test2", "Date2", true);
    Item itemC = new Item("Test3", "Date3", false);
    controller.itemList.addItem(itemA);
    controller.itemList.addItem(itemB);
    controller.itemList.addItem(itemC);
    controller.showComplete();
    for (Item i: controller.filteredList) {
      assertTrue(i.isComplete());
    }
  }

  @Test
  void sortIncomplete() {
    TodoListController controller = new TodoListController();
    controller.itemList = new ItemList("List");
    Item itemA = new Item("Test1", "Date1", false);
    Item itemB = new Item("Test2", "Date2", true);
    Item itemC = new Item("Test3", "Date3", false);
    controller.itemList.addItem(itemA);
    controller.itemList.addItem(itemB);
    controller.itemList.addItem(itemC);
    controller.showIncomplete();
    for (Item i: controller.filteredList) {
      assertFalse(i.isComplete());
    }
  }

  @Test
  void setTitle() {
    TodoListController controller = new TodoListController();
    controller.itemList = new ItemList("List");
    controller.setTitle("New Name!");
    assertEquals("New Name!", controller.itemList.getTitle());
  }
}