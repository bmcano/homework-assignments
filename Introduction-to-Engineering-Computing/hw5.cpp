/*------------------------------------------------------------------------------
Name: Brandon Cano
Date: 11/1/20
Assignment: HW5
Filename: HW5.cpp
Description: A program that gives a random 3 question quiz from a pool of 10
questions, you can view statistics, and you can quit the program
------------------------------------------------------------------------------*/
  
#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>

using namespace std;

int main()
{
  //variables
  int initalPrompt;
  int question;
  int answer;
  int correct = 0;
  int total = 0;
  int checkOne;
  int checkTwo;
  
  //arrays
  string questionBank[10] = {"What day of the month is Halloween?","What is 15 + 15?","How many months are in a year?","What year is it?","2x-2=0: Find x","Which day of August did the Derecho hit Iowa?","Which homework assignment is this?","How many members are in the Hawkeye Marching Band?","What is 5*5?","How many months have 31 days?"};
  int answerBank[10] = {31,30,12,2020,1,10,5,260,25,7};
  int amountBank[10] = {0,0,0,0,0,0,0,0,0,0};
  int correctBank[10] = {0,0,0,0,0,0,0,0,0,0};
  
  //setting a seed
  srand(time(0));
  
  //Inital Prompt
  cout << "1. Take quiz \n2. View question statistics \n3. Quit" << endl;
  cin >> initalPrompt;
  // while loop that keeps running until the user quits
  while (initalPrompt != 3)
  { 
    // start of option 1
    if (initalPrompt == 1)
    {
      // for asking the 3 questions
      for (int i=0; i<3; i++)
      {
        // the if and while statements will check to see if a question was already asked
        if (i == 0)
        {
          question = rand() % 10;
          checkOne = question;
        }
        if (i == 1)
        {
          question = rand() % 10;
          while (question == checkOne)
            question = rand() % 10;
          checkTwo = question;
        }
        if (i == 2)
        {
          question = rand() % 10; 
          while (question == checkOne || question == checkTwo)
            question = rand() % 10;     
        }
        
        // displays question        
        cout << questionBank[question] << endl;
        cin >> answer;

        //check to see if anwser is correct
        if (answer == answerBank[question])
        {
          correct++;
          total++;
          amountBank[question]++;
          correctBank[question]++;
          cout << "You are correct" << endl;
        }
        else // this lets the use know if they answered correctly
        {
          total++;
          amountBank[question]++;
          cout << "Your answer is incorrect" << endl;
        } 
      }       
    } 
    
    // start of option 2
    if (initalPrompt == 2)
    {
      cout << "------------Statistics------------\n" << "You have answered "<< correct << "/" << total << " correctly"<< endl;
      for(int j=0; j<10; j++)
      {
        cout << "-------------------------------------------------" << endl;
        cout << questionBank[j] << "\nThis question has been asked " << amountBank[j] << " times\nYou have answered this question correctly " << correctBank[j] << " times" << endl; 
      } 
    }   
    // menu choice shows up again  
    cout << "1. Take quiz \n2. View question statistics \n3. Quit" << endl;
    cin >> initalPrompt; 
  }  
  
  //option 3
  return 0;
}