module Test5 where

import System.Exit
import Test
import Exercises

t = Test "p5" (p5 (2,3) (* 10) fst) 20 

main :: IO ()
main = runSolo t
      
