module Test where

import Control.Exception
import System.Exit

data Test a = Test String a a

-- check that x and y are equal, catching exceptions evaluating x
-- return Nothing if caught an exception, otherwise result of equality test
core :: Eq a => a -> a -> IO (Maybe Bool)
core x y = catch (evaluate x >>= (\ ex -> return $ Just $ ex == y))
             (\ e -> let _ = e :: SomeException in return Nothing)

-- use core, printing name of test
runSolo :: (Show a , Eq a) => Test a -> IO ()
runSolo (Test name x y) =
  do
    b <- core x y
    case b of
      Nothing ->
        putStrLn ("Unimplemented (or other exception).")
      Just True -> 
        putStrLn ("Passing.")
      Just False ->
        putStrLn ("Failing. Expected " ++ show y ++ ", computed " ++ show x)

      
runBatch :: (Show a , Eq a) => Test a -> IO ()
runBatch t@(Test name x y) =
  do
    putStr (name ++ ": ")
    runSolo t
