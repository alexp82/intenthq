/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.domain;

/**
 *
 * @author alexp
 */
public class LaneStatus {

    private Integer laneNumber;
    private Horse horse;
    private Integer yards;

    public LaneStatus(Integer laneNumber, Horse horse) {
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
        return "LaneStatus{" + "laneNumber=" + laneNumber + ", horse=" + horse + ", yards=" + yards + '}';
    }

}
