module Test14 where

import System.Exit
import Test
import Exercises

t = Test "p14" (p14 [1,2,3]) [[1,2,3],[2,3],[3],[]]

main :: IO ()
main = runSolo t
      
