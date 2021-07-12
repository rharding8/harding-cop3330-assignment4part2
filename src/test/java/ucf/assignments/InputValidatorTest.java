package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

  @Test
  void isNull() {
    assertTrue(InputValidator.isNull(null));
    assertFalse(InputValidator.isNull("Hello!"));
  }

  @Test
  void descriptionValidator() {
    assertFalse(InputValidator.descriptionValidator(""));
    assertTrue(InputValidator.descriptionValidator("This is a valid description"));
  }

  @Test
  void dateValidator() {
    assertFalse(InputValidator.dateValidator("No date here"));
    assertTrue(InputValidator.dateValidator("2021-07-11"));
  }

  @Test
  void pathValidator() {
    assertFalse(InputValidator.pathValidator("Not a real path"));
    assertTrue(InputValidator.pathValidator(getClass().getResource("TestInput.json").getPath()));
  }
}