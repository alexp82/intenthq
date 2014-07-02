/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intenthq.horseracing.common;

import java.util.Collection;
import java.util.Map;

/**
 * Implementing basic assertions for commands and domains
 * @author alexp
 */
public class AssertionConcern {

    protected void assertArgumentNotEmpty(String aString, String aMessage) {
        if (aString == null || aString.trim().isEmpty()) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    protected void assertPositiveInteger(Integer aNumber, String aMessage) {
        if (aNumber == null || aNumber <= 0) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    protected void assertCollectionNotEmpty(Collection collection, String aMessage) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    protected void assertMapNotEmpty(Map map, String aMessage) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    protected void assertArrayNotEmpty(Object[] array, String aMessage) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(aMessage);
        }
    }
}
