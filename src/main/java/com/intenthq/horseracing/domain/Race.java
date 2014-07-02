/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author alexp
 */
public class Race {

    private String raceId;
    private Set<HoleType> holeTypes;
    private Map<Integer, LaneStatus> lanesStatus = new LinkedHashMap<>();
    private Integer length;
    private boolean raceOver;

    public Race(String raceId, Set<HoleType> holeTypes, Map<Integer, Horse> laneHorses, Integer lenght) {
        this.raceId = raceId;
        this.holeTypes = holeTypes;
        this.length = lenght;
        for (Integer lane : laneHorses.keySet()) {
            lanesStatus.put(lane, new LaneStatus(lane, laneHorses.get(lane)));
        }
    }

    public boolean updateRace(Integer lane, HoleType hole) {
        if (lane == null) {
            throw new IllegalArgumentException("Setting a Horse is mandatory!");
        }
        if (hole == null) {
            throw new IllegalArgumentException("Setting the hole is mandatory!");
        }
        if (!holeTypes.contains(hole)) {
            throw new IllegalArgumentException("The Hole " + hole + " is not specified for this race!");
        }
        if (lanesStatus.get(lane) == null) {
            System.out.println("Could not find a horse on lane " + lane + "!");
            return true;
        }
        if (isRaceOver()) {
            return false;
        } else {
            lanesStatus.get(lane).updateYards(hole.getYards());
            if (lanesStatus.get(lane).getYards() >= length) {
                raceOver = true;
                return false;
            }
        }
        return true;
    }

    public List<LaneStatus> results() {
        if (lanesStatus == null || lanesStatus.isEmpty()) {
            throw new IllegalStateException("Lanes should be configured");
        }
        List<LaneStatus> lanes = new ArrayList<>();
        if (isRaceOver()) {
            lanes.addAll(lanesStatus.values());
            Collections.sort(lanes, Collections.reverseOrder(new Comparator<LaneStatus>() {

                @Override
                public int compare(LaneStatus o1, LaneStatus o2) {
                    if (o1 != null && o2 != null && o1.getYards() != null && o2.getYards() != null) {
                        return o1.getYards().compareTo(o2.getYards());
                    }
                    return 0;
                }

            }));
        }
        return lanes;
    }

    public boolean isRaceOver() {
        return raceOver;
    }

    public String raceId() {
        return raceId;
    }

}
