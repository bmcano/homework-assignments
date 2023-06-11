module Test12 where

import System.Exit
import Test
import Exercises

t = Test "p12" (p12 [1,2,3,4,5]) 120

main :: IO ()
main = runSolo t
      
