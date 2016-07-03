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

(defn multiplication [a]
  (fn [b]
    (fn [f]
      (fn [x]
        ((a (b f)) x)))))

(defn exponentiation [a]
  (fn [b]
    (b a)))

(defn true-function [first]
  (fn [second] first))

(defn false-function [first]
  (fn [second] second))

(defn is-zero? [n]
  ((n (fn [x] false-function)) true-function))

(defn or-function [first]
  (fn [second]
    ((first true-function) second)))

(defn and-function [first]
  (fn [second]
    ((first second) false-function)))
