//------------------------------------------------
// Name: Brandon Cano
// Date: 9/6/20
// File: hw1.cpp
// Description: Program that determines how likely
//              we would become friends based on a score
//------------------------------------------------
#include <iostream>

using namespace std;

int main()
{
  int score = 0; 
  bool response = 0; // Variables
  int response3 = 0;
  
  cout << "In this series of questions that you will answer will help determine how likely we would become friends" << endl;
  
  cout << "Do you like to play ultimate frisbee? (1=yes, 0=no)" << endl; // Question 1 
  cin >> response;
  
  if (response == 1) // Score for question 1 and nested question
  {
    score += 15;  // Score of 15 is added
    cout << "Do you like to run? (1=yes, 0=no)" << endl; // Nested question 1
    cin >> response;
    if (response == 1)
    {
      score += 5;  // score for nested question
    }
    else
    {
      score += 0;
    }
  }
  else
  {
    score += 0;
  }
  
  
  cout << "Do you like star wars? (1=yes, 0=no)" << endl; // Question 2
  cin >> response;
  
  if (response == 1) // Score for question 2 and nested question
  {
    
    score += 10;
    cout << "Which trilogy is your favorite? (0=Original Trilogy, 1=Prequel Trilogy, 2=Sequel Trilogy)" << endl; // Nested question 2
    cin >> response3;
    if (response3 == 0)
    {
      score += 5;
    }
    else if (response3 == 1)
    {
      score += 10;
    }
    else
    {
      score -= 5;
    }
    
  }
  else
  {
    score += 0;
  }
  
  
  cout << "Do you play an instrument? (1=yes, 0=no) I play trumpet!" << endl; // Question 3
  cin >> response;
  
  if (response == 1) // Score for question 3 and nested question
  {
    score += 5;
    cout << "Are you involved with any performing arts like marching band, jazz band, or concert band? (1=yes, 0=no)" << endl; // Nested question 3
    cin >> response;
    if (response == 1)
    {
      score += 10;
    }
    else
    {
      score +=0;
    }
  }
  else
  {
    score += 0;
  }
  
  
  cout << "Do you like to play video games? (1=yes, 0=no)" << endl; // Question 4
  cin >> response;
  
  if (response == 1) // Score for question 4
  {
    score +=10;
  }
  else
  {
    score -= 5;
  }
  
  
  cout << "Do you like to hang out/spend time with your friends? (1=yes, 0=no)" << endl; // Question 5
  cin >> response;
  
  if (response == 1) // Score for question 5
  {
    score += 15;
  }
  else 
  {
    score -= 10;
  }
  
  
  cout << "Are you willing to meet new people? (1=yes, 0=no)" << endl; // Question 6
  cin >> response;
  
  if (response == 1) // Score for question 6
  {
    score += 5;
  }
  else
  {
    score -= 5;
  }
  
  
  if (score <= 25) // The many outputs depending on the amount of points the user got
  {
    cout << "We probably would not be friends" << endl;
  }
  else if (score <= 50)
  {
    cout << "We would be friends" << endl;
  }
  else if (score < 80)
  {
    cout << "We would be good friends" << endl;
  }
  else
  {
    cout << "Wow! We are very similar, we would great friends" << endl;
  }

  return 0;
}