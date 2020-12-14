(ns exercises.chapter2.exercise2_45
  (:use exercises.picture))

; Exercise 2.45:
; 'right-split' and 'up-split' can be expressed as instances of a general splitting
;   operation.
; Define a procedure 'split' with the property that evaluating
;
;   (define right-split (split beside below))
;   (define up-split (split below beside))
;
; produces procedures 'right-split' and 'up-split' with the same behaviors as the ones
;   already defined.

(defn split [a-splitter another-splitter]
  (fn [painter n]
    (if (= n 0)
      painter
      (let [smaller ((split a-splitter another-splitter) painter (dec n))]
        (a-splitter painter (another-splitter smaller smaller))))))

(def right-split (split beside below))

(def up-split (split below beside))

(draw (right-split wave 4))

(draw ((split beside below) wave 4))

(draw (up-split wave 4))

(draw ((split below beside) wave 4))
