package io.wquach.service;

import java.util.List;
import java.util.function.Consumer;


/**
 * Created by wquach on 6/4/17.
 */
public interface CrudService<T> {
    /**
     * Add an object to the system
     * @param t the object to add
     * @return the id of the object in the system
     */
    int add(T t);

    /**
     * Update an object in the system, based on the ID in the provided  object
     * @param t the object with updated field values.
     */
    void update(T t);

    /**
     * Get all object of type T in the system, and processes them using the provided processor
     * @param processor used to process the results of the query
     */
    void getAll(Consumer processor);

    /**
     * Get all object of type T in the system, and processes them using the provided processor
     * @param processor used to process the results of the query
     * @param page the page of result to return
     */
    void getAll(Consumer processor, Integer page);

    /**
     * Get a single object from the system
     * @param id the ID of the object
     * @return T object with ID of id
     */
    T getOne(int id);

    /**
     * Delete a single object from the system
     * @param id the ID of the object to delete
     */
    void deleteSingle(int id);
}
