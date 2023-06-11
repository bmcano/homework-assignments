module Test3 where

import System.Exit
import Test
import Basic

t = Test "p3" (p3 (10,20) (3,4)) (13,24) 

main :: IO ()
main = runSolo t
      
