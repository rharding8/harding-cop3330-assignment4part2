package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Comparator;
import java.util.function.Predicate;

public class TodoListController {
  // All given FXML Fields:

  @FXML
  public TextField pathField;
  @FXML
  public Button addButton;
  @FXML
  public TextField dateField;
  @FXML
  public TextField descriptionField;
  @FXML
  public CheckBox completeBox;
  @FXML
  public Button saveButton;
  @FXML
  public Button updateButton;
  @FXML
  public Button removeButton;
  @FXML
  public ListView<Item> itemDisplay;
  @FXML
  public Button dateSortButton;
  @FXML
  public Button showAllButton;
  @FXML
  public Button showCompleteButton;
  @FXML
  public Button showIncompleteButton;
  @FXML
  public Button loadButton;

  // ItemList to be given from TodoListController
  ItemList itemList;
  // ObservableList to store a sorted version of the list in
  ObservableList<Item> sortedList;

  @FXML
  public void addButtonClicked(ActionEvent actionEvent) {
    // Call addToList()
    // Call refresh()
    addToList();
    refresh();
  }

  @FXML
  public void removeButtonClicked(ActionEvent actionEvent) {
    // Call removeFromList() using selection from itemDisplay
    // Call refresh()
    removeFromList(itemDisplay.getSelectionModel().getSelectedItem());
    refresh();
  }

  @FXML
  public void saveButtonClicked(ActionEvent actionEvent) {
    // Call saveList in TodoListIO using path from pathField and itemList
  }

  @FXML
  public void updateButtonClicked(ActionEvent actionEvent) {
    // Call updateItem() using selection from itemDisplay
    // Call refresh()
    updateItem(itemDisplay.getSelectionModel().getSelectedItem());
    refresh();
  }

  public void addToList() {
    // Bind completeBox to boolean value
    // Add new Item to items in itemList using the fields entered and boolean value
    // Make sure sortedList updates as well
    boolean flag = completeBox.isSelected();
    Item i = new Item(descriptionField.getText(), dateField.getText(), flag);
    itemList.addItem(i);
    sortedList.add(i);
  }

  public void removeFromList(Item i) {
    // Remove i from "items" in itemList
    // Make sure sortedList updates as well
    itemList.removeItem(i);
    sortedList.remove(i);
  }

  public void updateItem(Item i) {
    // Bind completeBox to boolean value
    // Edit i using the fields entered and boolean value
    // Ensure the item is updated in both itemList and sortedList
    itemList.removeItem(i);
    sortedList.remove(i);
    boolean flag = completeBox.isSelected();
    i.setDescription(descriptionField.getText());
    i.setDate(dateField.getText());
    i.setCompletion(flag);
    itemList.addItem(i);
    sortedList.add(i);
  }

  @FXML
  public void initialize() {
    // Initialize itemList with placeholder title "List"
    // Set sortedList to "items" in itemList
    // Call refresh()
    itemList = new ItemList("List");
    sortedList = FXCollections.observableArrayList(itemList.getItems());
    refresh();
  }

  @FXML
  public void refresh() {
    // Set description and date fields to "null"
    // Set completeBox to false
    // Set the items in itemDisplay equal to sortedList
    descriptionField.setText(null);
    dateField.setText(null);
    completeBox.setSelected(false);
    itemDisplay.setItems(sortedList);
  }

  @FXML
  public void dateSortClicked(ActionEvent actionEvent) {
    // Call dateSort()
    // Call refresh()
    dateSort();
    refresh();
  }

  public void dateSort() {
    // Sort sortedList using date as a comparator
    sortedList.sort(Comparator.comparing(Item::getDate));
  }

  @FXML
  public void showAllClicked(ActionEvent actionEvent) {
    // Reset sortedList back to the full "items" in itemList
    sortedList = FXCollections.observableArrayList(itemList.getItems());
    refresh();
  }

  @FXML
  public void showCompleteClicked(ActionEvent actionEvent) {
    sortedList.removeIf(Predicate.not(Item::isComplete));
    refresh();
  }

  @FXML
  public void showIncompleteClicked(ActionEvent actionEvent) {
    sortedList.removeIf(Item::isComplete);
    refresh();
  }

  @FXML
  public void loadButtonClicked(ActionEvent actionEvent) {
  }
}
