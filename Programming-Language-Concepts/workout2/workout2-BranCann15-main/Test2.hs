module Test2 where

import System.Exit
import Test
import Exercises

t = Test "p2" (p2 (+) 4) 8 

main :: IO ()
main = runSolo t
      
