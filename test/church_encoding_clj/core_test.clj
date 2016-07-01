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

(deftest true-function-test
  (testing "true is a function that takes two parameters and always chooses the first one"
    (is (= ((true-function true) false) true))))

(deftest false-function-test
  (testing "false is a function that takes two parameters and always chooses the second one"
    (is (= ((false-function true) false) false))))
