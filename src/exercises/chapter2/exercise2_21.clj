(ns exercises.chapter2.exercise2_21)

; Exercise 2.21:
; The procedure square-list takes a list of numbers as argument and
;   returns a list of the squares of those numbers.
;
;   (square-list (list 1 2 3 4))
;     (1 4 9 16)
;
; Here are two different definitions of square-list.
; Complete both of them by filling in the missing expressions:
;
;   (define (square-list items)
;     (if (null? items)
;       nil
;       (cons
;         ⟨ ?? ⟩ ⟨ ?? ⟩ )))
;   (define (square-list items)
;     (map
;       ⟨ ?? ⟩ ⟨ ?? ⟩ ))

(defn square [x] (* x x))

(defn map [proc items]
  (if (empty? items)
    nil
    (cons (proc (first items))
          (map proc (rest items)))))

(defn square-list [items]
  (if (empty? items)
    nil
    (cons
      (square (first items)) (square-list (rest items)) )))

(square-list (list 1 2 3 4))
; => (1 4 9 16)

(defn square-list [items]
  (map square items ))

(square-list (list 1 2 3 4))
; => (1 4 9 16)
