module Test9 where

import System.Exit
import Test
import Exercises

t = Test "p9" (p9 [1..9]) True

main :: IO ()
main = runSolo t
      
