module Test11 where

import System.Exit
import Test
import Basic

t = Test "p11" (p11 [1,2] [3,4,5] [6,7,8,9]) [1..9]

main :: IO ()
main = runSolo t 
      
