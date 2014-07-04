/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.domain;

import com.intenthq.horseracing.common.AssertionConcern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Domain modeling the Race
 * @author alexp
 */
public class Race extends AssertionConcern {

    // Race identifier
    private String raceId;
    // Configuration - types of holes used
    private Set<HoleType> holeTypes;
    // Configuration - maximum number of lanes
    private Integer maxNoOfLanes;
    // Current status of horses during the race
    private Map<Integer, HorseRaceStatus> lanesStatus = new LinkedHashMap<>();
    // Configuration - the race length in yards
    private Integer length;
    // Race status
    private boolean raceOver;

    public Race(String raceId, Set<HoleType> holeTypes, Integer maxNoOfLanes, Integer length) {
        assertArgumentNotEmpty(raceId, Race.class.getSimpleName() + ".raceId is mandatory!");
        assertCollectionNotEmpty(holeTypes, Race.class.getSimpleName() + ".holeTypes is mandatory!");
        assertPositiveInteger(maxNoOfLanes, Race.class.getSimpleName() + ".maxNoOfLanes is mandatory!");
        assertPositiveInteger(length, Race.class.getSimpleName() + ".length is mandatory!");
        this.raceId = raceId;
        this.holeTypes = holeTypes;
        this.maxNoOfLanes = maxNoOfLanes;
        this.length = length;
    }

    public boolean initiateRace(Map<Integer, Horse> laneHorses) {
        assertMapNotEmpty(laneHorses, Race.class.getSimpleName() + " - setting laneHorses is mandatory");
        if (laneHorses.size() > maxNoOfLanes) {
            throw new IllegalArgumentException("Too many horses. Maximum allowed number of horses is " + maxNoOfLanes);
        }
        if (isRaceOver()) {
            System.out.println("Race cannot be initialized! Race is already over!");
            return false;
        } else {
            for (Integer lane : laneHorses.keySet()) {
                lanesStatus.put(lane, new HorseRaceStatus(lane, laneHorses.get(lane)));
            }
            return true;
        }
    }

    public void updateRace(Integer lane, HoleType hole) {
        assertPositiveInteger(lane, Race.class.getSimpleName() + " - updateRace: lane is mandatory!");
        if (hole == null) {
            throw new IllegalArgumentException("Setting the hole is mandatory!");
        }
        if (!holeTypes.contains(hole)) {
            throw new IllegalArgumentException("The Hole " + hole + " is not specified for this race!");
        }
        if (lanesStatus == null || lanesStatus.isEmpty()) {
            throw new IllegalStateException("The race is not initialized!");
        }
        if (lanesStatus.get(lane) == null) {
            System.out.println("WARN: Could not find a horse on lane " + lane + "!");
        } else if (!isRaceOver()) {
            lanesStatus.get(lane).updateYards(hole.getYards());
            if (lanesStatus.get(lane).getYards() >= length) {
                raceOver = true;
            }
        }
    }

    public List<HorseRaceStatus> results() {
        if (lanesStatus == null || lanesStatus.isEmpty()) {
            throw new IllegalStateException("Lanes should be configured, race should be initialized!");
        }
        List<HorseRaceStatus> lanes = new ArrayList<>();
        lanes.addAll(lanesStatus.values());
        Collections.sort(lanes, Collections.reverseOrder(new Comparator<HorseRaceStatus>() {

            @Override
            public int compare(HorseRaceStatus o1, HorseRaceStatus o2) {
                if (o1 != null && o2 != null && o1.getYards() != null && o2.getYards() != null) {
                    return o1.getYards().compareTo(o2.getYards());
                }
                return 0;
            }

        }));
        return lanes;
    }

    public boolean isRaceOver() {
        return raceOver;
    }

    public String raceId() {
        return raceId;
    }

    public void endRace() {
        raceOver = true;
    }

}
