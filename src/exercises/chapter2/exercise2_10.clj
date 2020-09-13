(ns exercises.chapter2.exercise2_10
  (:use exercises.chapter2.exercise2_7
        exercises.chapter2.exercise2_8))

; Exercise 2.10:
; Ben Bitdiddle, an expert systems programmer, looks over Alyssa’s shoulder and comments
;   that it is not clear what it means to divide by an interval that spans
;   zero.
; Modify Alyssa’s code to check for this condition and to signal an error if it occurs.

(defn spans-zero? [interval]
  (or (= (lower-bound interval) 0)
      (= (upper-bound interval) 0)))

(intern 'exercises.chapter2.exercise2_7
        'div-interval
        (fn [x y]
          (if (spans-zero? y)
            (throw (IllegalArgumentException. "should not divide by an interval that spans zero"))
            (mul-interval x
                          (make-interval (/ 1.0 (upper-bound y))
                                         (/ 1.0 (lower-bound y)))))))

(div-interval resistor-1 resistor-2)
;=> (1.2401215805471126 1.6752519596864504)

(def zero-lower-bound-resistor (make-interval -6.12 0))
(def zero-upper-bound-resistor (make-interval 0 7.48))

;(div-interval resistor-1 zero-lower-bound-resistor)
;should not divide by an interval that spans zero

;(div-interval resistor-1 zero-upper-bound-resistor)
;should not divide by an interval that spans zero
