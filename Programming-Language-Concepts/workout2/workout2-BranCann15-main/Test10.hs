module Test10 where

import System.Exit
import Test
import Exercises

t = Test "p10" (p10 length even [[1..2],[1..3],[1..4],[1..5]]) [[1..2],[1..4]]

main :: IO ()
main = runSolo t
      
