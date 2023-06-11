{
module Tokens where
}

%wrapper "basic"

tokens :-

  ab*                         { TokenIt "p0" }
  c*d+                        { TokenIt "p1" }
  (e|f)+                      { TokenIt "p2" }
  [g-m][0-2]*                 { TokenIt "p3" }
  ['\"]([A-G\ ]*)['\"]        { TokenIt "p4" } -- (\'[A-G\ ]*\')|(\"[A-G\ ]*\")
  (Y|X)+Y?                    { TokenIt "p5" }
  
  $white+                       ;
{
-- The token type:
data Token = TokenIt String {- which problem the string is for -} String
           deriving (Eq)

instance Show Token where
  show (TokenIt name str) = name ++ "(" ++ show str ++ ")"
}
