module ind2 where

open import lib
open import ntree 

-- treeMap, defined in ntree.agda, is like map on lists, but for trees.
-- This theorem states that mapping the identity function on a tree gives
-- you back the same tree.
-- 
-- Note that you can type λ by writing a backslash and then the
-- word 'lambda' (without the quotation marks)
treeMapId : ∀(t : Tree) → treeMap (λ x → x) t ≡ t
treeMapId Leaf = refl
treeMapId (Node x t t1) rewrite treeMapId t | treeMapId t1 = refl