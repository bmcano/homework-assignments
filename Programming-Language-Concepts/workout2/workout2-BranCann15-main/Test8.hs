module Test8 where

import System.Exit
import Test
import Exercises

t = Test "p8" (p8 [0,1,0,1,0,1,2]) 3

main :: IO ()
main = runSolo t
      
