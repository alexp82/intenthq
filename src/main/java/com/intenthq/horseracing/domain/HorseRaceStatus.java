/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.domain;

import com.intenthq.horseracing.common.AssertionConcern;

/**
 * Domain modeling the status of horse on a specific lane during the race
 * @author alexp
 */
public class HorseRaceStatus extends AssertionConcern {

    // The lane
    private Integer laneNumber;
    // The Horse
    private Horse horse;
    // Current number of yards
    private Integer yards;

    public HorseRaceStatus(Integer laneNumber, Horse horse) {
        assertPositiveInteger(laneNumber, HorseRaceStatus.class.getSimpleName() + ".laneNumber is mandatory!");
        this.laneNumber = laneNumber;
        this.horse = horse;
        this.yards = 0;
    }

    public void updateYards(Integer yards) {
        this.yards += yards;
    }

    public Integer getLaneNumber() {
        return laneNumber;
    }

    public Horse getHorse() {
        return horse;
    }

    public Integer getYards() {
        return yards;
    }

    @Override
    public String toString() {
        return "HorseRaceStatus{" + "laneNumber=" + laneNumber + ", horse=" + horse + ", yards=" + yards + '}';
    }

}
