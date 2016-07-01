(ns church-encoding-clj.core)

(defn church->integer [x]
  ((x inc) 0))

(defn zero [f]
  (fn [x] x))

(defn one [f]
  (fn [x] (f x)))

(defn two [f]
  (fn [x] (f (f x))))

(defn three [f]
  (fn [x] (f (f (f x)))))

(defn successor [n]
  (fn [f]
    (fn [x] (f ((n f) x)))))

(defn addition [a]
  (fn [b]
    ((b successor) a)))

(defn true-function [first]
  (fn [second] first))

(defn false-function [first]
  (fn [second] second))
