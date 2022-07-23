/************************************************************
 * Programmer: Brandon Cano
 * Date: 12/6/21
 * Description:
 *   Node header file that initializes every value and method
 *   for the Node class file
 ***********************************************************/

#ifndef HW8_NODE_H
#define HW8_NODE_H

class Node {
public:
    // explicit constructor
    explicit Node(int data = 0, Node* previousPtr = nullptr, Node* nextPtr = nullptr);

    // getter and setter for m_previousPtr
    Node* getPreviousPtr() const;
    void setPreviousPtr(Node* previousPtr);

    // getter and setter for m_data
    int getData() const;
    void setData(const int& newData);

    // getter and setter for m_nextPtr
    Node* getNextPtr() const;
    void setNextPtr(Node* nextPtr);

    // print method
    void print();

private:
    Node* m_previousPtr;
    int m_data;
    Node* m_nextPtr;
};

#endif //HW8_NODE_H