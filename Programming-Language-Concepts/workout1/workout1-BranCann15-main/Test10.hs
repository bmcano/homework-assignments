module Test10 where

import System.Exit
import Test
import Basic

t = Test "p10" (p10 (1 / 3)) "1 / 3"

main :: IO ()
main = runSolo t
      
