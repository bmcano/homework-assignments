public interface List<T> {
    /**
     * Adds an element to the end of this List
     * @param e the element to add
     */
    void add(T e);

    /**
     * Returns the element in this List at index i
     * @param i the index of the element to get
     * @return the element at index i
     */
    T get(int i);

    /**
     * Removes the element in this List at index i
     * @param i the index of the element to remove
     * @return the element at index i
     */
     T remove(int i);

    /**
     * Returns the number of elements in this List
     * @return the number of elements in this List
     */
    int size();
}
