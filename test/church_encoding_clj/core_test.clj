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
