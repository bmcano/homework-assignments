module Test4 where

import System.Exit
import Test
import Exercises

t = Test "p4" (p4 (+) (* 10) 3) 33 

main :: IO ()
main = runSolo t
      
