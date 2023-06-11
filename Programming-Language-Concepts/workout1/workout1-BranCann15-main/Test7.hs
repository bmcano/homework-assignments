module Test7 where

import System.Exit
import Test
import Basic

t = Test "p7" (p7 'l' 'i' 'd') "lid"

main :: IO ()
main = runSolo t
      
