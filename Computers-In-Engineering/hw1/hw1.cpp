/*********************************************************************************
 * Programmer: Brandon Cano
 * Date: 8/28/21
 * Name: hw1
 * Description: In this assignment we take DNA strands and STRs counts for
 * different people and analyze them to match each person with their DNA strand
 * and this is done by reading files of the needed information in order to
 * come to a conclusion on how to match all the DNA strands this version
 * of my code is also expandable so it is possible to add more people/DNA strands
 * as long as it is in consistent formatting
 ********************************************************************************/
#include <iostream>
#include <fstream>
#include <string>
#include <vector>

using namespace std;

// initialize function
string patternFinder(string sequence, const string& pattern);

int main() {
    vector<string> dnaSequences;            // this vector will have all the DNA strands
    vector<string> strPatterns;             // this will hold all 3 STRs to count in each DNA strand
    vector<string> names;                   // list of names from CSV file
    string name, STR1, STR2, STR3;          // temporary variables for file reading
    string dnaSequence;                     // temporary variable for file reading

    vector<string> strNumbers;              // this tracks all the numbers from the CSV file
    vector<string> patternAppearances;      // this will hold all the numbers from the CSV file

    string currentCount[3];                 // array for analyzing data from files

    // NOTE: I have specifically set up the txt like this in order for it to be easily
    // expandable and so you only need two files to match everything and analyze
    // everything instead of having a separate txt for each person

    // this opens, reads, then closes the file containing the strands of DNA
    ifstream finDNA("DNA.txt");
    if(finDNA.fail()) {
        cerr << "ERROR: File DNA.txt could not be opened" << endl;
    }
    while(getline(finDNA, dnaSequence)) {
        dnaSequences.push_back(dnaSequence);
    }
    finDNA.close();

    // this opens, reads, then closes the file containing the names and the STRs
    ifstream finSTR("STR.csv");
    if(finSTR.fail()) {
        cerr << "ERROR: File STR.csv could no tbe opened" << endl;
    }
    while(true) {
        // this gathers each line of info all at once (EX: name,STR1,STR2,STR3)
        getline(finSTR, name, ',');
        getline(finSTR, STR1, ',');
        getline(finSTR, STR2, ',');
        getline(finSTR, STR3);

        if(!finSTR.good()) break; // exiting condition

        // we only want the STR patterns to be added for the first iteration
        if(name == "name") {
            strPatterns.push_back(STR1);
            strPatterns.push_back(STR2);
            strPatterns.push_back(STR3);
            continue;
        }
        names.push_back(name);
        patternAppearances.push_back(STR1);
        patternAppearances.push_back(STR2);
        patternAppearances.push_back(STR3);
    }
    finSTR.close();

    // run through each sequence and call patternFinder, then place each DNA strand
    // with the person that identifies with the collected information
    for(auto& sequence : dnaSequences) {
        for(int j=0; j<3; j++) {
            currentCount[j] = patternFinder(sequence, strPatterns[j]);
        }
        // for readability
        string p1 = currentCount[0];
        string p2 = currentCount[1];
        string p3 = currentCount[2];

        // this will make sure that the names will align with their DNA
        int iter = 0;
        for(int k=0; k<patternAppearances.size(); k+=3) {
            if(p1 == patternAppearances[k] && p2 == patternAppearances[k+1] && p3 == patternAppearances[k+2]) {
                cout << "This sequence matches with " << names[iter] << ":\n" << sequence << endl;
                break;
            }
            if(iter == names.size()-1) {
                cout << "No Match:\n" << sequence << endl;
                break;
            }
            iter++;
        }
    }
    return 0;
}

/************************************************************************
 * Function: Counts the highest number of repeated STRs for a DNA strand
 * Inputs:
 *   sequence - string - a DNA strand
 *   pattern - string - the STR to find and count in the DNA strand
 * Outputs:
 *   highestCount - string - the highest amount of times an STR repeats
 ***********************************************************************/
string patternFinder(string sequence, const string& pattern) { // NOLINT(performance-unnecessary-value-param)
    int highestCount, currentCount = 0;             // both are related to the amount of repetitions *Ignore NOLINT*
    int currentLocation = sequence.find(pattern);   // NOLINT(cppcoreguidelines-narrowing-conversions)

    // iterates through the sequence of the DNA to find the repetition for each pattern
    for(int i = currentLocation+4; i<sequence.size()+1; i+=4) {
        // for when the pattern continues
        if(sequence.substr(currentLocation, 4) == pattern) {
            currentCount++;

            // this will keep track of the highest count in a row
            if(currentCount > highestCount) {
                highestCount = currentCount;
            }
        }
        // resets counter when the pattern stops
        else {
            currentCount = 0;
        }
        currentLocation = i;
    }
    return to_string(highestCount);
}