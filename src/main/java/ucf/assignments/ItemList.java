/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Harding
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;


public class ItemList {
  private String title;
  private ObservableList<Item> items;

  public ItemList(String title) {
    // Set this.title to given title
    // Initialize items as an observableArrayList
    this.title = title;
    this.items = FXCollections.observableArrayList();
  }

  public String getTitle() {
    // Return title
    return title;
  }

  public void setTitle(String title) {
    // Set this.title to given title
    this.title = title;
  }

  public ObservableList<Item> getItems() {
    // Return items
    return items;
  }

  public void setItems(ObservableList<Item> itemList) {
    items = itemList;
  }

  public void addItem(Item i) {
    // Add i to items
    items.add(i);
  }

  public void removeItem(Item i) {
    // Remove i from items
    items.remove(i);
  }

  public void dateSort() {
    // Sort items by comparator date
    items.sort(Comparator.comparing(Item::getDate));
  }
}
