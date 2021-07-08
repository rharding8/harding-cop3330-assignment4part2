/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Harding
 */

package ucf.assignments;

public class Item {
  private String description;
  private String date;
  private boolean completion;

  public Item(String description, String date, boolean completion) {
    // Set this.description to given description
    // Set this.date to given date
    // Set this.completion to given completion
    this.description = description;
    this.date = date;
    this.completion = completion;
  }

  public String getDescription() {
    // Return description
    return description;
  }

  public void setDescription(String description) {
    // Set this.description to given description
    this.description = description;
  }

  public String getDate() {
    // Return date
    return date;
  }

  public void setDate(String date) {
    // Set this.date to given date
    this.date = date;
  }

  public boolean isComplete() {
    // Return completion
    return completion;
  }

  public void setCompletion(boolean completion) {
    // Set this.completion to given completion
    this.completion = completion;
  }

  @Override
  public String toString() {
    // Create new String constructed as:
      // "On [DATE]: [DESCRIPTION]"
    // If completion is true
      // Append "(Complete)" to the String
    // Return the string
    StringBuilder output = new StringBuilder();
    output.append("On ").append(date).append(": ").append(description);
    if (isComplete())
      output.append(" (COMPLETE)");
    return output.toString();
  }
}
