/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.command;

import com.intenthq.horseracing.common.AssertionConcern;

/**
 * Command representing a ball throw
 * @author alexp
 */
public class BallThrowCommand extends AssertionConcern {

    // the race identifier
    private String raceId;
    // the lane number
    private Integer laneNumber;
    // the number of yards the horse will advance
    private Integer noOfYards;

    public BallThrowCommand(String raceId, Integer lane, Integer noOfYards) {
        assertArgumentNotEmpty(raceId, BallThrowCommand.class.getSimpleName() + ".raceId is mandatory!");
        assertPositiveInteger(lane, BallThrowCommand.class.getSimpleName() + ".lane is mandatory and greater than 0!");
        assertPositiveInteger(noOfYards, BallThrowCommand.class.getSimpleName() + ".noOfYards is mandatory and greater than 0!");
        this.raceId = raceId;
        this.laneNumber = lane;
        this.noOfYards = noOfYards;
    }

    public String getRaceId() {
        return raceId;
    }

    public Integer getLane() {
        return laneNumber;
    }

    public Integer getNoOfYards() {
        return noOfYards;
    }

}
