module Test9 where

import System.Exit
import Test
import Exercises

t = Test "bstInsert" (bstInsert 8 bst) bst1

main :: IO ()
main = runSolo t
      
