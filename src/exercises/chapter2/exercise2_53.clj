(ns exercises.chapter2.exercise2_53)

; Exercise 2.53:
; What would the interpreter print in response to evaluating each of the
;   following expressions?
;
; (list 'a 'b 'c)
; (list (list 'george))
; (cdr '((x1 x2) (y1 y2)))
; (cadr '((x1 x2) (y1 y2)))
; (pair? (car '(a short list)))
; (memq 'red '((red shoes) (blue socks)))
; (memq 'red '(red shoes blue socks))

(defn memq [item x]
  (cond (empty? x) false
        (= item (first x)) x
        :else (memq item (rest x))))

(list 'a 'b 'c)
; => (a b c)

(list (list 'george))
; => ((george))

(rest '((x1 x2) (y1 y2)))
; => ((y1 y2))

(first (rest '((x1 x2) (y1 y2))))
; => (y1 y2)

(seq? (first '(a short list)))
; => false

(memq 'red '((red shoes) (blue socks)))
; => false

(memq 'red '(red shoes blue socks))
;=> (red shoes blue socks)