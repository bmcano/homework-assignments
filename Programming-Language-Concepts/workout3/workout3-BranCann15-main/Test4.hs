module Test4 where

import System.Exit
import Test
import Exercises

t = Test "swap2" (swap2 [1,2,3,4,5,6,7]) [2,1,4,3,6,5,7]

main :: IO ()
main = runSolo t
      
