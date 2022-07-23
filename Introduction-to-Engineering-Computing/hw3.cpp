/*-----------------------------------------------------------------------------------
Name: Brandon Cano
Date: 10/2/20
Assignment: Homework 3
Filename: hw3.cpp
Description: Reading the items from a shopping list and finding them at a store list
and figuring out which store saves you money and writing it onto savings.txt
-----------------------------------------------------------------------------------*/

#include <iostream>
#include <fstream>
#include <cmath>
using namespace std;

int main()
{
  // defining I/O stream variables
  ifstream fileIN ("priceList.txt");
  ifstream fin ("shoppingList.txt");
  ofstream fileOUT ("savings.txt");
  
  if (fileIN.fail()) //checks to see if file opened
  {
    cerr << "priceList.txt could not be opened" << endl;
    return -1;
  }
  
  if (fin.fail()) //checks to see if file opened
  {
    cerr << "shoppingList.txt could not be opened" << endl;
    return -1;
  }
  if (fileOUT.fail()) //checks to see if file opened
  {
    cerr << "savings.txt could not be opened" << endl;
    return -1;
  }
  
  // variables
  string shopItem;
  string item;
  string store;
  
  double priceA;
  double priceH;
  double savings;
  double totalSavingsA;
  double totalSavingsH;
  double amountSaved;
  
  int quantity;
  int itemAmount;
  int shopAmount;
  
  // initial amount of items
  fileIN >> itemAmount;
  fin >> shopAmount;

  // for loop for the shopping list of the amount of items
  for (int i=0; i < shopAmount; i++)
  {
    
    fin >> shopItem;
    fin >> quantity;
    
    // while loop is used to find a match on the pricelist.txt file
    while (shopItem != item)
    {
      // reads the item name and the prices from amazon and hyvee
      fileIN >> item;
      fileIN >> priceA;
      fileIN >> priceH;
      // for calculating savings when a match is detected
      if (shopItem == item)
      {
        savings = fabs(priceH - priceA);
        savings = savings * quantity;
        if (priceH > priceA)
        {
          // if amazon saves you money
          store = " Amazon";
          totalSavingsA += savings;
        }
        else
        {
          // if hyvee saves you money
          store = " Hyvee";
          totalSavingsH += savings;
        }
        // writes the amount you saves and from which store it was
        fileOUT << "You saved $" << savings << " on " << shopItem << " at "<< store << endl;
        amountSaved += savings;
        savings = 0;
      }    
    }   
  }
  
  // this detects which store save you the most money
  if (totalSavingsA > totalSavingsH)
  {
    cout << "Amazon saved you more money" << endl;
  }
  else
  {
    cout << "Hyvee saved you more money" << endl;
  }
  
  // prints out the total amount saved and then the amount saved from both stores
  cout << "Total amount saved " << amountSaved << endl;
  cout << "Hyvee Savings $" << totalSavingsH << endl;
  cout << "Amazon Savings $" << totalSavingsA << endl;
  
  return 0;
}