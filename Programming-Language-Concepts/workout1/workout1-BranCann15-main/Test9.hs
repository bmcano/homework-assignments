module Test9 where

import System.Exit
import Test
import Basic

t = Test "p9" (p9 1 3) "1 / 3"

main :: IO ()
main = runSolo t
      
