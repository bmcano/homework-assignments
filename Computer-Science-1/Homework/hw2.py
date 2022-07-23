# CS1210: HW2
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# Import randint and shuffle from random module.
from random import randint, shuffle

######################################################################
# createDeck() produces a new, cannonically ordered, 52 card deck
# using a nested comprehension. Providing a value less than 13
# produces a smaller deck, like the semi-standard 40 card 4 suit 1-10
# deck used in many older card games (including tarot cards). Here,
# we'll use it with default values.
#
def createDeck(N=13, S=('spades', 'hearts', 'clubs', 'diamonds')):
    return([ (v, s) for s in S for v in range(1, N+1) ])

######################################################################
# Construct the representation of a given card using special unicode
# characters for hearts, diamonds, clubs, and spades. The input is a
# legal card, c, which is a (v, s) tuple. The output is a 2 or
# 3-character string 'vs' or 'vvs', where 's' here is the unicode
# character corresponding to the four standard suites (spades, hearts,
# diamonds or clubs -- provided), and v is a 1 or 2 digit string
# corresponding to the integers 2-10 and the special symbols 'A', 'K',
# 'Q', and 'J'.
#
# Example:
#    >>> displayCard((1, 'spades'))
#    'A♠'
#    >>> displayCard((12, 'hearts'))
#    'Q♡'
#
def displayCard(c):
    suits = {'spades':'\u2660', 'hearts':'\u2661', 'diamonds':'\u2662', 'clubs':'\u2663'}
    symbols = {1:'A', 2:'2', 3:'3', 4:'4', 5:'5', 6:'6', 7:'7', 8:'8', 9:'9', 10:'10', 11:'J', 12:'Q', 13:'K'}
    return(symbols[c[0]] + suits[c[1]])             # will call both dictionaries for to display the card properly

######################################################################
# Print out an indexed representation of the state of the table:
# foundation piles are numbered 0-3, corner piles 4-7.
#
# Example:
#   >>> showTable(F, C)
#     F0: 9♡...9♡
#     F1: 2♢...2♢
#     F2: 7♡...7♡
#     F3: 8♡...8♡
#     C4:
#     C5:
#     C6:
#     C7:
# Or, mid-game:
#     F0: 8♣...A♢
#     F1: J♣...J♣
#     F2: A♠...A♠
#     F3: 
#     C4: K♡...K♡
#     C5: 
#     C6: 
#     C7:
#
def showTable(F, C):                                # if/else list comprehension to print foundation piles
    [ print("  F{}: {}...{}".format(i, displayCard(F[i][0]), displayCard(F[i][-1]))) if F[i] != [] else print("  F{}:".format(i)) for i in range(4) ]
                                                    # if/else list comprehension to print corner piles
    [ print("  C{}: {}...{}".format(i+4, displayCard(C[i][0]), displayCard(C[i][-1]))) if C[i] != [] else print("  C{}:".format(i+4)) for i in range(4) ]
                               
######################################################################
# Print out an indexed list of the cards in input list H, representing
# a hand. Entries are numbered starting at 8 (indexes 0-3 are reserved
# for foundation piles, and 4-7 are reserved for corners). The
# indexing is used to select cards for play.
#
# Example:
#   >>> showHand(H[0])
#   Hand: 8:A♢ 9:4♢ 10:3♡ 11:5♠ 12:6♠ 13:7♠ 14:8♠
#   >>> showHand(H[1])
#   Hand: 8:9♣ 9:5♢ 10:8♢ 11:9♢ 12:10♡ 13:A♠ 14:4♠
#
def showHand(H):
    print("Hand:", end='')                          # runs through the length of the hand and prints it out using list comphrension
    [ print(" {}:{}".format(8++c, displayCard(H[c])), end='') for c in range(len(H)) ]                         
                                 
######################################################################
# We'll use deal(N, D) to set up the game. Given a deck (presumably
# produced by createDeck()), shuffle it, then deal 7 cards to each of
# N players, and seed the foundation piles with 4 additional cards.
# Returns D, H, F, where D is what remains of the deck, H is a list of
# N 7-card "hands", and F is a list of lists corresponding to the four
# "seeded" foundation piles.
# 
# Example:
#   >>> D, H, F = deal(2, D)
#   >>> len(D)
#   34
#   >>> len(H)
#   2
#   >>> H[0][:3]
#   [(5, 'clubs'), (12, 'clubs'), (3, 'diamonds')]
#   >>> F[2]
#   [(11, 'hearts')]
#
def deal(N, D): 
    shuffle(D)                                      # shuffles the deck
    H = [ [] for i in range(N) ]                    # list with N number of hands
    F = [ [] for i in range(4) ]                    # foundation piles
    
    for n in range(N):                              # deals the cards to players
        [ H[n].append(D[i]) for i in range(7) ]                              
        D = D[7:]                                   # removes the cards from the deck

    [ F[i].append(D[i]) for i in range(4) ]         # lays the foundation piles      
    D = D[4:]
 
    return(D, H, F)

######################################################################
# Returns True if card c can be appended to stack S. To be legal, c
# must be one less in value than S[-1], and should be of the "other"
# color (red vs black).
#
# Hint: Remember, S might be empty, in which case the answer should
# not be True.
#
# Hint: Use the encapsulated altcolor(c1, c2) helper function to check
# for alternating colors.
#
# Example:
#   >>> legal([(2, 'diamonds')], (1, 'spades'))
#   True
#   >>> legal([(2, 'diamonds')], (1, 'hearts'))
#   False
#
def legal(S, c):                                    # red:(diamonds, hearts), black:(spades, clubs)
    if S == []:                                     # if S is empty than return False
        return(False)
    
    def altcolor(c1, c2):
        if c1 in ["diamonds", "hearts"] and c2 in ["diamonds", "hearts"]:
            return(False)                           # checks if the cards are both red
        if c1 in ["spades", "clubs"] and c2 in ["spades", "clubs"]:
            return(False)                           # checks if the cards are both black
        return(True)                                # otherwise, return True
    
    if S[-1][0] - 1 == c[0]:                        # checks to see if the number decends
        return(altcolor(S[-1][1], c[1]))            # returns the output of altcolor()
    return(False)       
    
######################################################################
# Governs game play for N players (2 by default). This function sets
# up the game variables, D, H, F and C, then chooses the first player
# randomly from the N players. By convention, player 0 is the user,
# while all other player numbers are played by the auto player.
#
# Each turn, the current player draws a card from the deck D, if any
# remain, and then is free to make as many moves as he/she chooses. 
#
# Hint: fill out the remainder of the function, replacing the pass
# statements and respecting the comments.
# 
def play(N=2):
    D, H, F = deal(N, createDeck())                 # sets up the game
    C = [ [] for i in range(4) ]                    # corners, initially empty

    player = randint(0, N-1)                        # randomly chooses which player goes first
    print('Player {} moves first.'.format(player))
   
    while True:                                     # start the play loop; we'll need to exit explicitly
        if D != []:                                 # when termination conditions are realized
            H[player].append(D[0])
            D.remove(D[0])

        print('\n\nPlayer {} ({} cards) to move.'.format(player, len(H[player])))
        print('Deck has {} cards left.'.format(len(D)))
        showTable(F, C)                             # show the table
       
        if player != 0:                             # let the current player have a go
            automove(F, C, H[player])
        else:
            usermove(F, C, H[player])

        if H[player] == []:                         # check to see if player is out of cards; if so, end the game
            print('\n\nPlayer {} wins!'.format(player))
            showTable(F, C)
            break

        if player == N-1: 
            player = 0                              # otherwise, go on to next player
        else: 
            player += 1
                 
######################################################################
# Prompts a user to play their hand.  See transcript for sample
# operation.
#
def usermove(F, C, hand):
    # valid() is an internal helper function that checks if the index
    # i indicates a valid F, C or hand index. To be valid, it cannot
    # be an empty pile or an out-of-range hand index. Remember, 0-3
    # are foundation piles, 4-7 are corner piles, and 8 and up index
    # into the hand.
    def valid(i):
        if 0 <= i-8 < len(hand):                    # if the index exist for the hand it'll return True
            return(True)
        return(False)
        
    hand.sort()                                     # sorts the deck                                                    
    print('Enter your move as "src dst": press "/" to refresh display; "." when done')
    while True:                                     # manages any number of moves
        move = []       
                                    
        while not move or not valid(move[0]) or not valid(move[1]):
            showHand(hand)                          # display current hand
            move = input("Your move? ").split()     # reads input and puts it into a list
            if len(move) == 1:
                if move[0] == '.':                  # ends the turn for the player
                    return(F, C, hand)
                
                elif move[0] == '/':                # shows the table
                    showTable(F, C)
                    break
                
            try:
                move = [int(move[0]), int(move[1])] # execute the command, which looks like [from, to]
                
                # for each type of move there are a few different conditons that exist
                # first, we check where the range of numbers fall to deteremind which of the 6 types of moves could occur
                # then, in most cases we will check the cards being played through the legal function.
                # if the pile is empty when trying to play a card onto it there are different conditions that exist. 
                # like for the corner piles, it checks to see if the card being played is a king in order to be valid
                # the last main condition is the valid function to make sure the input from the hand is legal
                
                # this checks to see if the move is an open foundation pile and will not check legal
                if move[0] >= 8 and 0 <= move[1] <= 3 and F[move[1]] == [] and valid(move[0]):
                    print('Moving {} to an open foundation'.format(displayCard(hand[move[0]-8])))
                    F[move[1]].append(hand[move[0]-8])    
                    hand.pop(move[0]-8)                # this checks to see if the card being played can be stacked on to a non-empty foundation pile


                elif move[0] >= 8 and 0 <= move[1] <= 3 and valid(move[0]) and legal(F[move[1]], hand[move[0]-8]):
                    print('Moving {} to F{}'.format(displayCard(hand[move[0]-8]), move[1]))
                    F[move[1]].append(hand[move[0]-8])
                    hand.pop(move[0]-8)

                # this checks to see if the move can put a foundation pile to another foundation pile
                elif 0 <= move[0] <= 3 and 0 <= move[1] <= 3 and legal(F[move[1]], F[move[0]][0]):
                    print('Moving F{} to F{}'.format(move[0], move[1]))
                    [ F[move[1]].append(F[move[0]][i]) for i in range(len(F[move[0]])) ]        
                    F[move[0]].clear()

                # this checks to see if the move is an open corner pile and will check to see if the card is a king   
                elif move[0] >= 8 and 4 <= move[1] <= 7 and valid(move[0]) and C[move[1]-4] == [] and hand[move[0]-8][0] == 13:
                    print('Moving {} to an open corner'.format(displayCard(hand[move[0]-8])))
                    C[move[1]-4].append(hand[move[0]-8])
                    hand.pop(move[0]-8) 
                
                # this checks to see if the card being played can be stacked on to a non-empty corner pile
                elif C[move[1]-4] != [] and valid(move[0]) and legal(C[move[1]-4], hand[move[0]-8]):
                    print('Moving {} to C{}'.format(displayCard(hand[move[0]-8]), move[1])) 
                    C[move[1]-4].append(hand[move[0]-8])
                    hand.pop(move[0]-8)    

                # this checks to see if the move can put a foundation pile to corner pile 
                elif 0 <= move[0] <= 3 and 4 <= move[1] <= 7 and (legal(C[move[1]-4], F[move[0]][0]) or F[move[0]][0][0] == 13):
                    print('Moving F{} to C{}'.format(move[0], move[1]))
                    [ C[move[1]-4].append(F[move[0]][i]) for i in range(len(F[move[0]])) ]
                    F[move[0]].clear()
   
                else: print('Illegal move')         # otherwise, print "Illegal move" warning
                       
            except:                                 # any failure to process ends up here
                print('Ill-formed move {}'.format(move))    

            if not hand:                            # if the hand is empty, return 
                return
            move = []                               # otherwise, reset move and keep trying

######################################################################
# Plays a hand automatically using a fixed but not particularly
# brilliant strategy. The strategy involves consolidating the table
# (to collapse foundation and corner piles), then scanning cards in
# your hand from highest to lowest, trying to place each card. The
# process is repeated until no card can be placed. See transcript for
# an example.
#
def automove(F, C, hand):
    moved = True                                    # keep playing cards while you're able to move something
    while moved:
        moved = False                               # change back to True if a card is moved 
        consolidate(F, C)                           # starts by consolidating the table
        hand.sort()                                 # sorts the hand 
        for i in range(len(hand)-1, -1, -1):        # scans cards in hand from high to low value
            stopper = False                         # this is used to skip the second for loop if a king is played
            for c in C:                             # finds the first emtpy corner available 
                if c == [] and hand[i][0] == 13:    # if current card is a king, place in an empty corner
                    print("Moving {} to an open corner".format(displayCard(hand[i])))
                    c.append(hand[i])
                    hand.pop()
                    moved, stopper = True, True
                    break                           # once the king is played break from loop

            if stopper: continue                    # then continue to the next card in the hand

            for j in range(4):                      # otherwise, try to place current card on an existing corner or foundation pile
                # place current card on corner pile
                if C[j] != [] and legal(C[j], hand[i]):
                    print("Moving {} to C{}".format(displayCard(hand[i]), j+4))
                    C[j].append(hand[i])
                    
                # place current card on foundation pile
                elif F[j] != [] and legal(F[j], hand[i]):  
                    print("Moving {} to F{}".format(displayCard(hand[i]), j))
                    F[j].append(hand[i])
                   
                # start a new foundation pile
                elif F[j] == []:
                    print("Moving {} to an open foundation".format(displayCard(hand[i])))
                    F[j].append(hand[i])
                        
                else: continue                      # if no condition is meet this will continue the for loop
                                                    # so that it doesn't run the following three lines
                hand.remove(hand[i])                # this prevents redundant code so it this does not need to
                moved = True                        # repeated three times
                break

######################################################################
# consolidate(F, C) looks for opportunities to consolidate by moving a
# foundation pile to a corner pile or onto another foundation pile. It
# is used by the auto player to consolidate elements on the table to
# make it more playable.
#
# Example:
#   >>> showTable(F, C)
#     F0: 6♢...6♢
#     F1: 10♣...10♣
#     F2: J♡...J♡
#     F3: Q♠...Q♠
#     C4: K♢...K♢
#     C5:
#     C6:
#     C7:
#   >>> consolidate(F, C)
#   >>> showTable(F, C)
#     F0: 6♢...6♢
#     F1:
#     F2: 
#     F3: 
#     C4: K♢...10♣
#     C5:
#     C6:
#     C7:
#
def consolidate(F, C):
    for f in range(4):                              # consider moving one foundation(f) onto another foundation(f1) or corner(f1)
        for f1 in range(4):        
            if len(F[f]) > 0 and len(F[f1]) > 0 and legal(F[f1], F[f][0]):
               print('Moving F{} to F{}'.format(f, f1))              
               [ F[f1].append(F[f][i]) for i in range(len(F[f])) ]             
               F[f].clear()                         # moving a foundation pile(f) to another foundation pile(f1)
                           
            elif len(F[f]) > 0 and len(C[f1]) > 0 and legal(C[f1], F[f][0]):
                print('Moving F{} to C{}'.format(f, f1+4)) 
                [ C[f1].append(F[f][i]) for i in range(len(F[f])) ]                  
                F[f].clear()                        # moving a foundation pile(f) to a corner pile(f1)
                
            elif C[f1] == [] and F[f] != [] and F[f][0][0] == 13:
                print('Moving F{} to an open corner'.format(f)) 
                [ C[f1].append(F[f][i]) for i in range(len(F[f])) ]                  
                F[f].clear()                        # moving a foundation pile(f) to an empty corner pile(f1)

    return(F, C)

######################################################################
if __name__ == '__main__':
    play(2)                                         # play two-player version by default
