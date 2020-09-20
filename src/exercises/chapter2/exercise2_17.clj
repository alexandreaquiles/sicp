(ns exercises.chapter2.exercise2_17)

; Exercise 2.17:
; Define a procedure last-pair that returns the list that contains only the last element of a given (nonempty)
;   list:
;
;   (last-pair (list 23 72 149 34))
;   (34)

(defn last-pair [list]
  (if (empty? (rest list))
    list
    (recur (rest list))))

(last-pair (list 23 72 149 34))
; => (34)

(last-pair (list 23))
; => (23)

(last-pair '())
; => ()