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
    descriptionField.setText(null);
    dateField.setText(null);
    completeBox.setSelected(false);
  }

  @FXML
  public void addButtonClicked(ActionEvent actionEvent) {
    // Call addToList()
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
    // Call updateItem() using selection from itemDisplay
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
    // Call setTitle()
    // Call refresh()
    setTitle(titleField.getText());
    refresh();
  }

  public void addToList(String description, String date, boolean flag) {
    // Bind completeBox to boolean value
    // Add new Item to items in itemList using the fields entered and boolean value
    // Make sure sortedList updates as well
    boolean check = true;
    if (InputValidator.isNull(description)) {
      descriptionField.setText("ERROR: No description entered to add");
      check = false;
    }
    if (InputValidator.isNull(date)) {
      dateField.setText("ERROR: No date entered to add");
      check = false;
    }
    if (!InputValidator.descriptionValidator(description)) {
      descriptionField.setText("ERROR: Invalid string entered for description");
      check = false;
    }
    if (!InputValidator.dateValidator(date)) {
      dateField.setText("ERROR: Invalid string entered for date");
      check = false;
    }
    if (check) {
      Item i = new Item(description, date, flag);
      itemList.addItem(i);
    }
  }

  public void removeFromList(Item i) {
    // Remove i from "items" in itemList
    // Make sure sortedList updates as well
    itemList.removeItem(i);
  }

  public void updateItem(Item i, String description, String date, boolean flag) {
    // Bind completeBox to boolean value
    // Edit i using the fields entered and boolean value
    // Ensure the item is updated in both itemList and sortedList
    itemList.removeItem(i);
    if (!InputValidator.isNull(description)) {
      if (InputValidator.descriptionValidator(description)) {
        i.setDescription(description);
      }
      else {
        descriptionField.setText("ERROR: Invalid description entered");
      }
    }
    if (!InputValidator.isNull(date)) {
      if (InputValidator.dateValidator(date)) {
        i.setDate(date);
      }
      else {
        dateField.setText("ERROR: Invalid date entered");
      }
    }
    i.setCompletion(flag);
    itemList.addItem(i);
  }

  public void showComplete() {
    filteredList = itemList.getItems().filtered(Item::isComplete);
  }

  public void showIncomplete() {
    filteredList = itemList.getItems().filtered(Predicate.not(Item::isComplete));
  }

  public void setTitle(String title) {
    // Set the title of itemList to the title entered in titleField
    itemList.setTitle(title);
  }

  public void save() {
    // If path is valid
      // Call saveList in TodoListIO using path from pathField and itemList
    // Else
      // Set pathField to error message
    if (InputValidator.pathValidator(pathField.getText())) {
      TodoListIO.saveList(itemList, pathField.getText());
    }
    else {
      pathField.setText("ERROR: Invalid Path Entered");
    }
  }

  public void load() {
    // If path in pathField is valid
      // Set itemList to list loaded from loadList in TodoListIO using pathField
      // Reset itemDisplay to display this new list
    // Else
      // Set pathField to error message
    if (InputValidator.pathValidator(pathField.getText())) {
      itemList = TodoListIO.loadList(pathField.getText());
      itemDisplay.setItems(itemList.getItems());
    }
    else {
      pathField.setText("ERROR: Invalid Path Entered");
    }
  }
}
