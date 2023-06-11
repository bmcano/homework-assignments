module Test3 where

import System.Exit
import Test
import Exercises

t = Test "p3" (p3 (+ 10) (1,2)) (11,12)

main :: IO ()
main = runSolo t
      
