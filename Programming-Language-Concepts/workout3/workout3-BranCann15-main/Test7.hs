module Test7 where

import System.Exit
import Test
import Exercises

t = Test "btZipWith" (btZipWith (*) e e) e4

main :: IO ()
main = runSolo t
      
