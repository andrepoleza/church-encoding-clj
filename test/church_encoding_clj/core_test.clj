(ns church-encoding-clj.core-test
  (:require [midje.sweet :refer :all]
            [church-encoding-clj.core :refer :all]))

(fact "zero is a function that corresponds to 0"
  (church->integer zero) => 0)

(fact "one is a function that corresponds to 1"
  (church->integer one) => 1)

(fact "two is a function that corresponds to 2"
  (church->integer two) => 2)

(fact "three is a function that corresponds to 3"
  (church->integer three) => 3)

(fact "successor is a function that takes an argument and increments it by 1"
  (church->integer (successor zero)) => 1
  (church->integer (successor one)) => 2
  (church->integer (successor two)) => 3
  (church->integer (successor three)) => 4
  (church->integer (successor zero)) =not=> 4
  (church->integer (successor three)) =not=> 0)

(fact "predecessor is a function that takes an argument and decrements it by 1"
  (church->integer (predecessor one)) => 0
  (church->integer (predecessor two)) => 1
  (church->integer (predecessor three)) => 2
  (church->integer (predecessor (successor three))) => 3)

(fact "integer->church"
  (church->integer (integer->church 0)) => 0
  (church->integer (integer->church 1)) => 1
  (church->integer (integer->church 2)) => 2
  (church->integer (integer->church 3)) => 3)

(fact "addition"
  (church->integer ((addition zero) zero)) => 0
  (church->integer ((addition zero) one)) => 1
  (church->integer ((addition zero) two)) => 2
  (church->integer ((addition zero) three)) => 3
  (church->integer ((addition one) one)) => 2
  (church->integer ((addition one) two)) => 3
  (church->integer ((addition one) three)) => 4
  (church->integer ((addition two) two)) => 4
  (church->integer ((addition two) three)) => 5
  (church->integer ((addition three) three)) => 6)

(fact "subtraction"
  (church->integer ((subtraction zero) zero)) => 0
  (church->integer ((subtraction one) one)) => 0
  (church->integer ((subtraction two) two)) => 0
  (church->integer ((subtraction three) three)) => 0
  (church->integer ((subtraction three) zero)) => 3
  (church->integer ((subtraction three) one)) => 2
  (church->integer ((subtraction three) two)) => 1
  (church->integer ((subtraction two) zero)) => 2
  (church->integer ((subtraction two) one)) => 1
  (church->integer ((subtraction one) zero)) => 1)

(fact "multiplication"
  (church->integer ((multiplication zero) zero)) => 0
  (church->integer ((multiplication zero) one)) => 0
  (church->integer ((multiplication zero) two)) => 0
  (church->integer ((multiplication zero) three)) => 0
  (church->integer ((multiplication one) one)) => 1
  (church->integer ((multiplication one) two)) => 2
  (church->integer ((multiplication one) three)) => 3
  (church->integer ((multiplication two) two)) => 4
  (church->integer ((multiplication two) three)) => 6
  (church->integer ((multiplication three) three)) => 9)

(fact "exponentiation"
  (church->integer ((exponentiation one) one)) => 1
  (church->integer ((exponentiation one) two)) => 1
  (church->integer ((exponentiation one) three)) => 1
  (church->integer ((exponentiation two) one)) => 2
  (church->integer ((exponentiation two) two)) => 4
  (church->integer ((exponentiation two) three)) => 8
  (church->integer ((exponentiation three) one)) => 3
  (church->integer ((exponentiation three) two)) => 9
  (church->integer ((exponentiation three) three)) => 27)

(fact "true is a function that takes two parameters and always chooses the first one"
  ((true-function true) false) => true)

(fact "false is a function that takes two parameters and always chooses the second one"
  ((false-function true) false) => false)

(fact "is-zero?"
  (is-zero? zero) => true-function
  (is-zero? one) => false-function
  (is-zero? two) => false-function
  (is-zero? three) => false-function
  (is-zero? (successor three)) => false-function)

(fact "or"
  ((or-function true-function) true-function) => true-function
  ((or-function true-function) false-function) => true-function
  ((or-function false-function) true-function) => true-function
  ((or-function false-function) false-function) => false-function)

(fact "and"
  ((and-function true-function) true-function) => true-function
  ((and-function true-function) false-function) => false-function
  ((and-function false-function) true-function) => false-function
  ((and-function false-function) false-function) => false-function)

(fact "not"
  (not-function true-function) => false-function
  (not-function false-function) => true-function)

(fact "nor"
  ((nor-function true-function) true-function) => false-function
  ((nor-function true-function) false-function) => false-function
  ((nor-function false-function) true-function) => false-function
  ((nor-function false-function) false-function) => true-function)

(fact "nand"
  ((nand-function true-function) true-function) => false-function
  ((nand-function true-function) false-function) => true-function
  ((nand-function false-function) true-function) => true-function
  ((nand-function false-function) false-function) => true-function)

(fact "xor"
  ((xor-function true-function) true-function) => false-function
  ((xor-function true-function) false-function) => true-function
  ((xor-function false-function) true-function) => true-function
  ((xor-function false-function) false-function) => false-function)

(fact "xnor"
  ((xnor-function true-function) true-function) => true-function
  ((xnor-function true-function) false-function) => false-function
  ((xnor-function false-function) true-function) => false-function
  ((xnor-function false-function) false-function) => true-function)

(fact "is-less-or-equal?"
  ((is-less-or-equal? zero) zero) => true-function
  ((is-less-or-equal? zero) one) => true-function
  ((is-less-or-equal? zero) two) => true-function
  ((is-less-or-equal? zero) three) => true-function
  ((is-less-or-equal? one) zero) => false-function
  ((is-less-or-equal? one) one) => true-function
  ((is-less-or-equal? one) two) => true-function
  ((is-less-or-equal? one) three) => true-function
  ((is-less-or-equal? two) zero) => false-function
  ((is-less-or-equal? two) one) => false-function
  ((is-less-or-equal? two) two) => true-function
  ((is-less-or-equal? two) three) => true-function
  ((is-less-or-equal? three) zero) => false-function
  ((is-less-or-equal? three) one) => false-function
  ((is-less-or-equal? three) two) => false-function
  ((is-less-or-equal? three) three) => true-function)

(fact "is-greater-or-equal?"
  ((is-greater-or-equal? zero) zero) => true-function
  ((is-greater-or-equal? zero) one) => false-function
  ((is-greater-or-equal? zero) two) => false-function
  ((is-greater-or-equal? zero) three) => false-function
  ((is-greater-or-equal? one) zero) => true-function
  ((is-greater-or-equal? one) one) => true-function
  ((is-greater-or-equal? one) two) => false-function
  ((is-greater-or-equal? one) three) => false-function
  ((is-greater-or-equal? two) zero) => true-function
  ((is-greater-or-equal? two) one) => true-function
  ((is-greater-or-equal? two) two) => true-function
  ((is-greater-or-equal? two) three) => false-function
  ((is-greater-or-equal? three) zero) => true-function
  ((is-greater-or-equal? three) one) => true-function
  ((is-greater-or-equal? three) two) => true-function
  ((is-greater-or-equal? three) three) => true-function)

(fact "is-less?"
  ((is-less? zero) zero) => false-function
  ((is-less? zero) one) => true-function
  ((is-less? zero) two) => true-function
  ((is-less? zero) three) => true-function
  ((is-less? one) zero) => false-function
  ((is-less? one) one) => false-function
  ((is-less? one) two) => true-function
  ((is-less? one) three) => true-function
  ((is-less? two) zero) => false-function
  ((is-less? two) one) => false-function
  ((is-less? two) two) => false-function
  ((is-less? two) three) => true-function
  ((is-less? three) zero) => false-function
  ((is-less? three) one) => false-function
  ((is-less? three) two) => false-function
  ((is-less? three) three) => false-function)

(fact "is-greater?"
  ((is-greater? zero) zero) => false-function
  ((is-greater? zero) one) => false-function
  ((is-greater? zero) two) => false-function
  ((is-greater? zero) three) => false-function
  ((is-greater? one) zero) => true-function
  ((is-greater? one) one) => false-function
  ((is-greater? one) two) => false-function
  ((is-greater? one) three) => false-function
  ((is-greater? two) zero) => true-function
  ((is-greater? two) one) => true-function
  ((is-greater? two) two) => false-function
  ((is-greater? two) three) => false-function
  ((is-greater? three) zero) => true-function
  ((is-greater? three) one) => true-function
  ((is-greater? three) two) => true-function
  ((is-greater? three) three) => false-function)

(fact "are-equal?"
  ((are-equal? zero) zero) => true-function
  ((are-equal? zero) one) => false-function
  ((are-equal? zero) two) => false-function
  ((are-equal? zero) three) => false-function
  ((are-equal? one) zero) => false-function
  ((are-equal? one) one) => true-function
  ((are-equal? one) two) => false-function
  ((are-equal? one) three) => false-function
  ((are-equal? two) zero) => false-function
  ((are-equal? two) one) => false-function
  ((are-equal? two) two) => true-function
  ((are-equal? two) three) => false-function
  ((are-equal? three) zero) => false-function
  ((are-equal? three) one) => false-function
  ((are-equal? three) two) => false-function
  ((are-equal? three) three) => true-function)

(fact "not-equal?"
  ((not-equal? zero) zero) => false-function
  ((not-equal? zero) one) => true-function
  ((not-equal? zero) two) => true-function
  ((not-equal? zero) three) => true-function
  ((not-equal? one) zero) => true-function
  ((not-equal? one) one) => false-function
  ((not-equal? one) two) => true-function
  ((not-equal? one) three) => true-function
  ((not-equal? two) zero) => true-function
  ((not-equal? two) one) => true-function
  ((not-equal? two) two) => false-function
  ((not-equal? two) three) => true-function
  ((not-equal? three) zero) => true-function
  ((not-equal? three) one) => true-function
  ((not-equal? three) two) => true-function
  ((not-equal? three) three) => false-function)
