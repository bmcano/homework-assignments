import graphviz

class PDA:
    # M = ( Q, Σ, Γ, R, E, s, F)
    def __init__(self, states, input_aplha, stack_aplha, transitions, start_state, start_symbol, final_states):
        self.states = states             # set
        self.input_aplha = input_aplha   # set
        self.stack_aplha = stack_aplha   # set
        self.transitions = transitions   # list[tuple(current_state, target_state, stack_operation)]
        self.start_state = start_state   # string
        self.start_symbol = start_symbol # string
        self.final_states = final_states # set
    
    """
     3. Write a printing function that can generate a GraphViz file for the transition diagram of a PDA
    """
    def generate_graphviz_for_pda(self, filename):
        dot = graphviz.Digraph(comment = 'PDA')
        # add states
        for state in self.states:
            dot.node(state, state, shape='circle')
        # add the transitions
        for transition in self.transitions:
            source, target, label = transition
            dot.edge(source, target, label=label)
        # show the starting state
        dot.node('Start', shape='point')
        dot.edge('Start', self.start_state, arrowhead='normal')
        # circle the final state
        for final_state in self.final_states:
            dot.node(final_state, final_state, shape='doublecircle')
        # displays left to right, rather than up to down
        dot.attr(rankdir='LR')
        dot.attr(layout='dot')
        print(dot.source)
        dot.render(f'doctest-output/{filename}.gv', view = False)
