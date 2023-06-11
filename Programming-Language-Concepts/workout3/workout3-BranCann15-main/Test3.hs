module Test3 where

import System.Exit
import Test
import Exercises

t = Test "toListPost" (toListPost e) [2,3,4,3,1]

main :: IO ()
main = runSolo t
      
