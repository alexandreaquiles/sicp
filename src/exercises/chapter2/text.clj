(ns exercises.chapter2.text)

(defn linear-combination [a b x y]
  (+ (* a x) (* b y)))

;(defn linear-combination [a b x y]
;  (add (mul a x) (mul b y)))

(def x (cons 1 '(2)))

(first x)
; => 1

(rest x)
; => (2)

(first (rest x))
; => 2

(def x (cons 1 '(2)))
(def y (cons 3 '(4)))
(def z (cons x y))

(first (first z))
; => 1

(first (rest z))
; => 3

;(def make-rat cons) ;won't work because cons needs a list as the 2nd arg
;(def numer first)
;(def denom rest)

(defn make-rat [n d] (cons n (cons d '())))
(defn numer [x] (first x))
(defn denom [x] (first (rest x)))

(defn add-rat [x y]
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn sub-rat [x y]
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn mul-rat [x y]
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))

(defn div-rat [x y]
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))

(defn equal-rat? [x y]
  (= (* (numer x) (denom y))
     (* (numer y) (denom x))))

(defn print-rat [x]
  ;(newline)
  (print (numer x))
  (print "/")
  (println (denom x)))

(def one-half (make-rat 1 2))
(print-rat one-half)
; 1/2
; => nil

(def one-third (make-rat 1 3))
(print-rat (add-rat one-half one-third))
; 5/6
; => nil

(print-rat (mul-rat one-half one-third))
; 1/6
; => nil

(print-rat (add-rat one-third one-third))
; 6/9
; => nil

(defn gcd [a b]
  (if (= b 0)
    a
    (recur b (rem a b))))

(defn make-rat [n d]
  (let [g (gcd n d)]
    (cons (/ n g) (cons (/ d g) '()))))

(print-rat (add-rat one-third one-third))
; 2/3
; => nil

(defn make-rat [n d] (cons n (cons d '())))

(defn numer [x]
  (let [g (gcd (first x) (first (rest x)))]
    (/ (first x) g)))

(defn denom [x]
  (let [g (gcd (first x) (first (rest x)))]
    (/ (first (rest x)) g)))

(print-rat (add-rat one-third one-third))
