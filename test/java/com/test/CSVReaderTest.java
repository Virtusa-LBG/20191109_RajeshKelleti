package com.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

@RunWith(JUnit4ClassRunner.class)
public class CSVReaderTest {

	private CSVReader objectUnderTest;

	@Test
	public void testCsvElementsPresence() {
		objectUnderTest = new CSVReader();
		Assert.assertTrue("Expected element: Name at row 1", objectUnderTest.readElements("test.csv", 1, "Name"));
		Assert.assertTrue("Expected element: Address at row 1", objectUnderTest.readElements("test.csv", 1, "Address"));
		Assert.assertTrue("Expected element: XYZ at row 2", objectUnderTest.readElements("test.csv", 2, "XYZ"));
		Assert.assertTrue("Expected element: Del at row 3", objectUnderTest.readElements("test.csv", 3, "Del"));
	}

	@Test
	public void testCsvElementsNotPresence() {
		objectUnderTest = new CSVReader();
		Assert.assertFalse("Not Expected element: Name at row 3", objectUnderTest.readElements("test.csv", 3, "Name"));
		Assert.assertFalse("Not Expected element: Del at row 1", objectUnderTest.readElements("test.csv", 1, "Del"));
	}

	@Test
	public void testCsvElementsCaseSensitivity() {
		objectUnderTest = new CSVReader();
		Assert.assertFalse("name should not present", objectUnderTest.readElements("test.csv", 2, "name"));
		Assert.assertFalse("xyz should not present", objectUnderTest.readElements("test.csv", 2, "xyz"));
	}

	@Test
	public void testUnavailableCSVRead() {
		objectUnderTest = new CSVReader();
		Assert.assertFalse("Expected element: Name at row 1", objectUnderTest.readElements("test123.csv", 1, "Name"));
		Assert.assertFalse("Not Expected element: Name at row 2", objectUnderTest.readElements("test.csv", 2, "Name"));
	}
}
