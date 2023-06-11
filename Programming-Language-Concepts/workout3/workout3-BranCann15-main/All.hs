module All where

import Test
import qualified Test1
import qualified Test2
import qualified Test3
import qualified Test4
import qualified Test5
import qualified Test6
import qualified Test7
import qualified Test8
import qualified Test9

main :: IO()
main =
 do
  runBatch Test1.t
  runBatch Test2.t
  runBatch Test3.t
  runBatch Test4.t
  runBatch Test5.t
  runBatch Test6.t
  runBatch Test7.t
  runBatch Test8.t
  runBatch Test9.t
  
