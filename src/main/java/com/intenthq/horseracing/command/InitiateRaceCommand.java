/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.command;

import com.intenthq.horseracing.common.AssertionConcern;

/**
 * Initiate the race
 * @author alexp
 */
public class InitiateRaceCommand extends AssertionConcern {

    // Race identifier
    private String raceId;
    // Horse names
    private String[] horses;

    public InitiateRaceCommand(String raceId, String[] horses) {
        assertArgumentNotEmpty(raceId, InitiateRaceCommand.class.getSimpleName() + ".raceId is mandatory!");
        assertArrayNotEmpty(horses, InitiateRaceCommand.class.getSimpleName() + ".horses cannot be empty!");
        this.raceId = raceId;
        this.horses = horses;
    }

    public String getRaceId() {
        return raceId;
    }

    public String[] getHorses() {
        return horses;
    }
}
