(ns exercises.chapter2.exercise2_44
  (:use exercises.picture))

; Exercise 2.44:
; Define the procedure 'up-split' used by 'corner-split'.
; It is similar to 'right-split', except that it switches the roles of
;   'below' and 'beside'.

(defn up-split [p n]
  (if (= n 0)
    p
    (let [smaller (up-split p (dec n))]
      (below p (beside smaller smaller)))))

(draw (up-split wave 4))
