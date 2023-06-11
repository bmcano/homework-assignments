module ind4 where

open import lib
open import ntree

-- The number of leaves in a perfect tree of height n is 2 to the n.
--
-- I found I needed the +0 theorem from nat-thms.agda 
perfect-numLeaves : ∀(n : ℕ) → numLeaves (perfect n) ≡ 2 pow n
perfect-numLeaves zero = refl
perfect-numLeaves (suc n) rewrite perfect-numLeaves n | +0 (2 pow n) = refl


