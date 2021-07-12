/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Harding
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

  @Test
  void isNull() {
    // Assert that the InputValidator correctly identifies a null input
    // Assert that InputValidator correctly identifies a non-null input
    assertTrue(InputValidator.isNull(null));
    assertFalse(InputValidator.isNull("Hello!"));
  }

  @Test
  void descriptionValidator() {
    // Assert that InputValidator correctly identifies a description that does not meet requirements
    // Assert InputValidator correctly identifies a valid description
    assertFalse(InputValidator.descriptionValidator(""));
    assertTrue(InputValidator.descriptionValidator("This is a valid description"));
  }

  @Test
  void dateValidator() {
    // Assert that InputValidator correctly identifies a date that does not meet requirements
    // Assert InputValidator correctly identifies a valid date
    assertFalse(InputValidator.dateValidator("2005-16-47"));
    assertTrue(InputValidator.dateValidator("2021-07-11"));
  }

  @Test
  void pathValidator() {
    // Assert that InputValidator correctly identifies an invalid path
    // Assert that InputValidator correctly identifies a valid path
    assertFalse(InputValidator.pathValidator("Not a real path"));
    assertTrue(InputValidator.pathValidator(getClass().getResource("TestInput.json").getPath()));
  }
}