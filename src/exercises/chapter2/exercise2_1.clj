(ns exercises.chapter2.exercise2_1
  (:use [exercises.math]))

; Exercise 2.1:
; Define a better version of 'make-rat' that handles both positive and negative arguments.
; 'make-rat' should normalize the sign so that if the rational number is positive,
;   both the numerator and denominator are positive, and if the rational number is negative,
;   only the numerator is negative.

(defn numer [x] (first x))
(defn denom [x] (first (rest x)))

(defn make-rat [n d]
  (let [g (gcd n d)]
    (cons (/ n g) (cons (/ d g) '()))))

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

; positive rat
; n >= 0 and d > 0 => doesn't need to do nothing
; n <  0 and d < 0 => negates both n and d

; negative rat
; n >= 0 and d < 0 => negates both n and d
; n <  0 and d > 0 => doesn't need to do nothing

(defn should-negate-rat? [n d]
  (or (and (neg-int? n) (neg-int? d))
      (and (pos-int? n) (neg-int? d))))

(assert (false? (should-negate-rat? 1 2)))
(assert (true? (should-negate-rat? -1 -2)))
(assert (true? (should-negate-rat? 1 -2)))
(assert (false? (should-negate-rat? -1 2)))

(defn normalize-rat [n d]
  (if (should-negate-rat? n d)
     (cons (- n) (cons (- d) '()))
     (cons n (cons d '() ))))

(defn make-rat [n d]
  (assert (not= d 0))
  (let [g (gcd (abs n) (abs d))]
    (normalize-rat (/ n g) (/ d g))))

(print-rat (make-rat 1 2))
; 1/2
; => nil

(print-rat (make-rat 2 4))
; 1/2
; => nil

(print-rat (make-rat -1 -2))
; 1/2
; => nil

(print-rat (make-rat -2 -4))
; 1/2
; => nil

(print-rat (make-rat 1 2))
; -1/-2
; => nil

(print-rat (make-rat 2 -4))
; -1/2
; => nil

(print-rat (make-rat -1 2))
; -1/2
; => nil

(print-rat (make-rat -2 4))
; -1/2
; => nil

(print-rat (add-rat (make-rat 1 3) (make-rat 1 3)))
; 2/3
; => nil
