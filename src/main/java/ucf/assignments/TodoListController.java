/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Harding
 */

package ucf.assignments;

import javafx.collections.FXCollections;
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
  public Button clearListButton;
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
  @FXML
  public TextField titleField;
  @FXML
  public Button setTitleButton;

  // ItemList to be given from TodoListController
  public ItemList itemList;
  // ObservableList to store a sorted version of the list in
  public ObservableList<Item> filteredList;

  @FXML
  public void initialize() {
    // Initialize itemList with placeholder title "List"
    // Set itemDisplay to display the items in itemList
    // Bind remove and update buttons to only be usable when an item is selected
    // Call refresh()
    itemList = new ItemList("List");
    itemDisplay.setItems(itemList.getItems());
    removeButton.disableProperty().bind(itemDisplay.getSelectionModel().selectedItemProperty().isNull());
    updateButton.disableProperty().bind(itemDisplay.getSelectionModel().selectedItemProperty().isNull());
    refresh();
  }

  @FXML
  public void refresh() {
    // If description, date, and path fields were correctly input and are still editable
      // Set description, date, and path fields to "null"
    // Else
      // Simply set them to be editable again, but leave error messages
    // Set completeBox to false

    if (descriptionField.isEditable())
      descriptionField.setText(null);
    else
      descriptionField.setEditable(true);

    if (dateField.isEditable())
      dateField.setText(null);
    else
      dateField.setEditable(true);

    if (pathField.isEditable())
      pathField.setText(null);
    else
      pathField.setEditable(true);

    completeBox.setSelected(false);
  }

  @FXML
  public void addButtonClicked(ActionEvent actionEvent) {
    // Call addToList() using the information in relevant fields
    // Call refresh()
    addToList(descriptionField.getText(), dateField.getText(), completeBox.isSelected());
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
    // Call updateItem() using selection from itemDisplay and information in relevant fields
    // Call refresh()
    updateItem(itemDisplay.getSelectionModel().getSelectedItem(),
            descriptionField.getText(), dateField.getText(), completeBox.isSelected());
    refresh();
  }

  @FXML
  public void saveButtonClicked(ActionEvent actionEvent) {
    // Call save()
    // Call refresh()
    save();
    refresh();
  }

  @FXML
  public void loadButtonClicked(ActionEvent actionEvent) {
    // Call load()
    // Call refresh()
    load();
    refresh();
  }

  @FXML
  public void dateSortClicked(ActionEvent actionEvent) {
    // Call dateSort() in itemList
    // Call refresh()
    itemList.dateSort();
    refresh();
  }

  @FXML
  public void showAllClicked(ActionEvent actionEvent) {
    // Reset the display to the full list in itemList
    itemDisplay.setItems(itemList.getItems());
  }

  @FXML
  public void showCompleteClicked(ActionEvent actionEvent) {
    // Call showComplete()
    // Set itemDisplay to show the filtered list
    // Call refresh()
    showComplete();
    itemDisplay.setItems(filteredList);
    refresh();
  }

  @FXML
  public void showIncompleteClicked(ActionEvent actionEvent) {
    // Call showIncomplete()
    // Set itemDisplay to show the filtered list
    // Call refresh()
    showIncomplete();
    itemDisplay.setItems(filteredList);
    refresh();
  }

  @FXML
  public void setTitleClicked(ActionEvent actionEvent) {
    // Call setTitle() using the information in titleField
    // Call refresh()
    setTitle(titleField.getText());
    refresh();
  }

  public void addToList(String description, String date, boolean flag) {
    // Set a boolean check to flag if any requirement fails
    // Check if the required fields are:
      // Null in which case:
        // Set the field to contain a fitting error message
        // Set the field to not be editable, as a flag for refresh()
      // Invalid according to validators in InputValidator in which case:
        // Set field to contain an error message fitting for this
        // Set field to be uneditable as a flag for refresh()
    // If the check holds true (Meaning all fields are valid)
      // Add new Item to items in itemList using the fields entered and boolean value

    boolean check = true;
    if (InputValidator.isNull(description)) {
      descriptionField.setText("ERROR: No description entered to add");
      descriptionField.setEditable(false);
      check = false;
    }
    else {
      if (!InputValidator.descriptionValidator(description)) {
        descriptionField.setText("ERROR: Invalid string entered for description");
        descriptionField.setEditable(false);
        check = false;
      }
    }
    if (InputValidator.isNull(date)) {
      dateField.setText("ERROR: No date entered to add");
      dateField.setEditable(false);
      check = false;
    }
    else {
      if (!InputValidator.dateValidator(date)) {
        dateField.setText("ERROR: Invalid string entered for date");
        dateField.setEditable(false);
        check = false;
      }
    }
    if (check) {
      Item i = new Item(description, date, flag);
      itemList.addItem(i);
    }
  }

  public void removeFromList(Item i) {
    // Remove i from "items" in itemList
    itemList.removeItem(i);
  }

  public void updateItem(Item i, String description, String date, boolean flag) {
    // Remove existing item from itemList
    // Check if the required fields are:
      // Null in which case:
        // Do nothing
      // Invalid according to validators in InputValidator in which case:
        // Set field to contain an error message fitting for this
        // Set field to be uneditable as a flag for refresh()
    // If the fields are valid
      // Set the item's fields to the new values
    // Add the now-updated item back into itemList

    itemList.removeItem(i);
    if (!InputValidator.isNull(description) && description.length() != 0) {
      if (InputValidator.descriptionValidator(description)) {
        i.setDescription(description);
      }
      else {
        descriptionField.setText("ERROR: Invalid description entered");
        descriptionField.setEditable(false);
      }
    }
    if (!InputValidator.isNull(date) && date.length() != 0) {
      if (InputValidator.dateValidator(date)) {
        i.setDate(date);
      }
      else {
        dateField.setText("ERROR: Invalid date entered");
        dateField.setEditable(false);
      }
    }
    i.setCompletion(flag);
    itemList.addItem(i);
  }

  public void showComplete() {
    // Set filteredList to be a filter of items in itemList by each item being complete
    filteredList = itemList.getItems().filtered(Item::isComplete);
  }

  public void showIncomplete() {
    // Set filteredList to be a filter of items in itemList by each item not being complete
    filteredList = itemList.getItems().filtered(Predicate.not(Item::isComplete));
  }

  public void setTitle(String title) {
    // Set the title of itemList to the title given
    itemList.setTitle(title);
  }

  public void save() {
    // If path is valid
      // Call saveList in TodoListIO using path from pathField and itemList
    // Else
      // Set pathField to error message
      // Set pathField to be uneditable as a flag for refresh()
    if (InputValidator.pathValidator(pathField.getText())) {
      TodoListIO.saveList(itemList, pathField.getText());
    }
    else {
      pathField.setText("ERROR: Invalid Path Entered");
      pathField.setEditable(false);
    }
  }

  public void load() {
    // If path in pathField is valid
      // Set itemList to list loaded from loadList in TodoListIO using pathField
      // Reset itemDisplay to display this new list
    // Else
      // Set pathField to error message
      // Set pathField to be uneditable as a flag for refresh()
    if (InputValidator.pathValidator(pathField.getText())) {
      itemList = TodoListIO.loadList(pathField.getText());
      itemDisplay.setItems(itemList.getItems());
    }
    else {
      pathField.setText("ERROR: Invalid Path Entered");
      pathField.setEditable(false);
    }
  }

  @FXML
  public void clearListClicked(ActionEvent actionEvent) {
    // Call clearList()
    // Reset itemDisplay to items in itemList
    // Call refresh()
    clearList();
    itemDisplay.setItems(itemList.getItems());
    refresh();
  }

  public void clearList() {
    // Create a new, empty ObservableList of items
    // Set the empty list into itemList
    ObservableList<Item> emptyList = FXCollections.observableArrayList();
    itemList.setItems(emptyList);
  }
}
