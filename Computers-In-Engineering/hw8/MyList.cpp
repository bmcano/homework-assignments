/************************************************************
 * Programmer: Brandon Cano
 * Date: 12/6/21
 * Description:
 *   MyList class file that will utilize the Node class to
 *   modify elements correctly into the linked list
 ***********************************************************/

#include <iostream>
#include "MyList.h"

// constructor
MyList::MyList(Node* currentPtr) {
    m_currentPtr = currentPtr;
}

// destructor
MyList::~MyList() {
    MyList::clear();
}

// prints the list in ascending order
void MyList::printAscending() {
    // checks if list is empty
    if (m_currentPtr == nullptr) {
        std::cout << "The List is Emtpy (Ascending)" << std::endl;
        return;
    }

    // create a new pointer
    Node* startPtr = m_currentPtr;

    // makes sure the startPtr is the first node
    while (startPtr->getPreviousPtr() != nullptr) {
        startPtr = startPtr->getPreviousPtr();
    }

    // prints out the linked list
    while (startPtr != nullptr) {
        std::cout << startPtr->getData() << " ";
        startPtr = startPtr->getNextPtr();
    }
    std::cout << std::endl;
}

// prints the list in descending order
void MyList::printDescending() {
    // check to see if the list is empty
    if (m_currentPtr == nullptr) {
       std::cout << "The List is Empty (Descending)" << std::endl;
       return;
    }

    // create a new pointer
    Node* startPtr = m_currentPtr;

    // makes sure the startPtr is the last node
    while (startPtr->getNextPtr() != nullptr) {
        startPtr = startPtr->getNextPtr();
    }

    // prints out the linked list
    while (startPtr != nullptr) {
        std::cout << startPtr->getData() << " ";
        startPtr = startPtr->getPreviousPtr();
    }
    std::cout << std::endl;
}

// inserts an item into the list
void MyList::insert(int value) {
    // make a new node
    Node* newNode = new Node;
    newNode->setData(value);

    // initialize the previous and current pointers
    Node* currentPtr = m_currentPtr;
    Node* previousPtr = nullptr;

    // empty list
    if (currentPtr == nullptr) {
        currentPtr = newNode;
        m_currentPtr = currentPtr;
        return;
    }

    // move current and previous pointers to correct location
    while (currentPtr != nullptr && value > currentPtr->getData()) {
        previousPtr = currentPtr;
        currentPtr = currentPtr->getNextPtr();
    }

    // inset at beginning
    if (previousPtr == nullptr) {
        newNode->setNextPtr(currentPtr);
        currentPtr->setPreviousPtr(newNode);
        m_currentPtr = newNode;
        return;
    }

    // insert at the end
    if (currentPtr == nullptr) {
        previousPtr->setNextPtr(newNode);
        newNode->setPreviousPtr(previousPtr);
        return;
    }

    // insert at the middle
    newNode->setNextPtr(currentPtr);
    currentPtr->setPreviousPtr(newNode);
    newNode->setPreviousPtr(previousPtr);
    previousPtr->setNextPtr(newNode);
    return;
}

// removes an int from the list
void MyList::remove(int value) {
    // check for empty list
    if (m_currentPtr == nullptr) {
        std::cout << "List is Empty" << std::endl;
        return;
    }

    // initialize the current and previous pointer
    Node* currentPtr = m_currentPtr;
    Node* previousPtr = nullptr;

    // set the previous and current pointer in the correct location
    while (currentPtr != nullptr && currentPtr->getData() != value) {
        previousPtr = currentPtr;
        currentPtr = currentPtr->getNextPtr();
    }

    // element not found
    if (currentPtr == nullptr) {
        std::cout << "Element Not Found" << std::endl;
        return;
    }

    // beginning of list
    if (previousPtr == nullptr) {
        m_currentPtr = currentPtr->getNextPtr();
        m_currentPtr->setPreviousPtr(nullptr);
        delete currentPtr;
        return;
    }

    // end of list
    if (currentPtr->getNextPtr() == nullptr) {
        previousPtr->setNextPtr(nullptr);
        delete currentPtr;
        return;
    }

    // middle of list
    previousPtr->setNextPtr(currentPtr->getNextPtr());
    (currentPtr->getNextPtr())->setPreviousPtr(previousPtr);
    delete currentPtr;
    return;
}

// clear all items
void MyList::clear() {
    // check for empty list
    if (m_currentPtr == nullptr) {
        return;
    }

    // create a temporary node
    Node* temp = new Node;

    // makes sure the startPtr is the first node
    while (m_currentPtr->getPreviousPtr() != nullptr) {
        m_currentPtr = m_currentPtr->getPreviousPtr();
    }

    // iterates through each item to delete it/free up memory
    std::cout << "Deleting All Nodes:" << std::endl;
    while (m_currentPtr != nullptr) {
        temp = m_currentPtr;
        m_currentPtr = m_currentPtr->getNextPtr();
        temp->print();
        delete temp;
    }
    std::cout << "All Nodes Have Been Deleted" << std::endl;
}