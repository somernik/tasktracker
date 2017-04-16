package com.sarah.persistence;

import com.sarah.entity.Task;
import com.sarah.entity.TaskEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * CalculationsTest
 * tests calculations
 * Created by sarah on 4/9/2017.
 */
public class CalculationsTest {
    Map<String, Double> testDayMap = new HashMap<String, Double>();
    Map<String, Double> testTotalsForPercentages = new HashMap<String, Double>();
    Map<String, Double> testTotalsForSorting = new HashMap<String, Double>();
    Map<String, Double> testTotalsAfter = new HashMap<String, Double>();
    Map<String, Double> percentagePerType = new HashMap<String, Double>();
    Map<String, Double> testDayMapTwo = new HashMap<String, Double>();
    Task testTaskForSorting = new Task();
    TaskEntry testEntry = new TaskEntry();
    Double total = 0.0;

    List<TaskEntry> testEntries = new ArrayList<TaskEntry>();
    List<Double> testList = new ArrayList<Double>();

    @Before
    public void SetUp() {
        // most common day
        testDayMap.put("Monday", 40.5);
        testDayMap.put("Tuesday", 60.0);
        testDayMap.put("Wednesday", 160.2);
        testDayMap.put("Thursday", 356.0);
        testDayMap.put("Friday", 200.0);
        testDayMap.put("Saturday", 505.0);
        testDayMap.put("Sunday", 160.2);

        // percentages
        total = 9257.3;
        testTotalsForPercentages.put("type1", 756.0);
        testTotalsForPercentages.put("type2", 563.8);
        testTotalsForPercentages.put("type3", 68.5);
        testTotalsForPercentages.put("type4", 7869.0);

        // sorting
        testTotalsForSorting.put("type1", 30.0);
        testTotalsForSorting.put("type2", 80.0);

        testTotalsAfter.put("type1", 80.0);
        testTotalsAfter.put("type2", 80.0);

        testTaskForSorting.setTaskType("type1");
        testTaskForSorting.setTimeSpent(50);

        // add value to day map
        testDayMapTwo.put("Monday", 40.5);
        testDayMapTwo.put("Tuesday", 60.0);
        testDayMapTwo.put("Wednesday", 160.2);
        testDayMapTwo.put("Thursday", 356.0);
        testDayMapTwo.put("Friday", 150.0);
        testDayMapTwo.put("Saturday", 505.0);
        testDayMapTwo.put("Sunday", 160.2);

        testEntry.setTimeAdded(50);

        // Add up task entries
        TaskEntry one = new TaskEntry();
        TaskEntry two = new TaskEntry();
        TaskEntry three = new TaskEntry();

        one.setTimeAdded(50);
        two.setTimeAdded(45);
        three.setTimeAdded(30);

        testEntries.add(one);
        testEntries.add(two);
        testEntries.add(three);

        testList.add(50.0);
        testList.add(95.0);
        testList.add(125.0);

    }

    @Test
    public void testGetMostCommonDay() {

        assertEquals("Saturday", Calculations.getMostCommonDay(testDayMap));
        assertNotNull(testDayMap);
    }

    @Test
    public void testCalculatePercentages() {

        Map<String, Double> expectedMap = new HashMap<String, Double>();
        expectedMap.put("type1", 8.1665280373327);
        expectedMap.put("type2", 6.090328713555788);
        expectedMap.put("type3", 0.7399565748112301);
        expectedMap.put("type4", 85.0031866743003);

        Calculations.calculatePercentages(percentagePerType, testTotalsForPercentages, total);
        assertEquals(expectedMap, percentagePerType);
        assertNotNull(percentagePerType);
    }

    @Test
    public void testUpdateTotalPerSomeSortingFactor() {
        Calculations.updateTotalPerSomeSortingFactor(testTaskForSorting, testTotalsForSorting, "type");
        assertEquals(testTotalsForSorting, testTotalsAfter);
        assertNotNull(testTotalsForSorting);
    }

    @Test
    public void testAddValuesToDayMap() {
        testDayMapTwo = Calculations.addValuesToDayMap(testDayMapTwo, testEntry, "Friday");
        assertEquals(testDayMap, testDayMapTwo);
        assertNotNull(testDayMapTwo);
    }

    @Test
    public void testGetEntries() {

        List<Double> listToCheck = Calculations.getEntries(testEntries);
        assertEquals(testList, listToCheck);
        assertNotNull(listToCheck);

    }
/*
    dayOfWeekSetUp
    calculateDaysOfWeek
*/
}
