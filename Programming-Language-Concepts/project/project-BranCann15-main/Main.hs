module Main where

import System.Environment ( getArgs )
import Control.Monad ()
import Data.List ( sortBy )
import Data.Char ( digitToInt, intToDigit )
import Numeric ( showIntAtBase )

-- Define different commonly used types.
type Connection = (Int, Int)
type Network = [(Int, Int)]
type ParallelNetwork = [[(Int, Int)]]

-- writeConnection -> This will take a Connection and put it in the "x -- y" string format.
writeConnection :: Connection -> String
writeConnection (x, y) = show (min x y) ++ " -- " ++ show (max x y)

-- writeNetwork -> This will take a Connection and will add the newline character for the whole network.
writeNetwork :: Connection -> String
writeNetwork (x, y) = writeConnection (x, y) ++ "\n"

-- replaceElement -> Replace an element in a list, at index i, with new element j.
replaceElement :: [Int] -> Int -> Int -> [Int]
replaceElement xs i j = do
    let (x, _:ys) = splitAt i xs
    x ++ [j] ++ ys

-- swapElements -> Swap two elements in a sequence, determined by a network connection. 
swapElements :: [Int] -> Connection -> [Int]
swapElements zs (x, y) = do
    let maxVal = max (zs!!(x - 1)) (zs!!(y - 1))
    let minVal = min (zs!!(x - 1)) (zs!!(y - 1))
    replaceElement (replaceElement zs (x - 1) minVal) (y - 1) maxVal

-- sortSequence -> Iterate through a Network to sort a sequence.
sortSequence :: Network -> [Int] -> [Int]
sortSequence fs xs = foldl swapElements xs fs

{- 
 - constructParallelNetwork -> First Network is unparallel form of network. Second Network is temporary network to add parallel elements to. 
 - A parallel network is added to and returned in base case.
 - If initial network is empty, add temporary network and return.
 - If the first element in the network can be run in parallel with all other elements of temporary network, add it to temporary network.
 - Otherwise add temporary network and continue.
 -} 
constructParallelNetwork :: Network -> Network -> ParallelNetwork -> ParallelNetwork
constructParallelNetwork [] n2 p = p ++ [n2]
constructParallelNetwork [e] n2 p
    | isParallelMatch e n2 = p ++ [n2 ++ [e]]
    | otherwise = p ++ [n2] ++ [[e]]
constructParallelNetwork (n:n1) n2 p
    | isParallelMatch n n2 = constructParallelNetwork n1 (n2 ++ [n]) p
    | otherwise = constructParallelNetwork n1 [n] (p ++ [n2])

-- isParallelMatch -> Check if connection can be run in parallel to all elements of a network.
isParallelMatch :: Connection -> Network -> Bool
isParallelMatch a [] = True
isParallelMatch (x1,y1) ((x2,y2):ns) = (x1 /= x2) && (x1 /= y2) && (y1 /= x2) && (y1 /= y2) && isParallelMatch (x1,y1) ns

-- sortNetworkByFirstIndex -> Sort every network element by the first element in its tuple.
sortNetworkByFirstIndex :: Network -> Network
sortNetworkByFirstIndex = sortBy (\(a,_) (b,_) -> compare a b) 

-- writeParallelNetwork -> This will take an item of the ParallelNetwork and put it in the parallel string format.
writeParallelNetwork :: Network -> String
writeParallelNetwork [] = ""
writeParallelNetwork [p] = writeConnection p ++ "\n"
writeParallelNetwork (p:ps) = writeConnection p ++ " , " ++ writeParallelNetwork ps

-- writeSortedNetwork -> Wrapper that sorts the networks by their first element before they're written 
-- EX: outputs 1--4 , 2--3, rather than 2--3 , 1--4
writeSortedNetwork :: Network -> String
writeSortedNetwork n = writeParallelNetwork (sortNetworkByFirstIndex n)

-- numberOfWires -> Computes the number of wires a Network has.
numberOfWires :: Network -> Int
numberOfWires network = maximum (concatMap (\(x, y) -> [x, y]) network)

-- intToBin -> Take an Int and returns a list of the binary digits representing an Int.
intToBin :: (Integral a, Show a) => a -> [Int]
intToBin x = map digitToInt (showIntAtBase 2 intToDigit x "")

-- isSortingNetwork -> Take in a Network and determine if it is a sorting network.
isSortingNetwork :: Network -> Bool
isSortingNetwork [] = True
isSortingNetwork x = sortingNetworkHelper (filter (\y -> length y == numberOfWires x) (map intToBin [0..(2 ^ length x)])) x

-- sortingNetworkHelper -> Takes list of lists of 0's and 1's, and a network, and checks that each run through the network produces a sorted result.
sortingNetworkHelper :: [[Int]] -> Network -> Bool
sortingNetworkHelper [] n = True
sortingNetworkHelper (x:xs) n
    | sorted (sortSequence n x) = sortingNetworkHelper xs n
    | otherwise = False

-- sorted -> Determines if a list of orderable elements is sorted.
sorted :: (Ord a) => [a] -> Bool
sorted [] = True
sorted [x] = True
sorted (x:y:xs) = (x <= y) && sorted (y:xs)

-- main program
main :: IO ()
main = do
    args <- getArgs
    case args of
        [] -> fail "Run with one command-line argument"
        "Read":filename:_ -> do
            network <- readFile filename
            writeFile "network.txt" (foldMap writeNetwork (read network :: Network))
        "Run":filename:sequence:_ -> do
            network <- readFile filename
            print (sortSequence (read network :: Network) (read sequence :: [Int]))
        "Parallel":filename:_ -> do
            network <- readFile filename
            writeFile "parallel.txt" (foldMap writeSortedNetwork (constructParallelNetwork (read network :: Network) [] [[]]))
        "Sorting":filename:_ -> do
            network <- readFile filename
            print (isSortingNetwork (read network :: Network))
