/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.service;

import com.intenthq.horseracing.command.BallThrowCommand;
import com.intenthq.horseracing.command.OrganizeRaceCommand;

/**
 *
 * @author alexp
 */
public interface RaceService {

    String organize(OrganizeRaceCommand command);

    boolean updateRace(BallThrowCommand command);

    String displayResults(String raceId);
}
