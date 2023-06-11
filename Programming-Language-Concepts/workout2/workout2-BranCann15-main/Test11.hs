module Test11 where

import System.Exit
import Test
import Exercises

t = Test "p11" (p11 length [[1..2],[1..3],[1..4]]) [([1..2],2),([1..3],3),([1..4],4)]

main :: IO ()
main = runSolo t 
      
