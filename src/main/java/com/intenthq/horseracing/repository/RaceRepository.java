/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.repository;

import com.intenthq.horseracing.domain.Race;

/**
 * Race repository interface - supporting basic CRUD implementation
 * @author alexp
 */
public interface RaceRepository {

    /**

     @return
     */
    String nextIdentity();

    /**

     @param race
     */
    void save(Race race);

    /**

     @param raceId
     @return
     @throws RaceNotFoundException
     */
    Race getRace(String raceId) throws RaceNotFoundException;

}
