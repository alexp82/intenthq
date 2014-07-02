/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.domain;

/**
 * The hole types
 * @author alexp
 */
public enum HoleType {

    FIVE(5), TEN(10), TWENTY(20), FORTY(40), SIXTY(60);

    private Integer yards;

    private HoleType(Integer yards) {
        this.yards = yards;
    }

    public static HoleType fromInteger(Integer yards) {
        for (HoleType holeType : HoleType.values()) {
            if (holeType.yards == yards) {
                return holeType;
            }
        }
        return null;
    }

    public Integer getYards() {
        return yards;
    }

}
