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

  public void addItem(Item i) {
    // Add i to items
    items.add(i);
  }

  public void removeItem(Item i) {
    // Remove i from items
    items.remove(i);
  }

  public ObservableList<Item> completionSort(boolean completion) {
    // Create new ObservableList
    // for every Item in items
      // if Item.isComplete() is equal to completion
        // add Item to new list
    // Return new List
    ObservableList<Item> sortedList = FXCollections.observableArrayList();
    for (Item i: items) {
      if (i.isComplete() == completion) {
        sortedList.add(i);
      }
    }
    return sortedList;
  }

  public ObservableList<Item> sortByDate() {
    // Create new ObservableList and set equal to items
    // Sort the new list by the "date" string of each item in ascending order
    // Return new list
    ObservableList<Item> sortedList = items;
    sortedList.sort(Comparator.comparing(Item::getDate));
    return sortedList;
  }
}
