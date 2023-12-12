import cfg
import pda

# Brandon Cano
# CS:4330
# Final Project: Option 2

"""
 4. Write a function that can translate a CFG into a PDA
"""
def translate_cfg_to_pda(cfg):
    # set the initial staes of every attribute for a pda
    pda_states = []
    pda_input_alpha = set(cfg.terminals)
    pda_stack_alpha = set(cfg.non_terminals)
    pda_transitions = []
    pda_start_state = '1'
    pda_start_symbol = ''
    pda_final_states = set(['4'])

    # set the inital set of transitions and states from in-class algorithm
    pda_states.append(pda_start_state)
    pda_states.append('2')
    pda_states.append('3')
    pda_states.append('4')
    pda_transitions.append((pda_start_state, '2', '•, +!'))
    pda_transitions.append(('2', '3', '•, +S'))
    pda_transitions.append(('3', '4', '•, -!'))

    # iterate through each key, and for each value go through each production
    # productions={'S' : ['A b','b A'], 'A' : ['a','C A C'], 'C' : ['a','b']}
    state_count = 4
    for s in cfg.productions.keys(): # [S, A, C] this is the value to substract
        for production in cfg.productions[s]: # ['A b','b A'] these are the values to add
            if production == ' ': continue
            pda_transitions.append(('3', str(state_count+1), f'•, -{s}'))
            state_count += 1
            chars = production.split(" ")
            for c in range(len(chars)-1): # [A, b] ignore last item
                pda_transitions.append((str(state_count), str(state_count+1), f'•, +{chars[c]}'))
                state_count += 1
            pda_transitions.append((str(state_count), '3', f'•, +{chars[-1]}'))
    # add terminal states
    for terminal in cfg.terminals:
        pda_transitions.append(('3', '3', f'{terminal}, -{terminal}'))

    return pda.PDA(
        pda_states, pda_input_alpha, pda_stack_alpha, pda_transitions,
        pda_start_state, pda_start_symbol, pda_final_states
    )

"""
 5. Write a function to transform a grammar into Chomsky normal form
"""
def convert_cfg_to_cnf(grammar):
    new_pairings = {}
    non_terminals = list(map(chr, range(65, 91))) # uppercase A-Z
    terminals = grammar.terminals
    productions = grammar.productions.copy()
    # for new nonterminals naming purposes, we will remove already existing terminals
    for p in productions.keys():
        if p in non_terminals:
            non_terminals.remove(p)

    # helper function for new nonterminal names
    def get_new_non_terminal():
        nt = non_terminals[0]
        non_terminals.remove(nt)
        return nt

    # Preliminary Steps
    # - Whenever N → s B s’ and B → , add N → s s’
    # nested loop checking each production
    # checking if the nt, t, nt pattern appears, then update productions
    for p in productions.keys():
        new_production = productions[p].copy()
        for i in range(len(productions[p])):
            g = productions[p][i].split(" ") # '( S )'
            if len(g) == 3 and g[0] in terminals and g[1] not in terminals and g[2] in terminals: 
                new_production.append(f'{g[0]} {g[2]}')
        grammar.productions.update({p: new_production})

    # 1. Remove unit productions X → Y and ε-productions X →
    # nested loops checking each production
    # check for a ε-productions, if one is found remove it, then update productions
    productions = grammar.productions.copy()
    for p in productions.keys():
        new_production = productions[p].copy()
        for i in range(len(productions[p])):
            g = productions[p][i] # 'A b'
            if g == ' ':
                new_production.remove(g)
        grammar.productions.update({p: new_production})

    # 2. Replace terminal t with new nonterminal Tt, and add Tt → t
    # nested loops checking each production
    # for each charater in a production 'A b' we check if it is a terminal
    # and if this char has not been found yet we add a new NT and production for it
    # then we update the productions
    productions = grammar.productions.copy()
    for p in productions.keys():
        new_production = productions[p].copy() # ['A b', ...]
        for i in range(len(productions[p])):
            g = productions[p][i].split(" ") # 'A b' -> ['A', 'b']
            for j in range(len(g)):
                c = g[j]
                if c in terminals: # ['A', 'b'] -> ['A', 'N'], N : ['b']
                    if c not in new_pairings.keys():
                        nt = get_new_non_terminal()
                        new_pairings.update({c: nt})
                    else:
                        nt = new_pairings[c]
                    g[j] = nt
                    new_production[i] = " ".join(g)
                    grammar.productions.update({nt: [c]})
        grammar.productions.update({p: new_production})

    # 3. Split X → Y1 ... Yk (for k > 2) into X → Y1 Z1 Z1 → Y2 Z2 ... Z(k-2) → Y(k-1) Yk
    # nested loops for each production
    # while loop for the length being 3 or more
    # we split the production [A1, A2, A3, ..., Ak] into smaller parts while adding a new NT 
    # continue this pattern until the length is 3 or less
    # then update productions
    productions = grammar.productions.copy()
    for p in productions.keys():
        new_production = productions[p].copy()
        for i in range(len(productions[p])):
            g = productions[p][i].split(" ")
            while len(g) >= 3: # [A1, A2, A3, ..., Ak]
                nt = get_new_non_terminal()
                s1 = f'{g[0]} {nt}'
                new_production[i] = s1
                del g[0]
                grammar.productions.update({nt: [" ".join(g)]})
        grammar.productions.update({p: new_production})

    return grammar

if __name__ == '__main__':
    # Inclass Oct 10th:
    grammar1 = cfg.CFG(
        non_terminals={'S'},
        terminals={'(',')'},
        start_symbol='S',
        productions={'S' : ['S S','( S )', ' ']}
    )
    # Kozen Example 21.4
    grammar2 = cfg.CFG(
        non_terminals={'S'},
        terminals={'a','b'},
        start_symbol='S',
        productions={'S' : ['a S b', ' ']}
    )
    # Kozen Example 21.5
    grammar3 = cfg.CFG(
        non_terminals={'S'},
        terminals={'[',']'},
        start_symbol='S',
        productions={'S' : ['[ S ]', 'S S', ' ']}
    )
    # Inclass Oct 12th
    grammar4 = cfg.CFG(
        non_terminals={'S','A','C'},
        terminals={'a','b'},
        start_symbol='S',
        productions={'S' : ['A b',' '], 'A' : ['a','C A C'], 'C' : ['a','b']}
    )
    # Shows current fault of the program (from slides)
    grammar5 = cfg.CFG(
        non_terminals={'N','B'},
        terminals={'s'},
        start_symbol='N',
        productions={'N' : ['B'], 'B' : ['s']}
    ) # Should be {'N': ['s']}

    # from slides
    balanced_parens = cfg.CFG(
        non_terminals={'E'},
        terminals={'(',')'},
        start_symbol='E',
        productions={'E' : ['E E','( E )', '( )']}
    )
    example = cfg.CFG(
        non_terminals={'E'},
        terminals={'\'+\''},
        start_symbol='E',
        productions={'E' : ['E \'+\' E']}
    )

    print("\nStep 3: Display a PDA as a GraphViz image (check under /doctest_output/ for graphs)")
    print("Step 4: Converting CFGs to PDAs\n")

    pda1 = translate_cfg_to_pda(grammar4) # in class example
    print(grammar4.productions)
    pda2 = pda.PDA(
        states=['1','2','3'], 
        input_aplha=set(['ε','(',')']), 
        stack_aplha=set(['⊥','X']),
        transitions=[('1','2','ε, +L'),('2','2','(, +X'),('2','2','), -X'),('2','3','ε, -L')],
        start_state='1',
        start_symbol='ε',
        final_states=set(['3'])
    )
    pda3 = translate_cfg_to_pda(example) # from slides
    pda4 = pda.PDA(
        states=['1','2','3', '4'], 
        input_aplha=set(['ε','a','b']), 
        stack_aplha=set(['⊥','X']),
        transitions=[('1','2','ε, +L'),('2','2','a, +X'),('2','3','ε, *'),('3','3','b, -X'), ('3','4','ε, -L')],
        start_state='1',
        start_symbol='ε',
        final_states=set(['4'])
    )
    
    pda1.generate_graphviz_for_pda("pda1")
    pda2.generate_graphviz_for_pda("pda2")
    pda3.generate_graphviz_for_pda("pda3")
    pda4.generate_graphviz_for_pda("pda4")
    
    print("\nStep 5: Converting CFGs to CNF\n")
    grammar_list = [
        grammar1,
        grammar2,
        grammar3,
        grammar4,
        grammar5,
    ]
    for grammar in grammar_list:
        print(f"Grammer: {grammar.productions}")
        cnf = convert_cfg_to_cnf(grammar)
        print(f" In CNF: {cnf.productions}")