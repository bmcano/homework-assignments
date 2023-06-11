module Test16 where

import System.Exit
import Test
import Exercises

t = Test "p16" (zipWith ($) (p16 [not,id,not] [not,not,id]) [True,True,True]) [True,False,False]

main :: IO ()
main = runSolo t
      
