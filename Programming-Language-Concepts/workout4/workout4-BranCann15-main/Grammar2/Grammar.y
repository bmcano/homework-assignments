{
module Grammar where
import Tokens
import Prog
}

%name parseProg
%tokentype { Token }
%error { parseError }

%token
    '('  { TokenLParen }
    ')'  { TokenRParen }
    ';'  { TokenSemi }
    ','  { TokenComma }
    id   { TokenId $$ }
%%

Prog : id ';' { [Var $1] }                              -- a; -> [Var "a"]
| Prog id ';' { $1 ++ [Var $2]}                         -- [a; b; c;] -> [Var "a",Var "b",Var "c"]
| id '(' Prog ')' ';' { [(FunCall $1 $3)] }
| id '(' ')' ';' { [(FunCall $1 [])] }                  -- accounts for empty FunCall
| id { [Var $1] }
| id ',' Prog { [Var $1] ++ $3 }                        -- allows FunCalls with multiple variable inputs
| Prog id '(' Prog ')' ';' { $1 ++ [(FunCall $2 $4)] }  -- allows there to be programs before functions
| Prog id '(' ')' ';' { $1 ++ [(FunCall $2 [])] }       -- allows programs before empty functions
| id '(' ')' ',' Prog { [FunCall $1 []] ++ $5}          -- To handle the nexted case with empty parameters
| id '(' Prog ')'  ',' Prog { [FunCall $1 $3] ++ $6 }   -- To handle nested case with filled parameters
| id '(' ')' { [FunCall $1 []] }                        -- handle the empty function call in isolation case
| id '(' Prog ')' { [FunCall $1 $3] }                   -- handle funciton call with parameters in isolation


{

parseError :: [Token] -> a
parseError tks = error ("Parse error: " ++ show tks)

}
