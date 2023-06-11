module Test5 where

import System.Exit
import Test
import Basic

t = Test "p5" (p5 (10,20,30,40)) ((10,20),(30,40)) 

main :: IO ()
main = runSolo t
      
