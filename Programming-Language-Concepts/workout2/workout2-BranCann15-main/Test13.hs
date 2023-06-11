module Test13 where

import System.Exit
import Test
import Exercises

t = Test "p13" (p13 1 [[2]]) [[1,2],[2]]

main :: IO ()
main = runSolo t
      
