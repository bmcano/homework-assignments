module Test6 where

import System.Exit
import Test
import Exercises

t = Test "p6" (p6 (uncurry (-)) (3,5)) 2

main :: IO ()
main = runSolo t
      
