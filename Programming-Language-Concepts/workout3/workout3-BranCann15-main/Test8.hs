module Test8 where

import System.Exit
import Test
import Exercises

t = Test "btSubtree" (btSubtree [False] e4) (Just $ Node 9 (sing 9) (sing 16))

main :: IO ()
main = runSolo t
      
