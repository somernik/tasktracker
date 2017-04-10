package com.sarah.persistence;

import com.sarah.entity.Task;
import com.sarah.entity.TaskEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by sarah on 4/9/2017.
 */
public class CalculationsTest {
    Map<String, Double> testDayMap = new HashMap<String, Double>();
    Map<String, Double> testTotalsForPercentages = new HashMap<String, Double>();
    Map<String, Double> testTotalsForSorting = new HashMap<String, Double>();
    Map<String, Double> testTotalsAfter = new HashMap<String, Double>();
    Map<String, Double> percentagePerType = new HashMap<String, Double>();
    Task testTaskForSorting = new Task();
    Double total = 0.0;

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

/*
    startCalculations
    dayOfWeekSetUp
    calculateDaysOfWeek

    addValuesToDayMap
    updateTotalPerSomeSortingFactor

     calculatePercentages

    getMostCommonDay
*/
}
