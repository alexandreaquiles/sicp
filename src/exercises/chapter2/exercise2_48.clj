(ns exercises.chapter2.exercise2_48
  (:use exercises.chapter2.exercise2_46))

; Exercise 2.48:
; A directed line segment in the plane can be represented as a pair of vectors —
;   the vector running from the origin to the start-point of the segment,
;   and the vector running from the origin to the end-point of the segment.
; Use your vector representation from Exercise 2.46 to define a representation
;   for segments with a constructor 'make-segment' and selectors 'start-segment'
;   and 'end-segment'.

(defn make-segment [start end] (list start end))
(defn start-segment [segment] (first segment))
(defn end-segment [segment] (second segment))
