module ind3 where

open import lib
open import ntree

-- mirroring a tree twice gives you back the original tree
mirror-mirror : ∀ (t : Tree) → mirror (mirror t) ≡ t
mirror-mirror Leaf = refl
mirror-mirror (Node x t t1) rewrite mirror-mirror t | mirror-mirror t1 = refl


