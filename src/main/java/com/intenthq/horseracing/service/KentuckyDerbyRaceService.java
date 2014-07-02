/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.service;

import com.intenthq.horseracing.command.BallThrowCommand;
import com.intenthq.horseracing.command.OrganizeRaceCommand;
import com.intenthq.horseracing.domain.HoleType;
import com.intenthq.horseracing.domain.Horse;
import com.intenthq.horseracing.domain.LaneStatus;
import com.intenthq.horseracing.domain.Race;
import com.intenthq.horseracing.repository.RaceNotFoundException;
import com.intenthq.horseracing.repository.RaceRepository;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author alexp
 */
public class KentuckyDerbyRaceService implements RaceService {

    private static RaceService instance = null;
    private RaceRepository raceRepository;

    public static RaceService getInstance(RaceRepository raceRepository) {
        if (instance == null) {
            synchronized (KentuckyDerbyRaceService.class) {
                if (instance == null) {
                    instance = new KentuckyDerbyRaceService(raceRepository);
                }
            }
        }
        return instance;
    }

    private KentuckyDerbyRaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public String organize(OrganizeRaceCommand command) {
        String raceId = this.raceRepository().nextIdentity();
        Race race = new Race(raceId, getHoleTypes(command.getHoleTypes()), getParticipants(command.getHorses()), command.getLength());
        this.raceRepository().save(race);
        return raceId;
    }

    private Set<HoleType> getHoleTypes(Integer[] aHoleTypes) {
        Set<HoleType> holeTypes = new LinkedHashSet<>();
        for (Integer iHoleType : aHoleTypes) {
            HoleType holeType = HoleType.fromInteger(iHoleType);
            if (holeType != null) {
                holeTypes.add(holeType);
            }
        }
        return holeTypes;
    }

    private Map<Integer, Horse> getParticipants(String[] horses) {
        // get Horses from HorseRepository by horseId
        Map<Integer, Horse> participantsOnLane = new LinkedHashMap<>();
        int lane = 1;
        for (String horseName : horses) {
            Horse horse = new Horse(horseName);
            participantsOnLane.put(lane++, horse);
        }
        return participantsOnLane;
    }

    @Override
    public boolean updateRace(BallThrowCommand command) {
        try {
            Race race = this.raceRepository().getRace(command.getRaceId());
            if (!race.isRaceOver()) {
                return race.updateRace(command.getLane(), HoleType.fromInteger(command.getNoOfYards()));
            } else {
                return false;
            }
        } catch (RaceNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public String displayResults(String raceId) {
        try {
            Race race = this.raceRepository().getRace(raceId);
            if (!race.isRaceOver()) {
                return "Race is not over yet!";
            } else {
                StringBuilder sb = new StringBuilder("Position, Lane, Horse name\n");
                int i = 0;
                for (LaneStatus laneStatus : race.results()) {
                    sb.append(++i).append(", ");
                    sb.append(laneStatus.getLaneNumber()).append(", ");
                    sb.append(laneStatus.getHorse().getName());
                    if (i < race.results().size()) {
                        sb.append("\n");
                    }
                }
                return sb.toString();
            }
        } catch (RaceNotFoundException ex) {
            return ex.getMessage();
        }
    }

    private RaceRepository raceRepository() {
        return raceRepository;
    }

}
