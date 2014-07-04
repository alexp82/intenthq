/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.domain;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author alexp
 */
public class RaceTest {

    private static final Integer FURLONG = 220;
    private static final Integer MAX_NO_OF_LANES = 7;
    private static final Set<HoleType> HOLE_TYPES = EnumSet.allOf(HoleType.class);
    private static final String RACE_ID = "RACEID";

    public RaceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of initiateRace method, of class Race.
     */
    @Test
    public void testInitiateRace() {
        System.out.println("initiateRace");
        Map<Integer, Horse> laneHorses = new LinkedHashMap<>();
        laneHorses.put(1, new Horse("Sydney"));
        laneHorses.put(3, new Horse("Melbourne"));
        Race race = new Race(RACE_ID, HOLE_TYPES, MAX_NO_OF_LANES, FURLONG);
        Assert.assertTrue(race.initiateRace(laneHorses));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitiateRaceIllegalArgument() {
        System.out.println("initiateRace");
        Map<Integer, Horse> laneHorses = new LinkedHashMap<>();
        Race race = new Race(RACE_ID, HOLE_TYPES, MAX_NO_OF_LANES, FURLONG);
        Assert.assertFalse(race.initiateRace(laneHorses));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitiateRaceMoreHorsesThanLanes() {
        System.out.println("initiateRace");
        Map<Integer, Horse> laneHorses = new LinkedHashMap<>();
        laneHorses.put(1, new Horse("Sydney"));
        laneHorses.put(3, new Horse("Melbourne"));
        Race race = new Race(RACE_ID, HOLE_TYPES, 1, FURLONG);
        race.initiateRace(laneHorses);
    }

    /**
     * Test of updateRace method, of class Race.
     */
    @Test
    public void testUpdateRaceNotOver() {
        System.out.println("updateRace");
        Race race = new Race(RACE_ID, HOLE_TYPES, MAX_NO_OF_LANES, FURLONG);
        Map<Integer, Horse> horses = new LinkedHashMap<>();
        horses.put(1, new Horse("Sydney"));
        Assert.assertTrue(race.initiateRace(horses));
        race.updateRace(1, HoleType.FIVE);
        Assert.assertFalse(race.isRaceOver());
        Assert.assertTrue(race.results().isEmpty());
    }

    /**
     * Test of updateRace method, of class Race.
     */
    @Test(expected = IllegalStateException.class)
    public void testUpdateRaceLanesNotConfigured() {
        System.out.println("updateRace");
        Race race = new Race(RACE_ID, HOLE_TYPES, MAX_NO_OF_LANES, FURLONG);
        race.updateRace(1, HoleType.FIVE);
    }

    /**
     * Test of results method, of class Race.
     */
    @Test
    public void testResultsRaceOver() {
        System.out.println("results");
        Race race = new Race(RACE_ID, HOLE_TYPES, 1, 5);
        Map<Integer, Horse> horses = new LinkedHashMap<>();
        horses.put(1, new Horse("Sydney"));
        Assert.assertTrue(race.initiateRace(horses));
        race.updateRace(1, HoleType.FIVE);
        Assert.assertTrue(race.isRaceOver());
        Assert.assertEquals(1, race.results().size());
    }

}
