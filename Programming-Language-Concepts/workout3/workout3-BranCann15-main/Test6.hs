module Test6 where

import System.Exit
import Test
import Exercises

t = Test "btDrop" (btDrop 2 e) [Leaf,Leaf,sing 3,sing 4]

main :: IO ()
main = runSolo t
      
