/*----------------------------------------------------------------
Name: Brandon Cano
Date: 11/15/20
Filename: HW6.cpp
Description: A program that takes a user input for a molecule 
then calculates its molecular mass
----------------------------------------------------------------*/

#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main()
{
  int elementAmount;
  // opens PeriodicTable.txt
  ifstream fileIn("PeriodicTable.txt");
  if (fileIn.fail()) // checks to see if file opened
  {
    cerr << "PeriodicTable.txt could not be opened" << endl;
    return -1;
  }
  fileIn >> elementAmount; // reads the amounts of elements on the PeriodicTable.txt file
   
  int atomicNumber[elementAmount]; // atomic number array from txt file
  string elementNames[elementAmount]; // name of elements array from txt file
  double molecularWeight[elementAmount]; // molecular mass array from txt file
  string userInput; // intial input
  int userAmount[118]; // amount of each element in formula
  string input[118]; // array for elements that are entered in by user
  double molecularMass; // variables that stores the molecular mass
    
  for (int i=0; i<elementAmount; i++) // reads all the contents from PeriodicTable.txt file
    fileIn >> atomicNumber[i] >> elementNames[i] >> molecularWeight[i];

  cout << "Please enter a molocule formula (enter element first then a number after with spaces in between then a XX at the end)" << endl;
  // takes the user input for the molecule
  for(int j=0; j<elementAmount; j++)
  {
    cin >> userInput; 
    if (userInput == "XX") // once it detects the XX it will break from the loop
      break;
    input[j] = userInput; // saves the user input to arrays
    cin >> userAmount[j];    
  }
  
  // compares the users input and finds it from the array created by the txt file and is able to find the atomic weight
  for (int k=0; k<118; k++)
    for (int l=0; l<118; l++) 
      if (input[k] == elementNames[l])
        molecularMass += userAmount[k] * molecularWeight[l]; // calculation for molecular weight of each element and totals it
    
  // displays molecular weight
  cout << molecularMass << " g/mol" << endl;   
  return 0;
}