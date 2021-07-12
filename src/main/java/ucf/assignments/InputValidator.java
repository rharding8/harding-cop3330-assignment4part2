package ucf.assignments;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InputValidator {

  public static boolean isNull(String input) {
    return input == null;
  }

  public static boolean descriptionValidator(String description) {
    return (description.length() <= 256 && description.length() >= 1);
  }

  public static boolean dateValidator(String date) {
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
    if (Integer.parseInt(date.substring(5, 7)) == 2 && Integer.parseInt(date.substring(8, 9)) > 29) {
      return false;
    }

    return (Integer.parseInt(date.substring(5, 7)) != 4
            && Integer.parseInt(date.substring(5, 7)) != 6
            && Integer.parseInt(date.substring(5, 7)) != 9
            && Integer.parseInt(date.substring(5, 7)) != 11)
            || Integer.parseInt(date.substring(8, 10)) != 31;
  }

  public static boolean pathValidator(String path) {
    File file = new File(path);
    return file.isDirectory() || file.isFile();
  }
}
