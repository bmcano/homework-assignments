#ifndef HW3_PROFILEDATABASE_H
#define HW3_PROFILEDATABASE_H
#include <vector>
#include <string>
#include "Profile.h"

// comment blocks are in ProfileDatabase.cpp file
class ProfileDatabase {
public:
    // constructor
    ProfileDatabase() = default;

    // class methods
    void addProfile(const Profile& profile);
    Profile getProfile(std::string profile) const;
    void deleteProfile(std::string profile);
    int getProfileIndex(std::string profile) const;
    void addFriends(std::string profile1, std::string profile2);
    void deleteFriends(std::string profile1, std::string profile2);
    void updateStatus(std::string profile, std::string status);
    void print();

private:
    // member variable
    std::vector<Profile> profiles;
};

#endif
