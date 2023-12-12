class CFG:
    def __init__(self, non_terminals, terminals, start_symbol, productions):
        self.non_terminals = non_terminals # set(string)
        self.terminals = terminals         # set(string)
        self.start_symbol = start_symbol   # string
        self.productions = productions     # dictionary {string : list(string)}
