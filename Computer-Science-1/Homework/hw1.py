# CS1210: HW1
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# In this homework, you will be implementing a spelling bee game,
# inspired by the that appears in the New York Times. The purpose of
# the game is to find as many possible words from a display of 7
# letters, where each word must meet the following criteria:
#   1. it must consist of four or more letters; and
#   2. it must contain the central letter of the display.
# So, for example, if the display looks like:
#    T Y
#   B I L
#    M A
# where I is the "central letter," the words "limit" and "tail" are
# legal, but "balmy," "bit," and "iltbma" are not.
#
# We'll approach the construction of our system is a step-by-step
# fashion; for this homework, I'll provide specs and function
# signatures to help you get started. If you stick to these specs and
# signatures, you should soon have a working system.
#
# First, we'll need a few functions from the random module. Read up on
# these at docs.python.org.
from random import choice, randint, sample

######################################################################
# fingerprint(W) takes a word, W, and returns a fingerprint of W
# consisting of an ordered set of the unique character constituents of
# the word. You have already encountered fingerprint(W) so I will
# provide the reference solution here for you to use elsewhere.
def fingerprint(W):
    return(''.join(sorted(set(W))))

######################################################################
# score(W) takes a word, W, and returns how many points the word is
# worth. The scoring rules here are straightforward:
#   1. four letter words are worth 1 point;
#   2. each additional letter adds 1 point up to a max of 9; and
#   3. pangrams (use all 7 letters in display) are worth 10 points.
# So, for example:
#      A L
#     O B Y
#      N E
#   >>> score('ball')
#   1
#   >>> score('balloon')
#   4
#   >>> score('baloney')
#   10     # Pangram!
#
def score(W):
    wScore = 0                          # score variable
    w = len(W)                          # used as a counter
    if len(fingerprint(W)) == 7:        # checks for pangrams
        return(10)
    while w > 3:                        # loops through to caclulate score
        wScore += 1
        w = w - 1
        if wScore == 9:                 # once max score is reached it breaks from loop to return score
            break
    return(wScore)  
    
"""if len(fingerprint(W)) == 7:
        return 10
   return(min(9,len(W)-3))"""

######################################################################
# jumble(S, i) takes a string, S, having exactly 7 characters and an
# integer index i where 0<=i<len(S). The string describes a puzzle,
# while i represents the index of S corresponding to the "central"
# character in the puzzle. This function doesn't return anything, but
# rather prints out a randomized representation of the puzzle, with
# S[i] at the center and the remaining characters randomly arrayed
# around S[i]. So, for example:
#    >>> jumble('abelnoy', 1)
#     A L
#    O B Y
#     N E
#    >>> jumble('abelnoy', 1)
#     N Y
#    L B A
#     E O
#
def jumble(S, i):
    center = S[i]                                   # takes center letter
    S = sample(S,len(S))                            # shuffles fingerprint
    S.remove(center)                                # removes the center letter after random shuffle
    print(" {} {} \n{} {} {}\n {} {} ".format(S[0],S[1],S[2],center,S[3],S[4],S[5]))
    
######################################################################
# readwords(filename) takes the name of a file containing a dictionary
# of English words and returns two values, a dictionary of legal words
# (those having 4 or more characters and fingerprints of 7 of fewer
# characters), with fingerprints as keys and values consisting of sets
# of words with that fingerprint, as well as a list, consisting of all
# of the unique keys of the dictionary having exactly 7 characters (in
# no particular order).
#
# Your function should provide some user feedback. So, for example:
#    >>> D,S=readwords('words.txt')
#    113809 words read: 82625 usable; 33830 unique fingerprints.
#    >>> len(S)
#    13333
#    >>> S[0]
#    'abemort'
#    >>> D[S[0]]
#    {'barometer', 'bromate'}
#
def readwords(filename):
    file = open(filename, 'r')                      # opens the file and reads it        
    n = 0
                                                    # uWord - list of usable words
    uWord, uFP, S = [], [], []                      # uFP - list of unique fingerprints 
                                                    # S - list of unique fingerprints that have a length of 7
    D = {}                                          # D - dictionary with fingerprints
               
    for i in file:                                  # runs through the list of each word from "words.txt"
        i = i.strip()
        n += 1
        if len(i) > 3 and len(fingerprint(i)) <= 7: # each words must meet requirements
            uWord += [i]                            
            uFP += [fingerprint(i)]

            if fingerprint(i) not in D.keys():      # this is for creating the dictionary
                D[fingerprint(i)] = {i}             # this sets the fingerprint and with the intial value
            else:
                wSet = D[fingerprint(i)]            # this will add any new words the the set of words for each key
                wSet.add(i)

            if len(fingerprint(i)) == 7:            # for fingerprint of length 7
                S += [fingerprint(i)]
                
    uFP, S = list(set(uFP)), list(set(S))           # removes any potential duplicates
    print("{} words read: {} usable; {} unique fingerprints.".format(n,len(uWord),len(uFP)))
    file.close()
                           
    return(D, S)
 
######################################################################
# round(D, S) takes two arguments, corresponding to the values
# returned by readwords(), randomly selects a puzzle seed from the
# list S and a central letter from within S. It then shows the puzzle
# and enters a loop where the user can:
#    1. enter a new word for scoring;
#    2. enter / to rescramble and reprint the puzzle;
#    3. enter + for a new puzzle; or
#    4. enter . to end the game.
# When a word is entered, it is checked for length (must be longer
# than 4 characters and its fingerprint must be contained within the
# puzzle seed). The word is then checked against D, and if found, is
# scored and added to the list of words.
#
# Here is a sample interactive transcript of round() in action:
#    >>> D,S = readwords('words.txt')
#    >>> round(D,S)
#     E H
#    R P U
#     O S
#    Input words, or: '/'=scramble; ':'=show; '+'=new puzzle; '.'=quit
#    sb> pose
#    Bravo! +1
#    sb> repose
#    Bravo! +3
#    sb> house
#    Word must include 'p'
#    sb> :
#    2 words found so far:
#      pose
#      repose
#    sb> /
#     H R
#    O P E
#     S U
#    sb> prose
#    Bravo! +2
#    sb> +
#    You found 3 of 415 possible words: you scored 6 points.
#    True
#
def round(D, S):                  
    rFingerprint = S[randint(0,len(S)-1)]           # randomly chooses fingerprint 
    rInt = randint(0,6)                             # randomly chooses the center letter
    jumble(rFingerprint, rInt)                      # jumbles the fingerprint
                         
    tScore, tWords = 0, 0                           # tScore - total score; tWords - total words that can be guessed
    wList = []                                      # list of correctly guessed words

    for i in D:                                     # this is what will calculate the total amount of 
        if rFingerprint[rInt] in i and set(i) <= set(rFingerprint):  # possible words for each puzzle  
            tWords += len(D[i])
        
    while True:                                     # gameplay
        command = input("Input words, or: '/'=scramble; ':'=show; '+'=new puzzle; '.'=quit\n")
    
        if command == '/':                          # scrambles the fingerprint
            jumble(rFingerprint, rInt) 
            
        elif command == ':':                        # shows list of correctly guessed words
            print("{} words found so far".format(len(wList)))
            [print(" {}".format(i)) for i in wList]         
               
        elif command == '+':                        # new puzzle
            print("You found {} of {} possible words: you scored {} points".format(len(wList),tWords,tScore))
            return(True) 
            
        elif command == '.':                        # quit game
            print("You found {} of {} possible words: you scored {} points".format(len(wList),tWords,tScore))
            return(False)
        
        elif tWords == len(wList):                  # checks to see if all words have been guessed
            print("\nCongrats, you have guessed all the words for this puzzle!\n")
            return(True) 
                                          
        elif len(command) < 4:                      # checks to see if word is long enough 
            print("words must be atleast 4 letters long")                        
                                                    # first it checks to see if center letter appears in user input                                                   
        elif rFingerprint[rInt] in command:         # then it checks if the fingerprint exist and if the word exist                                              
            if fingerprint(command) in D.keys() and command in D[fingerprint(command)]:
                if command not in wList:            # finally it checks to see if word has already been found
                   tScore += score(command)    
                   wList += [command]
                   print("Good job! +{}".format(score(command)))
                else:
                    print("You already found this word")      
            else:
                print("Unrecognized word")          # word either does not exist or can not be spelled from fingerprint
        else:
            print("Word must contain the center letter '{}'".format(rFingerprint[rInt]))
                  
######################################################################
# play(filename='words.txt') takes a single optional argument filename
# (defaults to 'words.txt') that gives the name of the file containing
# the dictionary of legal words. After invoking readwords(), it
# repeatedly invokes rounds() until it obtains a False, indicating the
# game is over.
#
def play(filename='words.txt'):
    print("\nWelcome to the word game!\n")          # intro to program
    
    D, S = readwords(filename)                      # gathers the dictionary and list from file
    key = True                  
    
    while key == True:                              # this controls the rounds and when the game ends
        key = round(D, S)
    
    print("\nThanks for Playing!\n")                # ending message

play()