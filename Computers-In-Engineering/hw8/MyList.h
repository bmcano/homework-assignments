/************************************************************
 * Programmer: Brandon Cano
 * Date: 12/6/21
 * Description:
 *   MyList header file that initializes every value and
 *   method for the MyList class file
 ***********************************************************/

#ifndef HW8_MYLIST_H
#define HW8_MYLIST_H

#include "Node.h"

class MyList {
public:
    // explicit constructor
    explicit MyList(Node* currentPtr = nullptr);
    // destructor
    ~MyList();

    // prints the list in ascending order
    void printAscending();
    // prints the list in descending order
    void printDescending();

    // inserts an item into the list
    void insert(int value);
    // removes an item from the list
    void remove(int value);

    // clears all items from the list
    void clear();

private:
    Node* m_currentPtr;
};

#endif //HW8_MYLIST_H