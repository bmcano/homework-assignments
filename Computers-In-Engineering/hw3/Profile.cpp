//
// Created by Brand on 9/23/2021.
//
#include <iostream>
#include "Profile.h"

// constructor
Profile::Profile(std::string name, std::string status) {
    setName(name);
    setStatus(status);
}

// getters
std::string Profile::getName() const {
    return m_name;
}
std::string Profile::getStatus() const{
    return m_status;
}

// setters
void Profile::setName(std::string name) {
    m_name = name;
}
void Profile::setStatus(const std::string& status) {
    m_status = status;
}

/********************************************************************
 * print() method in class Profile
 *   no inputs or outputs
 *  only purpose is to print out all the information for the profile
 *******************************************************************/
void Profile::print() {
    std::cout << "Name: " << m_name << "\nStatus: " << m_status << std::endl;
    std::cout << "Friends: " << std::endl;
    for(int i=0; i<m_friendList.size(); i++) {
        std::cout << "  " << m_friendList[i] << std::endl;
    }
}

/********************************************************************
 * addFriend method in class Profile
 *   Inputs: string friendName - name of friend to be added to the
 *     current profile
 *   Outputs: None
 *   only purpose is to add a friend to the friend list vector
 *******************************************************************/
void Profile::addFriend(std::string friendName) {
    for(int i=0; i<m_friendList.size(); i++) {
        if(m_friendList[i] == friendName) {
            std::cout << friendName << " is already a friend of " << m_name << std::endl;
            return;
        }
    }
    m_friendList.push_back(friendName);
    std::cout << friendName << " added to " << m_name << "'s friend list." << std::endl;
}

/********************************************************************
 * deleteFriend method in class Profile
 *   Inputs: string friendName - name of friend to be deleted to the
 *     current profile
 *   Outputs: None
 *   only purpose is to remove a friend to the friend list vector
 *******************************************************************/
void Profile::deleteFriend(std::string friendName) {
    for(int i=0; i<m_friendList.size(); i++) {
        if(m_friendList[i] == friendName) {
            m_friendList.erase(m_friendList.begin()+i);
            std::cout << friendName << " removed from " << m_name << "'s friend list." << std::endl;
            return;
        }
        if(i==m_friendList.size()-1) {
            std::cout << friendName << " is not in " << m_name << "'s friend list." << std::endl;
        }
    }
}