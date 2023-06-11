module Test8 where

import System.Exit
import Test
import Basic

t = Test "p8" (p8 "the pond" "frozen") "the pond is frozen"

main :: IO ()
main = runSolo t
      
