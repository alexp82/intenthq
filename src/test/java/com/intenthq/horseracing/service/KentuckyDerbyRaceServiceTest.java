/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.service;

import com.intenthq.horseracing.command.BallThrowCommand;
import com.intenthq.horseracing.command.InitiateRaceCommand;
import com.intenthq.horseracing.command.OrganizeRaceCommand;
import com.intenthq.horseracing.repository.RaceInMemoryRepository;
import com.intenthq.horseracing.repository.RaceRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author alexp
 */
public class KentuckyDerbyRaceServiceTest {

    private static final Integer FURLONG = 220;
    private static final Integer MAX_NO_OF_LANES = 7;
    private static final Integer[] HOLE_TYPES = new Integer[]{5, 10, 20, 40, 60};
    private static final String RACEID = "RACEID";

    private RaceRepository raceRepository;
    private RaceService raceService;

    public KentuckyDerbyRaceServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        raceRepository = RaceInMemoryRepository.getInstance();
        raceService = KentuckyDerbyRaceService.getInstance(raceRepository);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of organize method, of class KentuckyDerbyRaceService.
     */
    @Test
    public void testOrganizeSuccessful() {
        System.out.println("organize");
        OrganizeRaceCommand command = new OrganizeRaceCommand(FURLONG, HOLE_TYPES, MAX_NO_OF_LANES);
        String raceId = raceService.organize(command);
        Assert.assertNotNull(raceId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOrganize() {
        System.out.println("organize");
        OrganizeRaceCommand command = new OrganizeRaceCommand(null, null, null);
        String raceId = raceService.organize(command);
        Assert.assertNotNull(raceId);
    }

    /**
     * Test of initiateRace method, of class KentuckyDerbyRaceService.
     */
    @Test()
    public void testInitiateRace() {
        System.out.println("initiateRace");
        InitiateRaceCommand command = new InitiateRaceCommand(RACEID, new String[]{"Sydney"});
        KentuckyDerbyRaceService instance = null;
        boolean expResult = false;
        boolean result = instance.initiateRace(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateRace method, of class KentuckyDerbyRaceService.
     */
    @Test
    public void testUpdateRace() {
        System.out.println("updateRace");
        BallThrowCommand command = null;
        KentuckyDerbyRaceService instance = null;
        boolean expResult = false;
        boolean result = instance.updateRace(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of displayResults method, of class KentuckyDerbyRaceService.
     */
    @Test
    public void testDisplayResults() {
        System.out.println("displayResults");
        String raceId = "";
        KentuckyDerbyRaceService instance = null;
        String expResult = "";
        String result = instance.displayResults(raceId);
        assertEquals(expResult, result);
    }

}
