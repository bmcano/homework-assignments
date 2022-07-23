/**************************************************
  Name: Brandon Cano
  Date: 9/19/20
  Program name: HW2.cpp
  Program description: Calculate the average temperature
                        of various heat plates
**************************************************/

#include <iostream>
#include <cmath>
using namespace std;

int main()
{
  // Defining all the variables
  
  int length = 0;
  int height = 0;
  // for storing the values that gives the highest average temperature
  int hmax = 0;
  int lmax = 0;
  // coordinates 
  double x = 0;
  double y = 0;
  // for the calulations
  double temp = 0;
  double maxavg = 0;
  // pi constant
  const double PI = acos(-1);
   
  // Start of loop section
  // First two for loops run through all 36 combinations of the dimensions
  for (height = 1; height <= 6; height++)
  {
    for (length = 1; length <= 6; length++)
    {
      // Next 2 for loops run through all 1000000 sensors in the plate
      temp = 0;
      for (double i = 0; i <= 999; i++)
       {
        x = (i*length)/999; // gives x coordinate
        for (double j = 0; j <= 999; j++)
        {
          y = (j*height)/999; // gives y coordinate
          // Main equation for finding the sum of the temps
          temp += 100 + ((100 * sin((PI*x)/length)*sinh((PI*y)/length)) / sinh((PI*height)/length));
        }
       }
      
      double tempavg = temp/(1000*1000); // Calculates the average temperature
      
      if (tempavg > maxavg) // Keeps track of the max average value and stores it
      {
        maxavg = tempavg;
        hmax = height;
        lmax = length;
        cout << "Height = " << height << "; Length = " << length << "; Average Temperature = " << tempavg << endl;
        // Still want to print the value as normal which is why this is a little redundent
      }
      else
      {
        cout << "Height = " << height << "; Length = " << length << "; Average Temperature = " << tempavg << endl; 
      }      
    }
  }
  // After all the calulations the last bit will give the max average value and its dimensions
  cout << "The max average is " << maxavg << " with the height being " << hmax << "cm and the length being " << lmax << "cm" << endl;
  
  return 0;
}
