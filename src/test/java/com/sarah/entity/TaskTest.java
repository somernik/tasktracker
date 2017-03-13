package com.sarah.entity;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * Created by Sarah Omernik on 2/22/2017.
 */
public class TaskTest {

    Task task;
    Task task1;
    Task task2;
    Task newTask;

    @Before
    public void SetUp() {

        task = new Task("Test", "TestCategory", "TestTask", "TestDescription",
                createLocalDateFromString("20-02-2017"), 1, 1, 30,
                90, createLocalDateFromString("12-03-2017"), false );

        task1 = new Task("Test2", "TestCategory", "TestTask", "TestDescription1",
                createLocalDateFromString("20-02-2017"), 2, 1, 47,
                90, createLocalDateFromString("12-03-2017"), true );

        task2 = new Task("Test3", "TestCategory", "TestTask", "TestDescription2",
                createLocalDateFromString("20-02-2017"), 3, 1, 50,
                90, createLocalDateFromString("12-03-2017"),  true );

        newTask = new Task("Test4", "TestCategory", "TestTask", "TestDescription2",
                createLocalDateFromString("20-02-2017"), 3, 1, 0,
                0, createLocalDateFromString("12-03-2017"), false );
    }

    private LocalDate createLocalDateFromString(String input) {
        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(input, DATE_FORMAT);
    }

    @Test
    public void testEstimateTimeLeft() throws Exception {

        assertEquals(60, task.estimateTimeLeft(), 0.001);
        assertNotNull(task);
    }
/*
    @Test
    public void testEstimateTotalTime() throws Exception {
        assertEquals(, newTask.estimateTotalTime(), 0.001);
        assertNotNull(newTask);
    }

    public void testCalculateTotalTime() throws Exception {

    }
*/
}