(ns church-encoding-clj.core)

(defn zero [f]
  (fn [x]
    x))

(defn one [f]
  (fn [x]
    (f x)))

(defn two [f]
  (fn [x]
    (f (f x))))

(defn three [f]
  (fn [x]
    (f (f (f x)))))

(defn successor [n]
  (fn [f]
    (fn [x]
      (f ((n f) x)))))

(defn predecessor [n]
  (fn [f]
    (fn [x]
      (((n
         (fn [g]
           (fn [h]
             (h (g f)))))
        (fn [u]
          x))
       (fn [u]
         u)))))

(defn church->integer [x]
  ((x inc) 0))

(defn integer->church [x]
  (if (= x 0)
    zero
    (successor (integer->church (dec x)))))

(defn addition [a]
  (fn [b]
    ((b successor) a)))

(defn subtraction [a]
  (fn [b]
    ((b predecessor) a)))

(defn multiplication [a]
  (fn [b]
    (fn [f]
      (fn [x]
        ((a (b f)) x)))))

(defn exponentiation [a]
  (fn [b]
    (b a)))

(defn true-function [first]
  (fn [second]
    first))

(defn false-function [first]
  (fn [second]
    second))

(defn is-zero? [n]
  ((n
    (fn [x]
      false-function)) true-function))

(defn or-function [first]
  (fn [second]
    ((first true-function) second)))

(defn and-function [first]
  (fn [second]
    ((first second) false-function)))

(defn not-function [x]
  ((x false-function) true-function))

(defn nor-function [first]
  (fn [second]
    (not-function ((or-function first) second))))

(defn nand-function [first]
  (fn [second]
    (not-function ((and-function first) second))))

(defn xor-function [first]
  (fn [second]
    ((first
      ((second false-function) true-function)) ((second true-function) false-function))))

(defn xnor-function [first]
  (fn [second]
    (not-function ((xor-function first) second))))

(defn is-less-or-equal? [first]
  (fn [second]
    (is-zero? ((subtraction first) second))))

(defn is-greater-or-equal? [first]
  (fn [second]
    (is-zero? ((subtraction second) first))))
