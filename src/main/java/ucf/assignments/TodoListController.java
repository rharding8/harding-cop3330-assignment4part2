package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
  ObservableList<Item> filteredList;

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
  public void updateButtonClicked(ActionEvent actionEvent) {
    // Call updateItem() using selection from itemDisplay
    // Call refresh()
    updateItem(itemDisplay.getSelectionModel().getSelectedItem());
    refresh();
  }

  @FXML
  public void saveButtonClicked(ActionEvent actionEvent) {
    // Call saveList in TodoListIO using path from pathField and itemList
    TodoListIO.saveList(itemList, pathField.getText());
  }

  public void addToList() {
    // Bind completeBox to boolean value
    // Add new Item to items in itemList using the fields entered and boolean value
    // Make sure sortedList updates as well
    boolean flag = completeBox.isSelected();
    Item i = new Item(descriptionField.getText(), dateField.getText(), flag);
    itemList.addItem(i);
  }

  public void removeFromList(Item i) {
    // Remove i from "items" in itemList
    // Make sure sortedList updates as well
    itemList.removeItem(i);
  }

  public void updateItem(Item i) {
    // Bind completeBox to boolean value
    // Edit i using the fields entered and boolean value
    // Ensure the item is updated in both itemList and sortedList
    itemList.removeItem(i);
    boolean flag = completeBox.isSelected();
    if (descriptionField.getText() != null)
      i.setDescription(descriptionField.getText());
    if (dateField.getText() != null)
      i.setDate(dateField.getText());
    i.setCompletion(flag);
    itemList.addItem(i);
  }

  @FXML
  public void initialize() {
    // Initialize itemList with placeholder title "List"
    // Set sortedList to "items" in itemList
    // Call refresh()
    itemList = new ItemList("List");
    itemDisplay.setItems(itemList.getItems());
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
  }

  @FXML
  public void dateSortClicked(ActionEvent actionEvent) {
    // Call dateSort()
    // Call refresh()
    itemList.dateSort();
    refresh();
  }

  @FXML
  public void showAllClicked(ActionEvent actionEvent) {
    itemDisplay.setItems(itemList.getItems());
  }

  @FXML
  public void showCompleteClicked(ActionEvent actionEvent) {
    filteredList = itemList.getItems().filtered(Item::isComplete);
    itemDisplay.setItems(filteredList);
  }

  @FXML
  public void showIncompleteClicked(ActionEvent actionEvent) {
    filteredList = itemList.getItems().filtered(Predicate.not(Item::isComplete));
    itemDisplay.setItems(filteredList);
  }

  @FXML
  public void loadButtonClicked(ActionEvent actionEvent) {
    itemList = TodoListIO.loadList(pathField.getText());
    itemDisplay.setItems(itemList.getItems());
    refresh();
  }
}
