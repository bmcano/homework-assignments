/************************************************************
 * Programmer: Brandon Cano
 * Date: 12/6/21
 * Description:
 *   Node class file that will be in charge of connecting
 *   each node and storing the data at each location
 ***********************************************************/

#include <iostream>
#include "Node.h"

// explicit constructor
Node::Node(int data, Node* previousPtr, Node* nextPtr) {
    setPreviousPtr(previousPtr);
    setData(data);
    setNextPtr(nextPtr);
}


// getter and setter for m_previousPtr
Node* Node::getPreviousPtr() const {
    return m_previousPtr;
}

void Node::setPreviousPtr(Node* previousPtr) {
    m_previousPtr = previousPtr;
}


// getter and setter for m_data
int Node::getData() const {
    return m_data;
}


void Node::setData(const int& newData) {
    m_data = newData;
}


// getter and setter for m_nextPtr
Node* Node::getNextPtr() const {
    return m_nextPtr;
}

void Node::setNextPtr(Node* nextPtr) {
    m_nextPtr = nextPtr;
}


// print method
void Node::print() {
    std::cout << getData() << std::endl;
}