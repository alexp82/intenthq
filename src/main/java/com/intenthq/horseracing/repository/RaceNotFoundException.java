/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.repository;

/**
 * Thrown when a race cannot be found in the repository
 * @author alexp
 */
public class RaceNotFoundException extends Exception {

    public RaceNotFoundException(String message) {
        super(message);
    }
}
