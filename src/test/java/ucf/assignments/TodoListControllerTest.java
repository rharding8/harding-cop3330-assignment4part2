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
    // Initialize the ItemList in the controller as a new list
    // Add some test items to the list
    // Compare item retrieved from list to the original test data
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
    // Initialize the ItemList in controller to a new list
    // Add some test items to an expected list
    // Add all those items into the ItemList in TodoListController
    // Remove one item from both
    // Assert that the items all match in the order they were originally in
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
    // Initialize the ItemList in the controller as a new list
    // Add a test item to the list
    // Assert the item is still the same as intended
    // Update one aspect (completion)
    // Assert the aspect has changed
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
    // Instantiate TodoListController
    // Initialize the ItemList in the controller as a new list
    // Add some items into the list
    // Filter the list by completion
    // Make sure all items in filteredList are complete
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
    // Instantiate TodoListController
    // Initialize the ItemList in the controller as a new list
    // Add some items into the list
    // Filter the list by incomplete items
    // Make sure all items in filteredList are not complete
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
    // Instantiate a new TodoListController
    // Initialize ItemList as a new list
    // Change the title using setTitle
    // Assert the new title is kept
    TodoListController controller = new TodoListController();
    controller.itemList = new ItemList("List");
    controller.setTitle("New Name!");
    assertEquals("New Name!", controller.itemList.getTitle());
  }

  @Test
  void clearList() {
    // Instantiate a new TodoListController
    // Initialize the ItemList to a new list
    // Add some test data
    // Clear the list
    // Assert there are no items in the ItemList
    TodoListController controller = new TodoListController();
    controller.itemList = new ItemList("List");
    controller.addToList("This will be removed", "2021-07-11", true);
    controller.clearList();
    assertEquals(0, controller.itemList.getItems().size());
  }
}