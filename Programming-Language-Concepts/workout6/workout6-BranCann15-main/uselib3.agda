module uselib3 where

open import lib

{- This theorem states that (x + y)^2 is equal to the usual
   quadratic polynomial.

   Hint: _+_ is parsed left associatively, so if you see
   
   a + b + c

   please remember this is (a + b) + c.  This affects how you
   apply +assoc at the end to get the summands in exactly the
   same grouping. -}
poly : ∀(x y : ℕ) → square x + 2 * x * y + square y ≡ square (x + y)
poly x y rewrite +0 x | *distribr x y (x + y) | *distribr x x y | *distribl y x y | *distribl x x y | +assoc (x * x + x * y) (y * x) (y * y) | +assoc (x * x) (x * y) (x * y) | *comm x y = refl



