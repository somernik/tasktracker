package com.sarah.controllers;

import com.sarah.entity.TaskEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.*;

import static org.junit.Assert.*;

/**
 * TaskDetailsTest
 * tests task details
 * Created by sarah on 4/9/2017.
 */
public class TaskDetailsTest {

    List<TaskEntry> testEntries = new ArrayList<TaskEntry>();
    List<Double> testList = new ArrayList<Double>();

    @Before
    public void SetUp() {
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
    public void testGetEntries() {
        /*
        TaskDetails newDetail = new TaskDetails();
        List<Double> listToCheck = newDetail.getEntries(testEntries);
        assertEquals(testList, listToCheck);
        assertNotNull(listToCheck);
        */
    }

}
