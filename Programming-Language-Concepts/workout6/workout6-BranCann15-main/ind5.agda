module ind5 where

open import lib
open import ntree

-- the size of a perfect binary tree of depth n is (2 to the (n+1)) minus 1
-- 
-- I needed +0, +suc
perfect-size : ∀(n : ℕ) → suc (size (perfect n)) ≡ 2 pow (suc n)
perfect-size zero = refl
perfect-size (suc n) rewrite sym (+suc (size (perfect n)) (size (perfect n))) | sym (perfect-size n) | +0 (size (perfect n)) = refl 


