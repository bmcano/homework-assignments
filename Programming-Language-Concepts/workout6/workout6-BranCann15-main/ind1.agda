module ind1 where

open import lib

----------------------------------------------------------------------
-- For the theorems in the files ind*agda, I expect you will need to
-- use proof by induction, possibly also using theorems in the IAL.
--
-- This first one is just based on the IAL, but the rest also use
-- ntree.agda in this directory.
--
-- Remember that once you have loaded the file with Control-c Control-l,
-- you can put your cursor on a symbol and hit Alt-. (the 'alt' key
-- followed by a dot) and it will just to the definition of the
-- symbol.
----------------------------------------------------------------------

-- first look up these functions in the IAL so you understand
-- what this theorem is saying.  The proof is a simple induction
-- on n.
thm1 : ∀(n : ℕ) → list-any iszero (repeat n 1) ≡ ff
thm1 zero = refl
thm1 (suc n) = thm1 n

