/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.command;

import com.intenthq.horseracing.common.AssertionConcern;

/**
 * Command for organizing a race
 * @author alexp
 */
public class OrganizeRaceCommand extends AssertionConcern {

    // Number of yards for the race
    private Integer length;
    // Types of holes configuration for the race
    private Integer[] holeTypes;
    // Maximum number of lanes
    private Integer maxNoOfLanes;

    public OrganizeRaceCommand(Integer length, Integer[] holeTypes, Integer maxNoOfLanes) {
        assertPositiveInteger(length, OrganizeRaceCommand.class.getSimpleName() + ".length is mandatory and greater than 0!");
        assertArrayNotEmpty(holeTypes, OrganizeRaceCommand.class.getSimpleName() + ".holeTypes cannot be empty!");
        assertPositiveInteger(maxNoOfLanes, OrganizeRaceCommand.class.getSimpleName() + ".maxNoOfLanes is mandatory and greater than 0!");

        this.length = length;
        this.holeTypes = holeTypes;
        this.maxNoOfLanes = maxNoOfLanes;
    }

    public Integer getLength() {
        return length;
    }

    public Integer[] getHoleTypes() {
        return holeTypes;
    }

    public Integer getMaxNoOfLanes() {
        return maxNoOfLanes;
    }

}
