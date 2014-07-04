/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.service;

import com.intenthq.horseracing.command.BallThrowCommand;
import com.intenthq.horseracing.command.InitiateRaceCommand;
import com.intenthq.horseracing.command.OrganizeRaceCommand;

/**
 * Service facade for organizing and running a race
 * @author alexp
 */
public interface RaceService {

    /**
     Organize the race - set the length of the race,
     @param command
     @return the raceId identifier for the new race
     */
    String organize(OrganizeRaceCommand command);

    /**
     Initiate the race - assign the horses to the race
     @param command
     @return true if the initialization succeeded
     */
    boolean initiateRace(InitiateRaceCommand command);

    /**
     Update the race with a new ball throw information
     @param command
     @return true if the event was successfully registered, false if the race is already over or the race could not be found
     */
    boolean updateRace(BallThrowCommand command);

    /**
     Returns true if the race identified by raceId is over
     @param raceId
     @return true if the race is over
     */
    boolean isRaceOver(String raceId);

    /**
     Display the results for a race
     @param raceId
     @return formatted results for a race
     */
    String displayResults(String raceId);
}
