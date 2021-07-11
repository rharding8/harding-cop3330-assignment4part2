/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Harding
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

  @Test
  void getAndSetDescription() {
    // Instantiate Item using test data
    // Assert the data is kept
    // Set description to a new String
    // Assert the new String is kept
    Item testItem = new Item("TestDescription", "TestDate", false);
    assertEquals("TestDescription", testItem.getDescription());
    testItem.setDescription("NewDescription");
    assertEquals("NewDescription", testItem.getDescription());
  }

  @Test
  void getAndSetDate() {
    // Instantiate Item using test data
    // Assert the data is kept
    // Set date to a new String
    // Assert the new String is kept
    Item testItem = new Item("TestDescription", "TestDate", false);
    assertEquals("TestDate", testItem.getDate());
    testItem.setDate("NewDate");
    assertEquals("NewDate", testItem.getDate());
  }

  @Test
  void isComplete() {
    // Instantiate Item using test data
    // Assert the data is kept
    // Set completion to the opposite
    // Assert the new result
    Item testItem = new Item("TestDescription", "TestDate", false);
    assertFalse(testItem.isComplete());
    testItem.setCompletion(true);
    assertTrue(testItem.isComplete());
  }

  @Test
  void testToStringIncomplete() {
    // Instantiate Item using test data (incomplete)
    // Create String for expected result
    // Assert the toString returns the same result
    Item testItem = new Item("Test the program", "2021-07-10", false);
    String expected = "On 2021-07-10: Test the program";
    assertEquals(expected, testItem.toString());
  }

  @Test
  void testToStringComplete() {
    // Instantiate Item using test data (complete)
    // Create String for expected result
    // Assert the toString returns the same result
    Item testItem = new Item("Test the program", "2021-07-10", true);
    String expected = "On 2021-07-10: Test the program (COMPLETE)";
    assertEquals(expected, testItem.toString());
  }
}