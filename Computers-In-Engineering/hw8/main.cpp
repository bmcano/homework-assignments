/************************************************************
 * Programmer: Brandon Cano
 * Date: 12/7/21
 * Description:
 *   This homework assignment is meant to create a doubly
 *   linked list from the two classes Node and MyList.
 *   Then in main we are meant to unit test them by testing
 *   each method to ensure that the program works properly
 ***********************************************************/

#include <iostream>
#include "Node.h"
#include "MyList.h"

int main() {
    // Node testing
    std::cout << "Start of Node Class Testing" << std::endl;
    Node *testNode = new Node();
    Node *testNode2 = new Node();
    testNode->setNextPtr(testNode2);
    testNode2->setPreviousPtr(testNode);
    testNode->setData(2);
    testNode2->setData(5);
    testNode->print();
    std::cout << testNode2->getData() << std::endl;
    std::cout << testNode2->getPreviousPtr() << std::endl;
    std::cout << testNode->getNextPtr() << std::endl;
    delete testNode;
    delete testNode2;
    std::cout << "End of Node Class Testing" << std::endl;
    std::cout << std::endl;

    // MyList testing
    std::cout << "Start of MyList Class Testing" << std::endl;
    // print methods (showing empty)
    MyList list1;
    list1.printAscending();
    list1.printDescending();
    std::cout << std::endl;

    // check for empty list and insert
    list1.insert(5);
    list1.printAscending();
    list1.printDescending();
    std::cout << std::endl;

    // insert item at the beginning
    list1.insert(1);
    list1.printAscending();
    list1.printDescending();
    std::cout << std::endl;

    // insert at end
    list1.insert(9);
    list1.printAscending();
    list1.printDescending();
    std::cout << std::endl;

    // insert in the middle
    list1.insert(7);
    list1.insert(3);
    list1.printAscending();
    list1.printDescending();
    std::cout << std::endl;

    MyList list2;
    list2.insert(1);
    list2.insert(9);
    list2.insert(2);
    list2.insert(3);
    list2.insert(4);
    list2.insert(5);
    list2.printAscending();
    list2.printDescending();
    std::cout << std::endl;

    // remove items
    list2.remove(2);
    list1.remove(10); // item does not exist

    // clear list
    list2.clear();
    list2.printAscending();
    list2.printDescending();
    std::cout << "End of MyList Class Testing" << std::endl;
    std::cout << std::endl;

    return 0;
}