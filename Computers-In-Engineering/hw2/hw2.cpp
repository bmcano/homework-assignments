/*********************************************************************************
 * Programmer: Brandon Cano
 * Date: 9/13/21
 * Name: hw2
 * Description: The purpose of this assignment is to be able to validate an email
 *   address via a function. Then we should be able to create an object from a
 *   class in order to "send" an email to another person, with the time, subject,
 *   body, as well as the sender and recipient.
 ********************************************************************************/
#include <iostream>
#include <string>
#include <ctime>

using namespace std;

class EmailMessage {
public:
    explicit EmailMessage(string sender = "", string recipient = "", time_t timeSent = 0, string subject = "(no subject)", string body = "(empty body)") {
        setSender(sender);
        setRecipient(recipient);
        setTimeSent(timeSent);
        setSubject(subject);
        setBody(body);
    }

    // getter methods for all the data members
    string getSender() const {
        return m_sender;
    }
    string getRecipient() const {
        return m_recipient;
    }
    time_t getTimeSent() const {
        return m_timeSent;
    }
    string getSubject() const {
        return m_subject;
    }
    string getBody() const {
        return m_body;
    }

    // setter methods for all the data members
    void setSender(string sender) {
        m_sender = sender;
    }
    void setRecipient(string recipient) {
        m_recipient = recipient;
    }
    // In order to make sure someone does not try to enter a negative
    // value the if statement is set in place to make sure that it
    // can not happen, although using currentTime is ideal
    void setTimeSent(time_t timeSent) {
        m_timeSent = 0;
        if(timeSent >= 0) {
            m_timeSent = timeSent;
        }
    }
    void setSubject(string subject) {
        m_subject = subject;
    }
    void setBody(string body) {
        m_body = body;
    }

    void printEmail() {
        cout << "\nOutput from print method:\n";
        cout << "From: " << m_sender << endl;
        cout << "To: " << m_recipient << endl;
        cout << "Time: " << m_timeSent << " seconds since 00:00 hours, Jan 1, 1970 UTC\n";
        cout << "Subject: " << m_subject << endl;
        cout << m_body << endl;
    }

private:
    string m_sender;
    string m_recipient;
    time_t m_timeSent;
    string m_subject;
    string m_body;
};

bool validateEmailAddress(const string& email);

int main() {
    // unit testing makes this whole thing look like a mess especially with the expected outputs
    // create and gathers the current time
    time_t currentTime;
    time(&currentTime);

    // unit testing for validateEmailAddress function (5 test cases), with expected and actual outputs
    cout << boolalpha;
    cout << "brandon-cano@uiowa.edu is valid, validateEmailAddress returns -> " << validateEmailAddress("brandon-cano@uiowa.edu") << endl;
    cout << "abc.def@mail#archive.com is invalid, validateEmailAddress returns -> " << validateEmailAddress("abc.def@mail#archive.com") << endl;
    cout << "-testemail123@mail.c is invalid, validateEmailAddress returns -> " << validateEmailAddress("-testemail123@mail.c") << endl;
    cout << "this-is-valid@email.com is valid, validateEmailAddress returns -> " << validateEmailAddress("this-is-valid@email.com") << endl;
    cout << "another-_email@email.com is invalid, validateEmailAddress returns -> " << validateEmailAddress("another-_email@email.com") << endl;

    // email message unit testing (4 test cases) with expected and actual outputs
    cout << "\nExpected output from print method:\nFrom:\nTo:\nTime: 0 seconds since 00:00 hours, Jan 1, 1970 UTC\nSubject: (no subject)\n(empty body)\n";
    EmailMessage email1;
    email1.printEmail();

    cout << "\nExpected output from print method:\nFrom: gary-christensen@uiowa.edu\nTo: cie@engineering.uiowa.edu\nTime: " << currentTime <<
        " seconds since 00:00 hours, Jan 1, 1970 UTC\nSubject: Test\nHello, World!\n";
    EmailMessage email2;
    email2.setTimeSent(currentTime);
    email2.setSender("gary-christensen@uiowa.edu");
    email2.setRecipient("cie@engineering.uiowa.edu");
    email2.setSubject("Test");
    email2.setBody("Hello, World!");
    email2.printEmail();

    cout << "\nExpected output from print method:\nFrom: brandon-cano@uiowa.edu\nTo: cie@engineering.uiowa.edu\nTime: " << currentTime <<
         " seconds since 00:00 hours, Jan 1, 1970 UTC\nSubject: Question\nDid I do this right?\n";
    EmailMessage email3;
    email3.setTimeSent(currentTime);
    email3.setSender("brandon-cano@uiowa.edu");
    email3.setRecipient("cie@engineering.uiowa.edu");
    email3.setSubject("Question");
    email3.setBody("Did I do this right?");
    email3.printEmail();

    cout << "\nExpected output from print method:\nFrom: testemail123@email.com\nTo: anothertest@email.com\nTime: " << currentTime <<
         " seconds since 00:00 hours, Jan 1, 1970 UTC\nSubject: Test 2\nThis is a unique unit test\n";
    EmailMessage email4("testemail123@email.com", "anothertest@email.com", currentTime, "Test 2", "This is a unique unit test");
    email4.printEmail();

    return 0;
}

/*********************************************************************************
 * Function: takes an email address in and checks to see if it is valid by
 *   splitting it into two parts the prefix and domain.
 *   The specifications for prefixes are:
 *     - Allowed characters: letters (a-z, A-Z), numbers, underscores, periods, and hyphen.
 *     - The prefix cannot start or end with a period or a hyphen.
 *     - An underscore, period, or hyphen must be followed by one or more letter or number.
 *   The specifications for domains are:
 *     - Allowed characters: letters (a-z, A-Z), numbers, underscores, periods, and hyphen.
 *     - The last portion of the domain must be at least two characters(and only characters)
 * Inputs:
 *   email - string - is the email address that needs to be validated
 * Outputs:
 *   true/false - bool - returns false if the email is not valid and true if
 *   it is
 ********************************************************************************/
bool validateEmailAddress(const string& email) {
    // needs to contain only one of the '@' character
    if(email.find('@') == -1 || email.find_first_of('@') != email.find_last_of('@')) {
        return false;
    }

    // splits the email into two sections to check the prefix and domain separately
    int loc = email.find('@');
    string prefix = email.substr(0, loc);
    string domainName = email.substr(loc+1, email.size()-loc);

    // check to see if all characters are allowed and to make sure '.', '_', '-' do not follow on another
    for(int i=0; i<email.size(); i++) {
        if(!(isalnum(email[i]) || email[i] == '.' || email[i] == '_' || email[i] == '-' || email[i] == '@')) {
            return false;
        }
        if( i > 0 && (email[i] == '.' || email[i] == '_' || email[i] == '-')) {
            if(email[i-1] == '.' || email[i-1] == '_' || email[i-1] == '-') {
                return false;
            }
        }
    }

    // makes sure the first or last character is not a '.' or a '-' for prefixes
    char first = prefix[0], last = prefix[prefix.size()-1];
    if(first == '.' || first == '-' || last == '.' || last == '-') {
        return false;
    }

    // check to see if the last portion of the domain is at least 2 characters
    int lastPeriod = domainName.find_last_of('.');
    if(domainName.substr(lastPeriod+1, domainName.size() - lastPeriod).size() < 2 || lastPeriod == -1) {
        return false;
    }
    // checks to make sure the last characters after the last '.' are all alphabetic characters
    for(int i=lastPeriod+1; i<domainName.size(); i++) {
        if(!isalpha(domainName[i])) {
            return false;
        }
    }

    return true;
}