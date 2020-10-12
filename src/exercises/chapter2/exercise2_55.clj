(ns exercises.chapter2.exercise2_55)

; Exercise 2.55:
; Eva Lu Ator types to the interpreter the expression
;
;   (car ''abracadabra)
;
; To her surprise, the interpreter prints back quote. Explain.

(first ''abracadabra)
; => quote

; This expression represents the list (quote abracadabra)
; The first element is the 'quote' function

(println ''abracadabra)
; (quote abracadabra)
;=> nil

''abracadabra
; => (quote abracadabra)

(rest ''abracadabra)
; => (abracadabra)

(first (rest ''abracadabra))
; => abracadabra