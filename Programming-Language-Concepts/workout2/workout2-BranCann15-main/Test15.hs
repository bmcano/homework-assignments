module Test15 where

import System.Exit
import Test
import Exercises

t = Test "p15" (p15 [1,2,3] [10,20,30]) [11,22,33] 

main :: IO ()
main = runSolo t
      
