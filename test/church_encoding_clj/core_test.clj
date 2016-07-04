(ns church-encoding-clj.core-test
  (:require [clojure.test :refer :all]
            [church-encoding-clj.core :refer :all]))

(deftest zero-test
  (testing "zero is a function that corresponds to 0"
    (is (= (church->integer zero) 0))))

(deftest one-test
  (testing "one is a function that corresponds to 1"
    (is (= (church->integer one) 1))))

(deftest two-test
  (testing "two is a function that corresponds to 2"
    (is (= (church->integer two) 2))))

(deftest three-test
  (testing "three is a function that corresponds to 3"
    (is (= (church->integer three) 3))))

(deftest successor-test
  (testing "successor is a function that takes an argument and increments it by 1"
    (is (= (church->integer (successor zero)) 1))
    (is (= (church->integer (successor one)) 2))
    (is (= (church->integer (successor two)) 3))
    (is (= (church->integer (successor three)) 4))
    (is (not (= (church->integer (successor zero)) 4)))
    (is (not (= (church->integer (successor three)) 0)))))

(deftest predecessor-test
  (testing "predecessor is a function that takes an argument and decrements it by 1"
    (is (= (church->integer (predecessor one)) 0))
    (is (= (church->integer (predecessor two)) 1))
    (is (= (church->integer (predecessor three)) 2))
    (is (= (church->integer (predecessor (successor three))) 3))))

(deftest integer->church-test
  (testing "integer->church"
    (is (= (church->integer (integer->church 0)) 0))
    (is (= (church->integer (integer->church 1)) 1))
    (is (= (church->integer (integer->church 2)) 2))
    (is (= (church->integer (integer->church 3)) 3))))

(deftest addition-test
  (testing "addition"
    (is (= (church->integer ((addition zero) zero)) 0))
    (is (= (church->integer ((addition zero) one)) 1))
    (is (= (church->integer ((addition zero) two)) 2))
    (is (= (church->integer ((addition zero) three)) 3))
    (is (= (church->integer ((addition one) one)) 2))
    (is (= (church->integer ((addition one) two)) 3))
    (is (= (church->integer ((addition one) three)) 4))
    (is (= (church->integer ((addition two) two)) 4))
    (is (= (church->integer ((addition two) three)) 5))
    (is (= (church->integer ((addition three) three)) 6))))

(deftest subtraction-test
  (testing "subtraction"
    (is (= (church->integer ((subtraction zero) zero)) 0))
    (is (= (church->integer ((subtraction one) one)) 0))
    (is (= (church->integer ((subtraction two) two)) 0))
    (is (= (church->integer ((subtraction three) three)) 0))
    (is (= (church->integer ((subtraction three) zero)) 3))
    (is (= (church->integer ((subtraction three) one)) 2))
    (is (= (church->integer ((subtraction three) two)) 1))
    (is (= (church->integer ((subtraction two) zero)) 2))
    (is (= (church->integer ((subtraction two) one)) 1))
    (is (= (church->integer ((subtraction one) zero)) 1))))

(deftest multiplication-test
  (testing "multiplication"
    (is (= (church->integer ((multiplication zero) zero)) 0))
    (is (= (church->integer ((multiplication zero) one)) 0))
    (is (= (church->integer ((multiplication zero) two)) 0))
    (is (= (church->integer ((multiplication zero) three)) 0))
    (is (= (church->integer ((multiplication one) one)) 1))
    (is (= (church->integer ((multiplication one) two)) 2))
    (is (= (church->integer ((multiplication one) three)) 3))
    (is (= (church->integer ((multiplication two) two)) 4))
    (is (= (church->integer ((multiplication two) three)) 6))
    (is (= (church->integer ((multiplication three) three)) 9))))

(deftest exponentiation-test
  (testing "exponentiation"
    (is (= (church->integer ((exponentiation one) one)) 1))
    (is (= (church->integer ((exponentiation one) two)) 1))
    (is (= (church->integer ((exponentiation one) three)) 1))
    (is (= (church->integer ((exponentiation two) one)) 2))
    (is (= (church->integer ((exponentiation two) two)) 4))
    (is (= (church->integer ((exponentiation two) three)) 8))
    (is (= (church->integer ((exponentiation three) one)) 3))
    (is (= (church->integer ((exponentiation three) two)) 9))
    (is (= (church->integer ((exponentiation three) three)) 27))))

(deftest true-function-test
  (testing "true is a function that takes two parameters and always chooses the first one"
    (is (= ((true-function true) false) true))))

(deftest false-function-test
  (testing "false is a function that takes two parameters and always chooses the second one"
    (is (= ((false-function true) false) false))))

(deftest is-zero?-test
  (testing "is-zero?"
    (is (= (is-zero? zero) true-function))
    (is (= (is-zero? one) false-function))
    (is (= (is-zero? two) false-function))
    (is (= (is-zero? three) false-function))
    (is (= (is-zero? (successor three)) false-function))))

(deftest or-function-test
  (testing "or"
    (is (= ((or-function true-function) true-function) true-function))
    (is (= ((or-function true-function) false-function) true-function))
    (is (= ((or-function false-function) true-function) true-function))
    (is (= ((or-function false-function) false-function) false-function))))

(deftest and-function-test
  (testing "and"
    (is (= ((and-function true-function) true-function) true-function))
    (is (= ((and-function true-function) false-function) false-function))
    (is (= ((and-function false-function) true-function) false-function))
    (is (= ((and-function false-function) false-function) false-function))))

(deftest not-function-test
  (testing "not"
    (is (= (not-function true-function) false-function))
    (is (= (not-function false-function) true-function))))

(deftest nor-function-test
  (testing "nor"
    (is (= ((nor-function true-function) true-function) false-function))
    (is (= ((nor-function true-function) false-function) false-function))
    (is (= ((nor-function false-function) true-function) false-function))
    (is (= ((nor-function false-function) false-function) true-function))))

(deftest nand-function-test
  (testing "nand"
    (is (= ((nand-function true-function) true-function) false-function))
    (is (= ((nand-function true-function) false-function) true-function))
    (is (= ((nand-function false-function) true-function) true-function))
    (is (= ((nand-function false-function) false-function) true-function))))

(deftest xor-function-test
  (testing "xor"
    (is (= ((xor-function true-function) true-function) false-function))
    (is (= ((xor-function true-function) false-function) true-function))
    (is (= ((xor-function false-function) true-function) true-function))
    (is (= ((xor-function false-function) false-function) false-function))))

(deftest xnor-function-test
  (testing "xnor"
    (is (= ((xnor-function true-function) true-function) true-function))
    (is (= ((xnor-function true-function) false-function) false-function))
    (is (= ((xnor-function false-function) true-function) false-function))
    (is (= ((xnor-function false-function) false-function) true-function))))

(deftest is-less-or-equal?-test
  (testing "is-less-or-equal?"
    (is (= ((is-less-or-equal? zero) zero) true-function))
    (is (= ((is-less-or-equal? zero) one) true-function))
    (is (= ((is-less-or-equal? zero) two) true-function))
    (is (= ((is-less-or-equal? zero) three) true-function))
    (is (= ((is-less-or-equal? one) zero) false-function))
    (is (= ((is-less-or-equal? one) one) true-function))
    (is (= ((is-less-or-equal? one) two) true-function))
    (is (= ((is-less-or-equal? one) three) true-function))
    (is (= ((is-less-or-equal? two) zero) false-function))
    (is (= ((is-less-or-equal? two) one) false-function))
    (is (= ((is-less-or-equal? two) two) true-function))
    (is (= ((is-less-or-equal? two) three) true-function))
    (is (= ((is-less-or-equal? three) zero) false-function))
    (is (= ((is-less-or-equal? three) one) false-function))
    (is (= ((is-less-or-equal? three) two) false-function))
    (is (= ((is-less-or-equal? three) three) true-function))))

(deftest is-greater-or-equal?-test
  (testing "is-greater-or-equal?"
    (is (= ((is-greater-or-equal? zero) zero) true-function))
    (is (= ((is-greater-or-equal? zero) one) false-function))
    (is (= ((is-greater-or-equal? zero) two) false-function))
    (is (= ((is-greater-or-equal? zero) three) false-function))
    (is (= ((is-greater-or-equal? one) zero) true-function))
    (is (= ((is-greater-or-equal? one) one) true-function))
    (is (= ((is-greater-or-equal? one) two) false-function))
    (is (= ((is-greater-or-equal? one) three) false-function))
    (is (= ((is-greater-or-equal? two) zero) true-function))
    (is (= ((is-greater-or-equal? two) one) true-function))
    (is (= ((is-greater-or-equal? two) two) true-function))
    (is (= ((is-greater-or-equal? two) three) false-function))
    (is (= ((is-greater-or-equal? three) zero) true-function))
    (is (= ((is-greater-or-equal? three) one) true-function))
    (is (= ((is-greater-or-equal? three) two) true-function))
    (is (= ((is-greater-or-equal? three) three) true-function))))

(deftest is-less?-test
  (testing "is-less?"
    (is (= ((is-less? zero) zero) false-function))
    (is (= ((is-less? zero) one) true-function))
    (is (= ((is-less? zero) two) true-function))
    (is (= ((is-less? zero) three) true-function))
    (is (= ((is-less? one) zero) false-function))
    (is (= ((is-less? one) one) false-function))
    (is (= ((is-less? one) two) true-function))
    (is (= ((is-less? one) three) true-function))
    (is (= ((is-less? two) zero) false-function))
    (is (= ((is-less? two) one) false-function))
    (is (= ((is-less? two) two) false-function))
    (is (= ((is-less? two) three) true-function))
    (is (= ((is-less? three) zero) false-function))
    (is (= ((is-less? three) one) false-function))
    (is (= ((is-less? three) two) false-function))
    (is (= ((is-less? three) three) false-function))))

(deftest is-greater?-test
  (testing "is-greater?"
    (is (= ((is-greater? zero) zero) false-function))
    (is (= ((is-greater? zero) one) false-function))
    (is (= ((is-greater? zero) two) false-function))
    (is (= ((is-greater? zero) three) false-function))
    (is (= ((is-greater? one) zero) true-function))
    (is (= ((is-greater? one) one) false-function))
    (is (= ((is-greater? one) two) false-function))
    (is (= ((is-greater? one) three) false-function))
    (is (= ((is-greater? two) zero) true-function))
    (is (= ((is-greater? two) one) true-function))
    (is (= ((is-greater? two) two) false-function))
    (is (= ((is-greater? two) three) false-function))
    (is (= ((is-greater? three) zero) true-function))
    (is (= ((is-greater? three) one) true-function))
    (is (= ((is-greater? three) two) true-function))
    (is (= ((is-greater? three) three) false-function))))

(deftest are-equal?-test
  (testing "are-equal?"
    (is (= ((are-equal? zero) zero) true-function))
    (is (= ((are-equal? zero) one) false-function))
    (is (= ((are-equal? zero) two) false-function))
    (is (= ((are-equal? zero) three) false-function))
    (is (= ((are-equal? one) zero) false-function))
    (is (= ((are-equal? one) one) true-function))
    (is (= ((are-equal? one) two) false-function))
    (is (= ((are-equal? one) three) false-function))
    (is (= ((are-equal? two) zero) false-function))
    (is (= ((are-equal? two) one) false-function))
    (is (= ((are-equal? two) two) true-function))
    (is (= ((are-equal? two) three) false-function))
    (is (= ((are-equal? three) zero) false-function))
    (is (= ((are-equal? three) one) false-function))
    (is (= ((are-equal? three) two) false-function))
    (is (= ((are-equal? three) three) true-function))))
