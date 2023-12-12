Option 2: algorithms on CFGs and PDAs

Author: Brandon Cano

Tooling: 
 - Python v3.9.13
 - Python 'graphviz' library, found at https://pypi.org/project/graphviz/

Note: I did fill out the course evaluation

Sources: 
 - cfg.py: This file holds the datatype for the context free grammar (FCG)
 - pda.py: This file holds the datatype for the pushdown automata (PDA) also includes the function to make the graphviz pdf image
 - main.py: This file has the different functions related to the assignment and the driving force of the program
 - TESTING.txt: holds all the test results for the `translate_cfg_to_pda()` and `convert_cfg_to_cnf()` functions
 - doctest-output (dir): this directory holds all the outputs for the printing PDA function (referenced in the TESTING.txt)

Required functions:
 - CFG datatype (cfg.py):
   - For this datatype we have 4 attributes, a set of nonterminals and another of terminals. The start start symbol which is a 
     nonterminal and the productions that are produced in a dictionay with the keys as nonterminals and the value being a list of the productions.
     This is an example of how the production is created  
        > grammar = cfg.CFG(
        >     non_terminals={'S'},
        >     terminals={'(',')'},
        >     start_symbol='S',
        >     productions={'S' : ['S S','( S )', ' ']}
        > )

 - PDA datatype (pda.py): 
   - For this datatype the attributes are based off of the 7-tuple discussed in class, some items here are represented as python lists,
     but act as sets, lists were used for the sake of most useful functions with the list object
     This is an example of how a PDA can be created
        > pda.PDA(
        >     states=['1','2','3'], 
        >     input_aplha=set(['ε','(',')']), 
        >     stack_aplha=set(['!','X']),
        >     transitions=[('1','2','ε, +!'),('2','2','(, +X'),('2','2','), -X'),('2','3','ε, -!')], # (from, to, label/transition)
        >     start_state='1',
        >     start_symbol='ε',
        >     final_states=set(['3'])
        > )

 - generate_graphviz_for_pda(): for question #3
   - this function starts by adding nodes from the states list with the circle shape
   - then with the transitions that we have setup in their paritcular way we set src and dstn and the label assoicated
   - we identify the starting state and add an extra transition to it
   - we identify the final state and add an extra circle around it
   - finally we render the the image as a pdf saved to the doctest-output folder
  
 - translate_cfg_to_pda():
   - To start with this function we set what we can for the PDA right away which is both the input and alphabet stacks, along with start and final states
   - Then from the algorithm in class we start off adding the inital set of states and transitions when converting to a PDA.
   - Then we go through each nonterminal in the from the productions and add each route for each individual production along with the proper labels
   - Then we go through and add the terminal states to complete the PDA and return a newly formed PDA datatype.

 - convert_cfg_to_cnf():
   - For this there is a lot of steps and items to check for, but to start we set up some inital values with the terminals and nonterminals.
   - Then for the sake of naming new nonterminals we remove the current nonterminal from the list so there aren't any duplicates
   - get_new_non_terminal(): This is a helper function which will grab the first new NT available and remove it from the current list
   - From the algorithm in class there are 3 main steps which are broken up into different sets of loops along with some more edge cases to take care of prior.
    # Preliminary Steps
    # - Whenever N → s B s’ and B → , add N → s s’
    # – Whenever N → B and B → s, add N → s
    # – Repeat until no change, then throw away the unit and epsilon productions
    # 1. Remove unit productions X → Y and ε-productions X →
    # 2. Replace terminal t with new nonterminal Tt, and add Tt → t
    # 3. Split X → Y1 ... Yk (for k > 2) into X → Y1 Z1 Z1 → Y2 Z2 ... Z(k-2) → Y(k-1) Yk
   - This is the order in which I tackle the steps, as the program currently stands it does not properly covered these rule: 
      1. Whenever N → B and B → s, add N → s
      2. remove unit productions
   - Aside from that most cases will give the corrected output

Status:
 - generate_graphviz_for_pda(): works as intended, only slight annoyance is labeles overlapping but that is a fault of the library.
 - translate_cfg_to_pda(): works as intended, no known bugs
 - convert_cfg_to_cnf(): mostly works as intended, as explained above two small cases are not accounted for as I was unable to figure them out.
