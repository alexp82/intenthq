/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.service;

import com.intenthq.horseracing.command.BallThrowCommand;
import com.intenthq.horseracing.command.InitiateRaceCommand;
import com.intenthq.horseracing.command.OrganizeRaceCommand;
import com.intenthq.horseracing.domain.HoleType;
import com.intenthq.horseracing.domain.Horse;
import com.intenthq.horseracing.domain.Race;
import com.intenthq.horseracing.repository.RaceInMemoryRepository;
import com.intenthq.horseracing.repository.RaceRepository;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
    private static final String SAMPLE_OUTPUT = "Position, Lane, Horse name\n"
            + "1, 1, Star\n"
            + "2, 3, Cheyenne";

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

    @Test
    public void testOrganizeSuccessful() {
        System.out.println("organize");
        OrganizeRaceCommand command = new OrganizeRaceCommand(FURLONG, HOLE_TYPES, MAX_NO_OF_LANES);
        String raceId = raceService.organize(command);
        assertNotNull(raceId);
    }

    @Test
    public void testInitiateRaceForUnexistingRace() {
        System.out.println("initiateRace");
        Race race = new Race(RACEID, EnumSet.allOf(HoleType.class), MAX_NO_OF_LANES, FURLONG);
        raceRepository.save(race);
        InitiateRaceCommand command = new InitiateRaceCommand("OTHERRACEID", new String[]{"Sydney"});
        assertFalse(raceService.initiateRace(command));
    }

    @Test
    public void testInitiateRaceForFinishedRace() {
        System.out.println("initiateRace");
        Race race = new Race(RACEID, EnumSet.allOf(HoleType.class), MAX_NO_OF_LANES, FURLONG);
        raceRepository.save(race);
        race.endRace();
        InitiateRaceCommand command = new InitiateRaceCommand(RACEID, new String[]{"Sydney"});
        assertFalse(raceService.initiateRace(command));
    }

    @Test
    public void testUpdateRaceSuccess() {
        System.out.println("updateRace");
        Race race = new Race(RACEID, EnumSet.allOf(HoleType.class), MAX_NO_OF_LANES, FURLONG);
        raceRepository.save(race);
        Map<Integer, Horse> lanes = new LinkedHashMap<>();
        lanes.put(1, new Horse("Star"));
        race.initiateRace(lanes);
        BallThrowCommand command = new BallThrowCommand(RACEID, 1, 5);
        assertTrue(raceService.updateRace(command));
        assertEquals(Integer.valueOf(5), race.results().get(0).getYards());
        command = new BallThrowCommand(RACEID, 1, 10);
        assertTrue(raceService.updateRace(command));
        assertEquals(Integer.valueOf(15), race.results().get(0).getYards());
    }

    @Test
    public void testUpdateRaceForUninitializedRace() {
        System.out.println("updateRace");
        Race race = new Race(RACEID, EnumSet.allOf(HoleType.class), MAX_NO_OF_LANES, FURLONG);
        raceRepository.save(race);
        BallThrowCommand command = new BallThrowCommand(RACEID, 1, 5);
        assertFalse(raceService.updateRace(command));
    }

    @Test
    public void testUpdateRaceForUnexistingRace() {
        System.out.println("updateRace");
        Race race = new Race(RACEID, EnumSet.allOf(HoleType.class), MAX_NO_OF_LANES, FURLONG);
        raceRepository.save(race);
        BallThrowCommand command = new BallThrowCommand("OTHERRACEID", 1, 5);
        assertFalse(raceService.updateRace(command));
    }

    @Test
    public void testUpdateRaceForEndedRace() {
        System.out.println("updateRace");
        Race race = new Race(RACEID, EnumSet.allOf(HoleType.class), MAX_NO_OF_LANES, FURLONG);
        raceRepository.save(race);
        race.endRace();
        BallThrowCommand command = new BallThrowCommand(RACEID, 1, 5);
        assertFalse(raceService.updateRace(command));
    }

    @Test
    public void testDisplayResultsSuccess() {
        Race race = new Race(RACEID, EnumSet.allOf(HoleType.class), MAX_NO_OF_LANES, FURLONG);
        raceRepository.save(race);
        Map<Integer, Horse> lanes = new LinkedHashMap<>();
        lanes.put(1, new Horse("Star"));
        lanes.put(3, new Horse("Cheyenne"));
        race.initiateRace(lanes);
        race.updateRace(1, HoleType.TEN);
        race.updateRace(3, HoleType.FIVE);
        String result = raceService.displayResults(RACEID);
        assertEquals(SAMPLE_OUTPUT, result);
    }

    @Test
    public void testDisplayResultsForUninitializedRace() {
        Race race = new Race(RACEID, EnumSet.allOf(HoleType.class), MAX_NO_OF_LANES, FURLONG);
        raceRepository.save(race);
        String result = raceService.displayResults(RACEID);
        assertEquals("Lanes should be configured, race should be initialized!", result);
    }

    @Test
    public void testDisplayResultsForUnexistingRace() {
        Race race = new Race(RACEID, EnumSet.allOf(HoleType.class), MAX_NO_OF_LANES, FURLONG);
        raceRepository.save(race);
        String result = raceService.displayResults("OTHERRACEID");
        assertEquals("Race OTHERRACEID not found!", result);
    }

}
