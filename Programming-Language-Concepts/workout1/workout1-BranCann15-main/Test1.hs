module Test1 where

import System.Exit
import Test
import Basic

t = Test "p1" (p1 (1,2,3)) 6 

main :: IO ()
main = runSolo t
      
