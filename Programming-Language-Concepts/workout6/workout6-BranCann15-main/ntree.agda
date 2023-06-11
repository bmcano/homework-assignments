module ntree where

open import lib

-- simple Tree type storing natural numbers
data Tree : Set where
  Node : ℕ → Tree → Tree → Tree
  Leaf : Tree

-- a few functions on trees, used in problems in some of the ind*agda files

-- flip left and right subtrees throughout a tree
mirror : Tree → Tree
mirror (Node x t1 t2) = Node x (mirror t2) (mirror t1)
mirror Leaf = Leaf

-- construct the perfect binary tree whose depth is the input number
perfect : ℕ → Tree
perfect zero = Leaf
perfect (suc n) = Node 1 (perfect n) (perfect n)

-- count the number of leaves in a tree
numLeaves : Tree → ℕ
numLeaves (Node x t t') = numLeaves t + numLeaves t'
numLeaves Leaf = 1

-- count the number of constructors (either Leaf or Node) in a Tree
size : Tree → ℕ
size (Node x t t') = 1 + size t + size t'
size Leaf = 1

-- apply a function to every number stored in a tree, returning the resulting tree
treeMap : (ℕ → ℕ) → Tree → Tree
treeMap f Leaf = Leaf
treeMap f (Node x t t') = Node (f x) (treeMap f t) (treeMap f t')