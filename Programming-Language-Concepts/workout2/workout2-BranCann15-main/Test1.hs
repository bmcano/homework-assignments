module Test1 where

import System.Exit
import Test
import Exercises

t = Test "p1" (p1 not True) False 

main :: IO ()
main = runSolo t
      
