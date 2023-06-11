module Test13 where

import System.Exit
import Test
import Basic

t = Test "p13" (p13 "abc") "abccba"

main :: IO ()
main = runSolo t
      
