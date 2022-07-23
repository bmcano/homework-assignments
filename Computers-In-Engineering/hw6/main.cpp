//
// Note: Code was modified from Figure 11.3.1: Overriding member function.
// in the CIE ZyBook class textbook.

// Programmer: Brandon Cano
// Date: 11/4/21
// Description: This code was further modified to practice using constructors, destructors, copy constructors,
//  copy assignment operators, and pointers.
//  Also, all preexisting class methods with parameters were modified to be passed by constant alias

#include <iostream>
using namespace std;

class Computer {
public:
    // constructor
    explicit Computer(string usage = "0%", string status = "not connected"): cpuUsage{usage}, internetStatus{status} {
        // allocate memory
        memorySize = new float;
        *memorySize = 0;
    };

    // destructor
    ~Computer() {
        cout << "Computer Destructor Called:" << endl;
        print();
        // frees memory
        delete memorySize;
    };

    // copy constructor
    Computer(const Computer& obj) {
        cpuUsage = obj.getCPUUsage();
        internetStatus = obj.getInternetStatus();
        // allocate memory
        memorySize = new float;
        *memorySize = obj.getMemorySize();
    }

    // copy assignment operator
    Computer& operator=(const Computer& objToCopy) {
        if (this != &objToCopy) {
            delete memorySize;
            memorySize = new float;
            *memorySize = (objToCopy.getMemorySize());
        }
        setCPUUsage(objToCopy.getCPUUsage());
        setInternetStatus(objToCopy.getInternetStatus());

        return *this;
    }

    // getter and setter for cpuUsage
    string getCPUUsage() const {
        return cpuUsage;
    }
    void setCPUUsage(const string& usage) {
        cpuUsage = usage;
    }

    // getter and setter for internetStatus
    string getInternetStatus() const {
        return internetStatus;
    }
    void setInternetStatus(const string& status) {
        internetStatus = status;
    }

    // getter and setter for memorySize
    float getMemorySize() const {
        // if somehow the pointer is equal to the null pointer we
        // can not return that, so instead we return 0. Otherwise,
        // return the dereferenced value
        if(memorySize == nullptr) {
            return 0;
        }
        // dereference pointer
        return *memorySize;
    }
    void setMemorySize(const float& size) {
        // if we go to set a value and for some reason it is the nullptr
        // then we must allocate memory for the pointer
        if(memorySize == nullptr) {
            // allocate memory
            memorySize = new float;
        }
        *memorySize = size;
    }

    // if both values are known right away we can use this setter method
    // instead of using both individually
    void setComputerStatus(const string& usage, const string& status) {
        cpuUsage = usage;
        internetStatus = status;
    };

    // prints members variables
    void print() {
        cout << "  CPU usage: " << cpuUsage << endl;
        cout << "  Internet status: " << internetStatus << endl;
        cout << "  Memory size: " << *memorySize << endl;
    };

protected:
    float* memorySize = nullptr;

private:
    string cpuUsage;
    string internetStatus;
};


class Laptop : public Computer {
public:
    // constructor
    explicit Laptop(string usage = "0%", string status = "not connected" , string quality = "bad"): Computer(usage, status), wifiQuality{quality} {};

    // destructor
    ~Laptop() {
        cout << "Laptop Destructor Called:" << endl;
        print();
    };

    // copy constructor
    Laptop(const Laptop& obj) {
        setCPUUsage(obj.getCPUUsage());
        setInternetStatus(obj.getInternetStatus());
        setWifiQuality(obj.getWifiQuality());
        setMemorySize(obj.getMemorySize());
    }

    // copy assignment operator
    Laptop& operator=(const Laptop& objToCopy) {
        if (this != &objToCopy) {
            delete memorySize;
            memorySize = new float;
            *memorySize = (objToCopy.getMemorySize());
        }
        setCPUUsage(objToCopy.getCPUUsage());
        setInternetStatus(objToCopy.getInternetStatus());
        setWifiQuality(objToCopy.getWifiQuality());

        return *this;
    }

    // getter and setter for wifiQuality
    string getWifiQuality() const {
        return wifiQuality;
    }
    void setWifiQuality(const string& quality) {
        wifiQuality = quality;
    }

    // this is used, so we do not have to use all the setters individually
    void setComputerStatus(const string& usage, const string& status, const string& quality) {
        setCPUUsage(usage);
        setInternetStatus(status);
        setWifiQuality(quality);
    };

    // prints all member variables for both Laptop and Computer class
    void print() {
        cout << "  CPU usage: " << getCPUUsage() << endl;
        cout << "  Internet status: " << getInternetStatus() << endl;
        cout << "  WiFi quality: " << getWifiQuality() << endl;
        cout << "  Memory size: " << getMemorySize() << endl;
    };

private:
    string wifiQuality;
};


int main() {
    // this portion will not be changed per instruction
    Laptop myLaptop;

    myLaptop.setComputerStatus("25%", "connected", "good");
    myLaptop.print();

    // My unit testing
    // Part 3-5
    cout << "----------------------------" << endl;
    Computer c1;
    c1.setCPUUsage("50%");
    c1.setInternetStatus("connected");
    //c1.setMemorySize(2);
    cout << "Getters Test:\n";
    cout << c1.getCPUUsage() << endl;
    cout << c1.getInternetStatus() << endl;
    cout << c1.getMemorySize() << endl;
    c1.print();

    // part 8-9
    cout << "----------------------------" << endl;
    Computer c2("30%","not connected");
    c2.setMemorySize(3);
    Laptop l1("10%", "connected", "great");
    cout << "c2:\n";
    c2.print();
    cout << "l1:\n";
    l1.print();

    // part 12-13
    cout << "----------------------------" << endl;
    Computer c3(c2);
    Laptop l2(l1);
    c3.print();
    l2.print();

    // part 14-15
    cout << "----------------------------" << endl;
    Computer c4 = c2;
    c4.print();
    Laptop l3 = l1;
    l3.print();
    cout << "----------------------------" << endl;
    cout << "extra testing" << endl;
    // extra testing
    c4.setMemorySize(2);
    c4.setCPUUsage("100%");
    c4.print();
    c2.print();

    cout << endl;

    l3.setMemorySize(6);
    l3.setInternetStatus("disconnected");
    l3.print();
    l1.print();

    cout << "----------------------------" << endl;
    cout << "\nSTART OF DESTRUCTOR TEXT\n" << endl;

    return 0;
}