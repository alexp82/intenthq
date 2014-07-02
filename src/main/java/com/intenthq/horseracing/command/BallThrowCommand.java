/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.command;

/**
 *
 * @author alexp
 */
public class BallThrowCommand {

    private String raceId;
    private Integer lane;
    private Integer noOfYards;

    public BallThrowCommand(String raceId, Integer lane, Integer noOfYards) {
        this.raceId = raceId;
        this.lane = lane;
        this.noOfYards = noOfYards;
    }

    public String getRaceId() {
        return raceId;
    }

    public Integer getLane() {
        return lane;
    }

    public Integer getNoOfYards() {
        return noOfYards;
    }

}
