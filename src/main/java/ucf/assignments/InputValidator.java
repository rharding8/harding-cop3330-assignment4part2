package ucf.assignments;

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

    if (Integer.parseInt(date.substring(5, 6)) < 1 || Integer.parseInt(date.substring(5, 6)) > 12) {
      return false;
    }
    if (Integer.parseInt(date.substring(8, 9)) < 1 || Integer.parseInt(date.substring(8, 9)) > 31) {
      return false;
    }
    if (Integer.parseInt(date.substring(5, 6)) == 2 && Integer.parseInt(date.substring(8, 9)) > 29) {
      return false;
    }

    return (Integer.parseInt(date.substring(5, 6)) != 4
            && Integer.parseInt(date.substring(5, 6)) != 6
            && Integer.parseInt(date.substring(5, 6)) != 9
            && Integer.parseInt(date.substring(5, 6)) != 11)
            || Integer.parseInt(date.substring(8, 9)) != 31;
  }
}
