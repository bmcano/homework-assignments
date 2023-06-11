module Test1 where

import System.Exit
import Test
import Exercises

t = Test "mirror" (mirror e) e1 

main :: IO ()
main = runSolo t
      
