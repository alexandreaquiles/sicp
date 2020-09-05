(ns exercises.chapter2.exercise2_8
  (:use exercises.chapter2.exercise2_7))

;Exercise 2.8:
; Using reasoning analogous to Alyssa’s, describe how the difference of
;   two intervals may be computed.
; Define a corresponding subtraction procedure, called sub-interval .

; defining subtraction in terms of addition, as done with division/multiplication
(defn sub-interval [x y]
  (add-interval x
                (make-interval
                    (- (upper-bound y))
                    (- (lower-bound y)))))

(sub-interval (make-interval 4 5) (make-interval 1 2))
;=> (2 4)

(sub-interval (make-interval 4 5) (make-interval -1 2))
;=> (2 6)

(sub-interval (make-interval -4 5) (make-interval 1 2))
;=> (-6 4)
