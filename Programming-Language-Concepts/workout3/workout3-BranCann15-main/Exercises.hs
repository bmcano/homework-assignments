{- These problems are generally expected to require pattern-matching and
   recursion.  See Inclass.hs for week3 on ICON for related examples.
-}
module Exercises(module Exercises,module BinTree) where
import BinTree

{- the output tree should be just like the input
   tree, except that the left and right subtrees of
   every node have been switched. -}
mirror :: BinTree a -> BinTree a
mirror (Node x Leaf Leaf) = Node x Leaf Leaf
mirror (Node x l r) = Node x (mirror r) (mirror l)

{- implement a function similar to map for lists, but working on BinTrees.
   In more detail: btMap takes in a function from a to b, and should apply
   that function to all the values stored in the input BinTree, to obtain
   the output BinTree. -}
btMap :: (a -> b) -> BinTree a -> BinTree b
btMap _ Leaf = Leaf
btMap f (Node x l r) = Node (f x) (btMap f l) (btMap f r)

{- return the data stored in the BinTree, using a
   postfix traversal (so the value stored at each
   node appears after values in its subtrees).  I
   will go over some similar code, which you can
   find in the Inclass.hs file for week3 on ICON,
   on Tuesday, Feb. 7. -}
toListPost :: BinTree a -> [a]
toListPost Leaf = []
toListPost (Node x l r) = toListPost l ++ toListPost r ++ [x]

{- swap the first and second elements of the list, then
   repeat.  When you get to a list with just one value
   or the empty list, return that list. -}
swap2 :: [a] -> [a]
swap2 [] = []
swap2 [x] = [x]
swap2 (x:y:xs) = y:x:swap2 xs

{- combine the two lists by taking one element from the first,
   then an element from the second, and so forth.  If one list
   ends before the other, return the other. -}
knit :: [a] -> [a] -> [a]
knit xs [] = xs
knit [] xs = xs
knit (x:xs) (y:ys) = x:y:knit xs ys

{- return all the subtrees you find at the given depth,
   which you may assume is greater than or equal to zero.

   At depth 0, you should return a list containing the input tree.

   For depth greater than 0, if the input tree is a Leaf, return
   the empty list.
-}
btDrop :: Int -> BinTree a -> [BinTree a]
btDrop 0 t = [t]
btDrop d Leaf = []
btDrop d (Node x l r) = btDrop (d-1) l ++ btDrop (d-1) r

{- this is like zipWith on lists.
   The given function should be applied to corresponding values
   at Nodes.  If one tree has a Node and the other has a Leaf,
   just return Leaf. -}
btZipWith :: (a -> b -> c) -> BinTree a -> BinTree b -> BinTree c
btZipWith _ _ Leaf = Leaf
btZipWith _ Leaf _ = Leaf
btZipWith f (Node x1 l1 r1) (Node x2 l2 r2) = Node (f x1 x2) (btZipWith f l1 l2) (btZipWith f r1 r2)

{- We can represent paths into a tree as a list of booleans.  Each
   Bool indicates whether we should recurse into the left subtree (True) or
   the right one (False).  Given a path and a tree, return the subtree found
   by following the path.  If the tree ends in a leaf before the path ends,
   then return Nothing. -}
btSubtree :: [Bool] -> BinTree a -> Maybe (BinTree a)
btSubtree [] t = Just t
btSubtree _ Leaf = Nothing
btSubtree (False:bs) (Node _ _ r) = btSubtree bs r
btSubtree (True:bs) (Node _ l _) = btSubtree bs l

{- insert the given element of type a into the given BinTree,
   viewed as a binary search tree.  That is, you may assume
   that in any subtree (Node y l r), all data in l are less than
   or equal to y, and all data in r are greater than y.
   You should insert the new element into the tree in a way
   that maintains this property: insert to the left if the new element
   is less than or equal to y, and otherwise insert to the right.
   If you encounter a Leaf you just create a new singleton tree with
   the new element.

   If you forgot about binary search trees a little, just search online
   for "binary search tree insert". -}
bstInsert :: Ord a => a -> BinTree a -> BinTree a
bstInsert x Leaf = Node x Leaf Leaf
bstInsert x (Node y l r)
   | x <= y = Node y (bstInsert x l) r
   | otherwise = Node y l (bstInsert x r)