module Test6 where

import System.Exit
import Test
import Basic

t = Test "p6" (p6 "can") "cancan"

main :: IO ()
main = runSolo t
      
