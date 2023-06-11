module Test2 where

import System.Exit
import Test
import Exercises

t = Test "btMap" (btMap (* 10) e1) e2

main :: IO ()
main = runSolo t
      
