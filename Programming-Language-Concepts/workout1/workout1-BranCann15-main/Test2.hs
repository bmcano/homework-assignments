module Test2 where

import System.Exit
import Test
import Basic

t = Test "p2" (p2 (1,2)) (2,1)

main :: IO ()
main = runSolo t
      
