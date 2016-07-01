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
