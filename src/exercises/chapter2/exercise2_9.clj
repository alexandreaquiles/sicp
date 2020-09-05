(ns exercises.chapter2.exercise2_9
  (:use exercises.chapter2.exercise2_7
        exercises.chapter2.exercise2_8))

;Exercise 2.9:
; The width of an interval is half of the difference between its
;   upper and lower bounds.
; The width is a measure of the uncertainty of the number specified by
;   the interval.
; For some arithmetic operations the width of the result of combining
;   two intervals is a function only of the widths of the argument
;   intervals, whereas for others the width of the combination is not
;   a function of the widths of the argument intervals.
; Show that the width of the sum (or difference) of two intervals
;   is a function only of the widths of the intervals being added
;   (or subtracted).
; Give examples to show that this is not true for multiplication or
;   division.

(defn width-interval [interval]
  (/ (- (upper-bound interval) (lower-bound interval)) 2))

(width-interval (make-interval 6.12 7.48))
;=> 0.6800000000000002

; (xL . xU) + (yL . yU) <=> ( (xL + yL) . (xU + yU) )
; (xL . xU) - (yL . yU) <=> (xL . xU) + (-yU . -yL) <=> ( (xL - yU) . (xU - yL) )

; width (xL . xU) = (xU - xL) / 2
; width (yL . yU) = (yU - yL) / 2

; width (xL . xU) + width (yL . yU) = (xU - xL) / 2 + (yU - yL) / 2
;                                   = (xU + yU - xL - yL) / 2
;                                   = ((xU + yU) - (xL + yL))/2

; width [(xL . xU) + (yL . yU)] = width ( (xL + yL) . (xU + yU) )
;                               = ((xU + yU) - (xL + yL)) / 2
; QED

; width [(xL . xU) - (yL . yU)] = width ( (xL - yU) . (xU - yL) )
;                               = ((xU - yL) - (xL - yU)) / 2
;                               = (xU - yL - xL + yU) / 2
;                               = (xU + yU) - (xL + yL)) / 2
; QED

; depending on the relation between xL, xU, yL and yU
; (xL . xU) * (yL . yU) <=> ( (xL * yL) . (xU * yU) )

; width (xL . xU) * width (yL . yU) = (xU - xL) / 2 * (yU - yL) / 2
;                                   = (xU*yU + xU*-yL -xL*yU -xL*-yL) / 2
;                                   = (xU*yU - xU* yL -xL*yU + xL*yL) / 2

; width [(xL . xU) * (yL . yU)] = width ( (xL * yL) . (xU * yU) )
;                               = ((xU * yU) - (xL * yL)) / 2
; THAT's DIFFERENT from the multiplication of width
