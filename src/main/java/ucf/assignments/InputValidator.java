/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Harding
 */

package ucf.assignments;

import java.io.File;

public class InputValidator {

  public static boolean isNull(String input) {
    // Return true if the given string is null
    return input == null;
  }

  public static boolean descriptionValidator(String description) {
    // Return true if given string meets requirements for a valid description
    return (description.length() <= 256 && description.length() >= 1);
  }

  public static boolean dateValidator(String date) {
    // for each char in String
      // Ensure the first four characters are numerical
      // Ensure there are two numerical characters for a month
      // Ensure there are two numerical characters for a day
      // Ensure each is separated by a '-' character
      // Return false if any requirement is failed
    // Ensure month is valid in calender
    // Ensure day is valid in calender
    // If month is february
      // Ensure day is valid for the month and year
    // If month only has 30 days
      // Ensure day is valid in calender
    // Only if all requirements are met, return true
    int i = 0;
    if (date.length() != 10) {
      return false;
    }
    while (i < 4) {
      if (!Character.isDigit(date.charAt(i))) {
        return false;
      }
      i++;
    }
    if (date.charAt(i) != '-')
      return false;
    i++;
    while (i < 7) {
      if (!Character.isDigit(date.charAt(i))) {
        return false;
      }
      i++;
    }
    if (date.charAt(i) != '-')
      return false;
    i++;
    while (i < 10) {
      if (!Character.isDigit(date.charAt(i))) {
        return false;
      }
      i++;
    }

    if (Integer.parseInt(date.substring(5, 7)) < 1 || Integer.parseInt(date.substring(5, 6)) > 12) {
      return false;
    }
    if (Integer.parseInt(date.substring(8, 10)) < 1 || Integer.parseInt(date.substring(8, 9)) > 31) {
      return false;
    }

    if (Integer.parseInt(date.substring(5, 7)) == 2) {
      if (Integer.parseInt(date.substring(0, 4)) % 4 != 0 && Integer.parseInt(date.substring(8, 9)) > 28) {
        return false;
      }
      else if (Integer.parseInt(date.substring(8, 9)) > 29) {
        return false;
      }
    }

    return (Integer.parseInt(date.substring(5, 7)) != 4
            && Integer.parseInt(date.substring(5, 7)) != 6
            && Integer.parseInt(date.substring(5, 7)) != 9
            && Integer.parseInt(date.substring(5, 7)) != 11)
            || Integer.parseInt(date.substring(8, 10)) != 31;
  }

  public static boolean pathValidator(String path) {
    // Set a new File to the path
    // Return true if the path is a directory or file
    // false if the path is not valid
    File file = new File(path);
    return file.isDirectory() || file.isFile();
  }
}
