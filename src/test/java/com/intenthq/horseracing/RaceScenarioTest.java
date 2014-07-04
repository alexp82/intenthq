package com.intenthq.horseracing;

import com.intenthq.horseracing.command.BallThrowCommand;
import com.intenthq.horseracing.command.InitiateRaceCommand;
import com.intenthq.horseracing.command.OrganizeRaceCommand;
import com.intenthq.horseracing.repository.RaceInMemoryRepository;
import com.intenthq.horseracing.service.KentuckyDerbyRaceService;
import com.intenthq.horseracing.service.RaceService;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class RaceScenarioTest {

    private RaceService raceService;

    private static final String SAMPLE_INPUT = "Star, Dakota, Cheyenne, Misty, Spirit\n"
            + "1 60\n"
            + "3 5\n"
            + "1 60\n"
            + "4 5\n"
            + "4 10\n"
            + "2 5\n"
            + "5 10\n"
            + "1 60\n"
            + "3 20\n"
            + "7 10\n"
            + "1 40\n"
            + "2 60";
    private static final Integer[] HOLE_TYPES = new Integer[]{5, 10, 20, 40, 60};
    private static final String SAMPLE_OUTPUT = "Position, Lane, Horse name\n"
            + "1, 1, Star\n"
            + "2, 3, Cheyenne\n"
            + "3, 4, Misty\n"
            + "4, 5, Spirit\n"
            + "5, 2, Dakota";
    private static final Integer FURLONG = 220;
    private static final Integer MAX_NO_OF_LANES = 7;

    @Before
    public void setUp() throws Exception {
        raceService = KentuckyDerbyRaceService.getInstance(RaceInMemoryRepository.getInstance());
    }

    @Test
    public void testRaceScenario() throws Exception {
        String[] horses = SAMPLE_INPUT.split("\\n")[0].split(", ");
        String raceId = raceService.organize(new OrganizeRaceCommand(FURLONG, HOLE_TYPES, MAX_NO_OF_LANES));
        raceService.initiateRace(new InitiateRaceCommand(raceId, horses));
        String[] ballthrows = SAMPLE_INPUT.split("\\n");
        int i = 1;
        boolean updateOk = true;
        while (updateOk) {
            updateOk = raceService.updateRace(new BallThrowCommand(raceId, Integer.valueOf(ballthrows[i].split(" ")[0]), Integer.valueOf(ballthrows[i].split(" ")[1])));
            i++;
        }
        assertEquals(SAMPLE_OUTPUT, raceService.displayResults(raceId));
    }

}
