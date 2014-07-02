/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.repository;

import com.intenthq.horseracing.domain.Race;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Race domain repository - in memory implementation
 Implemented as singleton. There's no need for singleton implementation if using Spring DI.
 * @author alexp
 */
public class RaceInMemoryRepository implements RaceRepository {

    private static RaceRepository instance = null;
    private Map<String, Race> races;

    public static RaceRepository getInstance() {
        if (instance == null) {
            synchronized (RaceInMemoryRepository.class) {
                if (instance == null) {
                    instance = new RaceInMemoryRepository();
                }
            }
        }
        return instance;
    }

    private RaceInMemoryRepository() {
    }

    @Override
    public String nextIdentity() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    @Override
    public void save(Race race) {
        if (races == null) {
            races = new LinkedHashMap<>();
        }
        races.put(race.raceId(), race);
    }

    @Override
    public Race getRace(String raceId) throws RaceNotFoundException {
        if (raceId == null) {
            throw new IllegalArgumentException("RaceId argument is mandatory!");
        }
        if (races == null) {
            throw new IllegalStateException("Races are not initialized!");
        }
        if (!races.containsKey(raceId)) {
            throw new RaceNotFoundException("Race " + raceId + " not found!");
        }
        return races.get(raceId);
    }

}
