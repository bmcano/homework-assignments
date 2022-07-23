#include <iostream>
#include "ProfileDatabase.h"

/*********************************************************
 * addProfile in class ProfileDatabase
 *   Inputs: profile of class Profile
 *   Outputs: None
 *  This method will take in a profile object and then
 *  add it to the database if it is valid (EX: is unique)
 ********************************************************/
void ProfileDatabase::addProfile(const Profile& profile) {
    int index = getProfileIndex(profile.getName());
    if(index == -1) {
        std::cout << profile.getName() << " was added to the social network." << std::endl;
        profiles.push_back(profile);
        return;
    }
    std::cout << "Warning: A profile with the name " << profile.getName() << " already exist." << std::endl;
    std::cout << "\tProfile not added to the database." << std::endl;
}

/*******************************************************************
 * getProfile in class ProfileDatabase
 *   Inputs: profile name of type string
 *   Outputs: profile object
 *  This method will take in a string (name of profile) and then
 *  check to see if it is in the database, and will return the info
 ******************************************************************/
Profile ProfileDatabase::getProfile(std::string profile) const {
    for(int i=0; i<profiles.size(); i++) {
        if(profiles[i].getName() == profile) {
            return profiles[i];
        }
    }
    Profile emptyProfile("missing profile", "");
    return emptyProfile;
}

/*******************************************************************
 * deleteProfile in class ProfileDatabase
 *   Inputs: profile name of type string
 *   Outputs: None
 *  This method will take in a profile name (string) and then
 *  will delete the profile from the database if it currently exist
 ******************************************************************/
void ProfileDatabase::deleteProfile(std::string profile) {
    int index = getProfileIndex(profile);
    if(index == -1) {
        std::cout << profile << " does not exist" << std::endl;
        return;
    }
    profiles.erase(profiles.begin()+index);
}

/*******************************************************************
 * getProfileIndex in class ProfileDatabase
 *   Inputs: profile name of type string
 *   Outputs: integer of the index of the profile in the vector
 *  This method will find the index of a specific profile and will
 *  return this value, if the profile does not exist then it will
 *  return -1, this is used as an "error". This is also only a
 *  helper method for other methods as of now
 ******************************************************************/
int ProfileDatabase::getProfileIndex(std::string profile) const {
    for(int i=0; i<profiles.size(); i++) {
        if(profiles[i].getName() == profile) {
            return i;
        }
    }
    return -1;
}

/*******************************************************************
 * addFriends in class ProfileDatabase
 *   Inputs: profile name 1 and 2 of type string
 *   Outputs: None
 *  This method will take in two strings (profile names) and will
 *  first check to see if the profile exist, then will proceed to
 *  add both profiles to each others friends list
 ******************************************************************/
void ProfileDatabase::addFriends(std::string profile1, std::string profile2) {
    Profile p1 = getProfile(profile1);
    Profile p2 = getProfile(profile2);

    // only here to check to see if both profiles exist
    if(p1.getName() == "missing profile") {
        std::cout << "Invalid input to addFriends: " << profile1 << " does not exist" << std::endl;
        return;
    }
    if(p2.getName() == "missing profile") {
        std::cout << "Invalid input to addFriends: " << profile2 << " does not exist" << std::endl;
        return;
    }
    if(p1.getName() == p2.getName() && p1.getName() != "missing profile") {
        std::cout << "Invalid input to addFriends: you cannot be in your own friend list." << std::endl;
        return;
    }

    int index1 = getProfileIndex(profile1);
    int index2 = getProfileIndex(profile2);
    profiles[index1].addFriend(profile2);
    profiles[index2].addFriend(profile1);
}

/*******************************************************************
 * deleteFriends in class ProfileDatabase
 *   Inputs: profile name 1 and 2 of type string
 *   Outputs: None
 *  This method is similar to addFriends and does almost the same
 *  thing except that this method will delete the friends from the
 *  list rather than add them to it
 ******************************************************************/
void ProfileDatabase::deleteFriends(std::string profile1, std::string profile2) {
    Profile p1 = getProfile(profile1);
    Profile p2 = getProfile(profile2);

    // only here to make sure profiles exist
    if(p1.getName() == "missing profile") {
        std::cout << "Invalid input to deleteFriends: " << profile1 << " does not exist" << std::endl;
        return;
    }
    if(p2.getName() == "missing profile") {
        std::cout << "Invalid input to deleteFriends: " << profile2 << " does not exist" << std::endl;
        return;
    }
    if(p1.getName() == p2.getName() && p1.getName() != "missing profile") {
        std::cout << "Invalid input to deleteFriends: you cannot be deleted from your own friend list." << std::endl;
        return;
    }

    int index1 = getProfileIndex(profile1);
    int index2 = getProfileIndex(profile2);
    profiles[index1].deleteFriend(profile2);
    profiles[index2].deleteFriend(profile1);
}

/*******************************************************************
 * updateStatus in class ProfileDatabase
 *   Inputs: profile name and status of type string
 *   Outputs: None
 *  This method will first check to see if the profile exist then
 *  will set the status as whatever the string is
 ******************************************************************/
void ProfileDatabase::updateStatus(std::string profile, std::string status) {
    int index = getProfileIndex(profile);
    if(index == -1) {
        std::cout << profile << " does not exist. Status was not updated." << std::endl;
        return;
    }
    profiles[index].setStatus(status);
}

/*******************************************************************
 * print in class ProfileDatabase
 *   Inputs: None
 *   Outputs: None
 *  This method will print out all the profile names in the database
 ******************************************************************/
void ProfileDatabase::print() {
    std::cout << "List of Profiles:"<< std::endl;
    for(int i=0; i<profiles.size(); i++) {
        std::cout << "  " << profiles[i].getName() << std::endl;
    }
}