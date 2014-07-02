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
public class OrganizeRaceCommand {

    private Integer length;
    private Integer[] holeTypes;
    private String[] horses;

    public OrganizeRaceCommand(Integer length, Integer[] holeTypes, String[] horses) {
        // list of hole types
        // list of horses
        this.length = length;
        this.holeTypes = holeTypes;
        this.horses = horses;
    }

    public Integer getLength() {
        return length;
    }

    public String[] getHorses() {
        return horses;
    }

    public Integer[] getHoleTypes() {
        return holeTypes;
    }

}
