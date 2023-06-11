module Test4 where

import System.Exit
import Test
import Basic

t = Test "p4" (p4 ('a','b') ('c','d')) ('a','b','c','d') 

main :: IO ()
main = runSolo t
      
