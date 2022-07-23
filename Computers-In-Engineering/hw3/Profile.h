#ifndef HW3_PROFILE_H
#define HW3_PROFILE_H
#include <string>
#include <vector>

// comment blocks are in Profile.cpp file
class Profile {
public:
    // constructor
    explicit Profile(std::string name = "missing profile", std::string status = "");

    // getters
    std::string getName() const;
    std::string getStatus() const;

    // setters
    void setName(std::string name);
    void setStatus(const std::string& status);

    // class methods
    void print();
    void addFriend(std::string friendName);
    void deleteFriend(std::string friendName);

private:
    // member variables
    std::string m_name;
    std::string m_status;
    std::vector<std::string> m_friendList;
};

#endif
