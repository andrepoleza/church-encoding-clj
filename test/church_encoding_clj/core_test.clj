(ns church-encoding-clj.core-test
  (:require [clojure.test :refer :all]
            [church-encoding-clj.core :refer :all]))

(deftest zero-function
  (testing "zero is a function that corresponds to 0"
    (is (= (church->integer zero) 0))))

(deftest one-function
  (testing "one is a function that corresponds to 1"
    (is (= (church->integer one) 1))))

(deftest two-function
  (testing "two is a function that corresponds to 2"
    (is (= (church->integer two) 2))))

(deftest three-function
  (testing "three is a function that corresponds to 3"
    (is (= (church->integer three) 3))))