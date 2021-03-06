(ns exercises.chapter2.exercise2_12
  (:use exercises.chapter2.exercise2_7
        exercises.chapter2.exercise2_8
        exercises.chapter2.exercise2_10
        exercises.chapter2.exercise2_11))

; Exercise 2.12:
; Define a constructor make-center-percent that takes a center and
;   a percentage tolerance and produces the desired interval.
; You must also define a selector percent that produces the percentage
;   tolerance for a given interval.
; The center selector is the same as the one shown above.

(defn make-center-width [c w]
  (make-interval (- c w) (+ c w)))

(defn center [i]
  (/ (+ (lower-bound i) (upper-bound i)) 2))

(defn width [i]
  (/ (- (upper-bound i) (lower-bound i)) 2))

(defn make-center-percent [c p]
  (make-center-width c (* c (/ p 100))))

(defn percent [i]
  (* (/ (width i) (center i)) 100))

(def resistor (make-center-percent 3.5 4.29))

(center resistor)
;=> 3.5

(width resistor)
;=> 0.15015

(percent resistor)
;=> 4.29

(assert (= (center resistor) 3.5))
(assert (= (width resistor) 0.15015))
(assert (= (percent resistor) 4.29))