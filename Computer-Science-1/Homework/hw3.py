# CS1210: HW3 version 1
######################################################################
# Complete the signed() function, certifying that:
#  1) the code below is entirely your own work, and
#  2) it has not been shared with anyone outside the intructional team.
#
def signed():
    return(["bmcano"])

######################################################################
# In this homework, you will build the internals for Boggle, a popular
# word game played with 16 6-sided dice. At the same time, in class we
# will develop the interactive user interface for Boggle, so that your
# solution, augmented with what we do in class, will give you a
# playable Boggle game. This assignment will also give us a chance to
# work on a system using the object-oriented paradigm.
#
# This is version 1 of the template file, which does not include the
# user interface.  I will periodically release updated versions, which
# you can then merge into your own code: still, be sure to follow the
# instructions carefully, so as to ensure your code will work with the
# new template versions that contain the GUI we develop in class.
#
# The rules of Boggle are available online. Basically, you will roll
# the dice and arrange them into a 4x4 grid. The top faces of the die
# will display letters, and your job is to find words starting
# anywhere in the grid using only adjacent letters (where "adjacent"
# means vertically, horizontally, and diagonally adjacent). In our
# version of Boggle, there are no word length constraints beyond those
# implicitly contained in the master word list.
#
# Although other dice configurations are possible, the original Boggle
# dice are (in no particular order):
D = ["aaeegn","abbjoo","achops","affkps","aoottw","cimotu","deilrx","delrvy",
     "distty","eeghnw","eeinsu","ehrtvw","eiosst","elrtty","himnqu","hlnnrz"]

# You will need sample() from the random module to roll the die.
from random import sample

######################################################################
# Boggle is the base class for our system; it is analogous to the
# Othello class in our implementation of that game.  It contains all
# the important data elements for the current puzzle, including:
#    Boggle.board = the current puzzle board
#    Boggle.words = the master word list
#    Boggle.solns = the words found in the current puzzle board
#    Boggle.lpfxs = the legal prefixes found in the current puzzle board
# Additional data elements are used for the GUI and scoring, which
# will be added in subsequent versions of the template file.
#
# Note: we will opt to use Knuth's 5,757 element 5-letter word list
# ('words.dat') from the Wordnet puzzle, but the 113,809 element list
# of words from HW1 ('words.txt') should also work just as easily.
#
class Boggle():
    ##################################################################
    # This is the class constructor. It should read in the specified
    # file containing the dictionary of legal words and then invoke
    # the play() method, which manages the game.
    #
    def __init__(self, input='words.dat'):              # self variables initalized here to know what we have
        self.words = []                                 # list of words from the file
        self.board = []                                 # 4x4 list of list for game board
        self.lpfxs = set()                              # set of legal prefixes from dictionary
        self.solns = set()                              # set of solutions for current puzzle

        Boggle.readwords(self, input)                   # initiates readwords
        Boggle.play(self)                               # initiates play

    ##################################################################
    # Printed representation of the Boggle object is used to provide a
    # view of the board in a 4x4 row/column arrangement.
    #
    def __repr__(self):
        # returns the board in a readable 4x4 format, str is used to not return a none type
        return(str('\n'.join(' '.join(str(col).upper() for col in row) for row in self.board)))

    ##################################################################
    # The readwords() method opens the file specified by filename,
    # reads in the word list converting words to lower case and
    # stripping any excess whitespace, and stores them in the
    # Boggle.words list.
    #
    def readwords(self, filename):
        file = open(filename, 'r')                      # opens and reads the file  
        # for each word in the file, it will lower it and remove any extra whitespace
        Boggle.words = self.words = [ word.lower().strip() for word in file ]         
        print("Read {} words.".format(len(self.words))) # prints how many words were read

        file.close()                                    # closes the file
        
    ##################################################################
    # The newgame() method creates a new Boggle puzzle by rolling the
    # dice and assorting them to the 4x4 game board. After the puzzle
    # is stashed in Boggle.board, the method also computes the set of
    # legal feasible word prefixes and stores this in Boggle.lpfxs.
    def newgame(self):
        diceroll = sample(D, len(D))                    # shuffles the dice
        # shuffles each string and takes the first (top) letter
        dice = [ sample(l, len(l))[0] for l in diceroll ]
        # puts together the 4x4 board in a matrix form 
        Boggle.board = self.board = [ [ d for d in dice[(i*4):4*(i+1)] ] for i in range(4) ]
        # creats the contents for the prefixes for every unique prefix ranging from 1-(len(word)) letters
        Boggle.lpfxs = self.lpfxs = { ''.join(word[:i]) for word in self.words for i in range(1, len(word)+1) }
        
        Boggle.solns = Boggle.solve(self)               # initalized Boggle.solns
        
    ##################################################################
    # The solve() method constructs the list of words that are legally
    # embedded in the given Boggle puzzle. The general idea is search
    # recursively starting from each of the 16 puzzle positions,
    # accumulating solutions found into a list which is then stored on
    # Boggle.solns.
    #
    # The method makes use of two internal "helper" functions,
    # adjacencies() and extend(), which perform much of the work.
    def solve(self): 
        # Helper function adjacencies() returns all legal adjacent
        # board locations for a given location loc. A board location
        # is considered legal and adjacent if (i) it meets board size
        # constraints (ii) is not contained in the path so far, and
        # (iii) is adjacent to the specified location loc.
        def adjacencies(loc, path):
            adj = []                                    # list for valid adjacent paths
            r, c = loc                                  # for readability r=loc[0], c=loc[1]
            for row in range(-1, 2):                    # range(-1, 2) is used because those will test the surround spots
                for col in range(-1, 2):
                    # this list is added just so the condition is not a huge line
                    conditions = [0 <= r+row < 4, 0 <= c+col < 4, (r+row, c+col) not in path]
                    path.append((r+row, c+col))         # add the adjacent spot to the path to test if its in the prefixes set  
                    # if the locations are on the board and the string is in the lpfxs its a valid spot
                    if all(conditions) and Boggle.extract(self, path) in self.lpfxs: 
                        adj.append((r+row, c+col))      # if so add to adjacent list
                    path.pop()                          # remove added loc to test the next adjacent path
                    
            return(adj) 

        # Helper function extend() is a recursive function that takes
        # a location loc and a path traversed so far (exclusive of the
        # current location loc). Together, path and loc specify a word
        # or word prefix. If the word is in Boggle.words, add it to
        # Boggle.solns, because it can be constructed within the
        # current puzzle. Otherwise, if the curren prefix is still in
        # Boggle.lpfxs, attempt to extend the current path to all
        # adjacencies of location loc. To do this efficiently, a
        # particular path extension is abandoned if the current prefix
        # is no longer contained in self.lpfxs, because that means
        # there is no feasible solution to this puzzle reachable via
        # this extension to the current path/prefix.
        def extend(loc, path=[]):
            path.append(loc)                            # start with empty path by default so we add the first loc value
            word = Boggle.extract(self, path)           # gather the 'word' or current string
            if word in self.words:                      # if it is a word then add it the list of words
                self.solns.add(word)
        
            adj = adjacencies(loc, path)                # then get all valid adjacent postions to current loc
            for i in adj:                               # iterate through all adjacent postitions with recursion
                extend(i, path)                         # once this path finishes remove the past loc in path and go to the
                path.pop()                              # next adjacent postition in the list

        # this will run through all 16 spots on the board all call extend for each spot         
        [ [ extend((row, col), path=[]) for col in range(4) ] for row in range(4) ]
        # print("***Cheat: {}".format(self.solns))                            
        print("Puzzle contains {} legal solutions.".format(len(self.solns)))
        return(self.solns)
    
    ##################################################################
    # The extract() method takes a path and returns the underlying
    # word from the puzzle board.
    #
    def extract(self, path):
        return(''.join([ self.board[l[0]][l[1]] for l in path ]))
    
    ##################################################################
    # The checkpath() method takes a path and returns the word it
    # represents if the path is legal (i.e., formed of distinct and
    # sequentially adjacent locations) and realizes a legal word,
    # False otherwise.
    #
    def checkpath(self, path):
        moves = []                                      # will be used to check for duplicate moves
        for i in range(1, len(path)):
            # helps make the conditionals more readable
            row1, row2 = path[i-1][0], path[i][0]       # row1 is the previous row, and row2 is the current one
            col1, col2 = path[i-1][1], path[i][1]       # col1 is the previous column, and col2 is the current one
            # check rows/columns to see if the difference is greater than 1 which means its not an adjacent spot
            if abs(row2 - row1) > 1 or abs(col2 - col1) > 1 or path[i] in moves:
                print("Illegal move: {}".format(path))
                return(False)
        
            moves.append(path[i-1])                     # adds the previous move (row1, col1) to the list

        try:                                            # used to catch any out of range inputs by user
            word = Boggle.extract(self, path)
            if word in self.solns:                      # if solution exist then return the word
                return(word)
        except:                                         
            print("Illegal move: {}".format(path))      # if its out of range than print illgeal move
            return(False)                               # then return False
                
        print("Unrecognized word '{}'".format(word))    # if not print message
        return(False)                                   # then return False
    
    ##################################################################
    # The round() method plays a round (i.e., a single puzzle) of
    # Boggle. It should return True as long as the player is willing
    # to continue playing additional rounds of Boggle; when it returns
    # False, the Boggle game is over.
    #
    # Hint: Look to HW1's round() function for inspiration.
    #
    # This method will be replaced by an interactive version.
    #
    def round(self):
        # The recover() helper function converts a list of integers
        # into a path. Thus '3 2 2 1 1 2 2 3' becomes [(3, 2), (2, 1),
        # (1, 2), (2, 3)].
        def recover(path):
            # return a list of tuples and converts to int from str
            return([ (int(path[(i*2)]), int(path[1+(i*2)])) for i in range(int(len(path)/2)) ])
        
        if len(self.solns) == 0: return(True)           # checks for null solutions and makes a new puzzle if true            
            
        wFound = []                                     # list of words found
        print("Input 'r1 c1 r2 c2...'; '/'=display, ':'=show, '+'=new puzzle; '.'=quit\n\twhere 'r1 c1 r2 c2...' specifies a path as series of row, col coordinates.")
        print(Boggle.__repr__(self))                     # show the board 
        
        while True:                                     # game loop
            move = input("Boggle> ")                    # user input 
            
            if move == '/':                             # show current board
                print(Boggle.__repr__(self))
                continue                                # used to avoid error with recover and checkpath
                
            if move == ':':                             # show words found so far
                print("{} words found so far".format(len(wFound)))
                [ print(" {}".format(i)) for i in wFound ] 
                continue
                
            if move == '+':                             # new puzzle
                print("You found {} of {} possible words.\n".format(len(wFound), len(self.solns)))
                self.solns = set()                      # we need to clear the solns
                return(True)
            
            if move == '.':                             # quit game
                print("You found {} of {} possible words.".format(len(wFound), len(self.solns)))
                return(False)
              
            path = recover(move.split())                # after checking for commands convert input to valid move type
            legal = Boggle.checkpath(self, path)        # legal will either be False or a "valid" string
            
            if legal and legal not in wFound:           # word will be added if in self.solns
                print("'{}' added to list.".format(legal))
                wFound.append(legal)
                                                        # if word has been found
            elif legal in wFound: print("'{}' has already been found.".format(legal))
                
            if len(wFound) == len(self.solns):          # if all words are found go to new puzzle
                print("\nYou found all {} possible words.\n".format(len(self.solns)))
                return(True)
            
    ##################################################################
    # The play() method when invoked initiates a sequence of
    # individual Boggle rounds by repeatedly invoking the rounds()
    # method as long as the user indicates they are interested in
    # playing additional puzzles.
    #
    # Hint: Look to HW1's play() function for inspiration.
    #
    # This method will be replaced by an interactive version.
    #
    def play(self):
        print("\nWelcome to Boggle!\n")                 # intro to program
        while True:
            Boggle.newgame(self)                        # will create a new board
            if not Boggle.round(self): break            # this controls the rounds and when the game ends
                                 
        print("\nThanks for Playing!\n")                # ending message

######################################################################
if __name__ == '__main__':
    Boggle()
