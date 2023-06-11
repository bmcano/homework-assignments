module Test12 where

import System.Exit
import Test
import Basic

t = Test "p12" (p12 [1,2,3,4,5]) [2,3,4]

main :: IO ()
main = runSolo t
      
