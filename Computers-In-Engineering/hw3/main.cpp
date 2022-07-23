/********************************************************************************
 * Programmer: Brandon Cano
 * Date: 9/27/21
 * Description: PART 1: The general idea of this assignment is to create a social
 *   network and be able to perform various tasks from the class methods
 *   and this is shown by unit testing
 *   PART 2: For the second part we have a basic interface that the user
 *   can interact with and perform various tasks. Something important to note
 *   is that for both the name and status it can only be a single word
 *   **no whitespace** can be in the input or the program will break, this is
 *   also stated below
 *******************************************************************************/

#include <iostream>
#include "Profile.h"
#include "ProfileDatabase.h"

using namespace std;

int main() {
    cout << "|----------MILESTONE-1-TESTING----------|" << endl;
    Profile person1("Jane", "Seeing someone");
    person1.print();

    person1.addFriend("Joe");
    person1.addFriend("Sally");
    person1.addFriend("Sam");
    person1.print();

    person1.setStatus("Programming");
    person1.deleteFriend("Sam");
    person1.deleteFriend("Bob");
    person1.print();
    cout << endl;


    cout << "|----------MILESTONE-2-TESTING----------|" << endl;
    // milestone 2 testing from document
    ProfileDatabase database;
    Profile profileA("Sam","programming");
    Profile profileB("Sally","reading");
    Profile profileC("Sally");
    Profile profileD("Bob","doing homework");
    database.addProfile(profileA);
    database.addProfile(profileB);
    database.addProfile(profileC);
    database.addProfile(profileD);
    database.print();

    database.addFriends("Sam", "Sally");
    database.addFriends("Sam", "Sally");
    database.addFriends("Sam", "Bob");
    database.addFriends("Sam", "Joe");
    database.addFriends("Sam", "Sam");
    database.updateStatus("Sam", "I am making friends");
    database.getProfile("Sam").print();
    database.deleteFriends("Sam", "Sally");
    database.deleteFriends("Sam", "Joe");
    database.deleteProfile("Sally");
    database.getProfile("Sam").print();
    database.print();

    ////////////////////////////////////////////////////////////////
    ////--------------USER-INTERFACE-SECTION-BELOW--------------////
    ////////////////////////////////////////////////////////////////

    cout << "|-----Social Network Interface Below-----|" << endl;
    ProfileDatabase database1;

    int input;
    int subInput;
    string name;
    string status;
    string subName;
    int doesProfileExist;
    bool subMenu;
    bool menu = true;
    bool choice;

    /////////////////////////////////////////////////////////////////////
    //// IMPORTANT: Names and Statuses CANNOT contain any whitespace ////
    //// because the program was not designed to handle this and     ////
    //// I do not have the time to implement it                      ////
    /////////////////////////////////////////////////////////////////////
    while(menu){
        cout << "-------------------\n";
        cout << "1. Create Profile\n";
        cout << "2. Lookup Profiles\n";
        cout << "3. Modify Profile\n";
        cout << "4. Exit\n";
        cout << "-------------------\n";
        cin >> input;

        // breaks are used because we are using switch case statements
        switch (input) {
            // Create a Profile
            case 1:
                cout << "Select a Name: ";
                cin >> name;
                doesProfileExist = database1.getProfileIndex(name);

                if(doesProfileExist == -1) {
                    cout << "Select a Status: ";
                    cin >> status;
                    Profile profileT(name, status);
                    database1.addProfile(profileT);
                    continue;
                }

                cout << name << " has already been taken." << endl;
                break;

            // Lookup Profiles
            case 2:
                database1.print();
                cout << "Type in a name of a profile you want to look at:\n";
                cin >> name;
                database1.getProfile(name).print();
                break;

            // Modify Profile
            case 3:
                subMenu = true;
                cout << "Type in the name of your profile:\n";
                cin >> name;

                // go back to main menu if profile does not exist
                if(database1.getProfileIndex(name) == -1) {
                    cout << "Profile does not exist" << endl;
                    break;
                }


                // sub menu
                while(subMenu) {
                    cout << "-------------------\n";
                    cout << "1. Change Status\n";
                    cout << "2. Add Friend\n";
                    cout << "3. Delete Friend\n";
                    cout << "4. Delete Profile\n";
                    cout << "5. Main Menu\n";
                    cout << "-------------------\n";
                    cin >> subInput;

                    switch (subInput) {
                        // change status
                        case 1:
                            cout << "New Status:\n";
                            cin >> status;
                            database1.updateStatus(name, status);
                            break;

                            // add friend
                        case 2:
                            cout << "Type in the name of the friend" << endl;
                            cin >> subName;
                            if (database1.getProfileIndex(subName) == -1) {
                                cout << "Profile does not exist" << endl;
                            } else {
                                database1.addFriends(name, subName);
                                break;
                            }

                            // delete friend
                        case 3:
                            cout << "Type in the name of the friend" << endl;
                            cin >> subName;
                            if (database1.getProfileIndex(subName) == -1) {
                                cout << "Profile does not exist" << endl;
                            } else {
                                database1.deleteFriends(name, subName);
                                break;
                            }

                            // delete profile
                        case 4:
                            cout << "Are you sure you want to delete your profile? (1=yes, 0=no):" << endl;
                            cin >> choice;
                            if (choice) {
                                database1.deleteProfile(name);
                            }

                            // exit to main menu
                        case 5:
                            subMenu = false;
                    }
                }
                break;

            case 4:
                menu = false;
        }
    }
    return 0;
}
