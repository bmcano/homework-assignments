module bools5 where

open import lib

combK : ∀ x y → x imp (y imp x) ≡ tt
combK tt tt = refl
combK tt ff = refl
combK ff tt = refl
combK ff ff = refl


