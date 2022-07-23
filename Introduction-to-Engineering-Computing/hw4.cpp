/*------------------------------------
Name: Brandon Cano
Date: 10/16/20
File Name: HW4
Assignment Description: Dice roll game
--------------------------------------*/

#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

//functions
int diceRoll(); //rolls the dice
void displayDice(int diceDisp); //determines which dice face to display
int score(); //calculates the score for both players in each round
int game(); //the main part of the code for the game

//variables
int PoneScore = 0; //Player One Score
int PtwoScore = 0; //Player Two Score
int pScore; //Player Score 
int rScore; //Round Score
int roll; //Rolls the dice
int dOne; //Die One
int dTwo; //Die Two
int dThree; //Die Three
int reroll; //Rerolling for each die
int rerollQ; //Question for Rerolling

int main() //main function
{
  
  srand(time(0)); //sets a seed
  
  cout << "Player one will go first" << endl;
  for (int i = 0; i < 3; i++) //for loops makes the game as many rounds as need in this case its 3
  {
    //player 1 turn
    cout << " \n|-------Player One's Turn-------|\n " << endl;
    PoneScore += game(); //calls for game function
    //player 2 turn
    cout << " \n|-------Player Two's Turn-------|\n " << endl;
    PtwoScore += game(); //calls for game function 
    
    //displays the current score for both people at the end of each round
    cout << "Player one currently has a score of " << PoneScore << endl;
    cout << "Player two currently has a score of " << PtwoScore << endl;
  }
  
  //this determines who the winner is and if there is a tie
  if (PoneScore > PtwoScore)
  {
    cout << "Player 1 wins with a score of " << PoneScore << ", and player 2 got a score of " << PtwoScore << endl;
  }
  else if (PtwoScore > PoneScore)
  {
    cout << "Player 2 wins with a score of " << PtwoScore << ", and player 1 got a score of " << PoneScore << endl;
  }
  else
  {
    cout << "It was a tie. You both got a score of " << PoneScore << endl;
  }
  
  return 0;
}


int game() // used to reduce the amount of code and makes it less redundant
{
    rScore = 0; 
    //calls for the diceRoll function and then displays the dice
    dOne = diceRoll();
    dTwo = diceRoll();
    dThree = diceRoll();
    displayDice(dOne);
    displayDice(dTwo);
    displayDice(dThree);
    //this is the part that determines which dice to reroll, if any
    cout << "Would you like to re-roll any of your dice? (1=yes, 0=no)" << endl;
    cin >> rerollQ;
    
    if (rerollQ == 0)
    {
      //calls the score function to calculate the score
      rScore += score();
      return rScore;
    }
    
    if (rerollQ == 1)
    {   
      cout << "Would you like to reroll dice one? (1=yes, 0=no)" << endl;
      cin >> reroll;
      if (reroll == 1)
      {
        dOne = diceRoll();
      }
      
      cout << "Would you like to reroll dice two? (1=yes, 0=no)" << endl;
      cin >> reroll;
      if (reroll == 1)
      {
        dTwo = diceRoll();
      }
      
      cout << "Would you like to reroll dice three? (1=yes, 0=no)" << endl;
      cin >> reroll;
      if (reroll == 1)
      {
        dThree = diceRoll();
      }
      
      displayDice(dOne);
      displayDice(dTwo);
      displayDice(dThree);
      //calls the score function to calculate the score
      rScore += score();          
    } 
    return rScore;
}


int score()
{
  //all the calculations for each players score
  if (dOne == dTwo && dTwo == dThree) // checks to see if all three dice are the same
  {
    pScore = 20 + dOne;
    return pScore;
  }
  
  else if (dOne == dTwo || dOne == dThree) // checks to see if there is a two of a kind
  {
    pScore = 10 + dOne;
    return pScore;
  }
  else if (dTwo == dThree)
  {
    pScore = 10 + dTwo;
    return pScore;
  }
  else // if non of the 2 previous happen then it'll determine which was the highest value
  {
    if (dOne > dTwo && dOne > dThree)
    {
      pScore = dOne; 
    }
    else if (dTwo > dOne && dTwo > dThree)
    {
      pScore = dTwo;
    }
    else
    {
      pScore = dThree;
    } 
    return pScore;
  } 
}


int diceRoll()
{
  //random dice roll
  return roll = rand() % 6 + 1; 
}


void displayDice(int diceDisp)
{
  switch(diceDisp) // this is used for displaying the dice image
  {  
     case(1):
      cout << " ------- \n|       |\n|   *   |\n|       |\n ------- " << endl;
      break;
      
     case(2):
      cout << " ------- \n|     * |\n|       |\n| *     |\n ------- " << endl;
      break;
      
     case(3):
      cout << " ------- \n|     * |\n|   *   |\n| *     |\n ------- " << endl;
      break;
      
     case(4):
      cout << " ------- \n| *   * |\n|       |\n| *   * |\n ------- " << endl;
      break;
      
     case(5):
      cout << " ------- \n| *   * |\n|   *   |\n| *   * |\n ------- " << endl;
      break;
      
     case(6):
      cout << " ------- \n| *   * |\n| *   * |\n| *   * |\n ------- " << endl;
      break;        
  }  
}