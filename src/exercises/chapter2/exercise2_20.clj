(ns exercises.chapter2.exercise2_20)


; Exercise 2.20:
; The procedures + , * , and list take arbitrary numbers of arguments.
; One way to define such procedures is to use define with dotted-tail notation.
; In a procedure definition, a parameter list that has a dot before the last parameter name
;   indicates that, when the procedure is called, the initial parameters (if any) will have
;   as values the initial arguments, as usual, but the final parameter’s value will be a list
;   of any remaining arguments.
; For instance, given the definition
;
;   (define (f x y . z) ⟨ body ⟩ )
;
;   the procedure f can be called with two or more arguments.
; If we evaluate (f 1 2 3 4 5 6)
;   then in the body of f, x will be 1, y will be 2, and z will be the list (3 4 5 6).
; Given the definition
;
;   (define (g . w) ⟨ body ⟩ )
;
;   the procedure g can be called with zero or more arguments.
;
; If we evaluate (g 1 2 3 4 5 6)
;   then in the body of g, w will be the list (1 2 3 4 5 6). Footnote 11
;
; Use this notation to write a procedure same-parity that takes one or more integers and returns a list of all
;   the arguments that have the same even-odd parity as the first argument.
; For example,
;
;   (same-parity 1 2 3 4 5 6 7)
;   (1 3 5 7)
;
;   (same-parity 2 3 4 5 6 7)
;   (2 4 6)
;
; Footnote: 11
;  To define f and g using lambda we would write
;
;  (define f (lambda (x y . z)  ⟨ body ⟩ ))
;  (define g (lambda w ⟨ body ⟩ ))

; dotted-tail notation in Scheme is similar to variadic functions in Clojure: https://clojure.org/guides/learn/functions#_variadic_functions

(defn f [x y & z]
  (println x y z))

(f 1 2 3 4 5 6)
; 1 2 (3 4 5 6)
; => nil

(defn same-parity [e & items]
  (let [has-same-parity? (if (odd? e) odd? even?)]
    (loop [items items result (list e)]
       (cond (empty? items) (reverse result)
             (has-same-parity? (first items)) (recur (rest items) (cons (first items) result))
             :else (recur (rest items) result)))))

(same-parity 1 2 3 4 5 6 7)
; => (1 3 5 7)

(same-parity 2 3 4 5 6 7)
; => (2 4 6)

(defn same-parity-with-concat [e & items]
  (let [has-same-parity? (if (odd? e) odd? even?)]
    (loop [items items result (list e)]
      (cond (empty? items) result
            (has-same-parity? (first items)) (recur (rest items) (concat result (list (first items))))
            :else (recur (rest items) result)))))

(same-parity-with-concat 1 2 3 4 5 6 7)
; => (1 3 5 7)

(same-parity-with-concat 2 3 4 5 6 7)
; => (2 4 6)

(defn same-parity-with-filter [e & items]
  (let [has-same-parity? (if (odd? e) odd? even?)]
    (cons e (filter has-same-parity? items))))

(same-parity-with-filter 1 2 3 4 5 6 7)
; => (1 3 5 7)

(same-parity-with-filter 2 3 4 5 6 7)
; => (2 4 6)