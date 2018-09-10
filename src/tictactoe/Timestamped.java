/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author William Taylor <musicmrman99@gmail.com>
 * @param <T> the type of object to timestamp
 */
public class Timestamped<T> implements Comparable<Timestamped> {
    private static final DateTimeFormatter DATE_TIME_FORMAT =
        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private LocalDateTime timestamp;
    private T obj;
    
    /**
     * Initialise the score.
     */
    public Timestamped() {
        setTimestamp();
        set(null);
    }
    /**
     * Initialise the score.
     * @param obj The object to timestamp.
     */
    public Timestamped(T obj) {
        setTimestamp();
        set(obj);
    }
    /**
     * Initialise the score.
     * @param timestamp The timestamp for this score.
     * @param obj The value to timestamp.
     */
    public Timestamped(LocalDateTime timestamp, T obj) {
        setTimestamp(timestamp);
        set(obj);
    }
    /**
     * Initialise the score.
     * @param timestamp The formatted timestamp for this score.
     * @param obj The value to timestamp.
     */
    public Timestamped(String timestamp, T obj) {
        setTimestampFromString(timestamp);
        set(obj);
    }
    
    /* Setters */
    /**
     * Set the score to val.
     * @param obj The object to timestamp.
     */
    public final void set(T obj) {
        this.obj = obj;
    }
    
    /**
     * Set the timestamp to the current time.
     */
    public final void setTimestamp() {
        this.timestamp = LocalDateTime.now();
    }
    /**
     * Set the timestamp to the given time.
     * @param timestamp The timestamp to set.
     */
    public final void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     * Set the timestamp to the given time.
     * @param timestamp The formatted timestamp to set.
     */
    public final void setTimestampFromString(String timestamp) {
        this.timestamp = LocalDateTime.parse(timestamp, DATE_TIME_FORMAT);
    }
    
    /* Getters */
    /**
     * Return the score's value.
     * @return The current score.
     */
    public final T get() {
        return obj;
    }
    
    /**
     * Return the timestamp associated with this score.
     * @return The current timestamp.
     */
    public final LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    /**
     * Return the timestamp associated with this score as a string in the format
     * yyyy/mm/dd hh:mm:ss
     * @return The current timestamp as a string.
     */
    public final String getTimestampAsString() {
        return timestamp.format(DATE_TIME_FORMAT);
    }

    /* Comparisons */
    /**
     * Compare this score to other, another score.
     * @param other The score to compare to.
     * @return A number <0 if this score is less than other, 0 if the scores are
     * equal, and a number >0 if this score is greater than other.
     */
    @Override
    public int compareTo(Timestamped other) {
        // If either timestamp is not specified, then they are 'equal'
        if (this.timestamp != null && other.getTimestamp() != null) {
            if (this.timestamp.isAfter(other.getTimestamp())) {
                return 1;
            } else if (this.timestamp.isBefore(other.getTimestamp())) {
                return -1;
            }
        }

        return 0;
    }
}
