module Test5 where

import System.Exit
import Test
import Exercises

t = Test "knit" (knit [1,3,5,7,9] [2,4,6]) [1,2,3,4,5,6,7,9]

main :: IO ()
main = runSolo t
      
