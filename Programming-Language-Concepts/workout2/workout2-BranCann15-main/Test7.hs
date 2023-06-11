module Test7 where

import System.Exit
import Test
import Exercises

t = Test "p7" (p7 [1..9]) [2,4,6,8]

main :: IO ()
main = runSolo t
      
