(ns exercises.chapter2.text)

(defn linear-combination [a b x y]
  (+ (* a x) (* b y)))

;(defn linear-combination [a b x y]
;  (add (mul a x) (mul b y)))

(def x (cons 1 '(2)))

(first x)
; => 1

(rest x)
; => (2)

(first (rest x))
; => 2

(def x (cons 1 '(2)))
(def y (cons 3 '(4)))
(def z (cons x y))

(first (first z))
; => 1

(first (rest z))
; => 3

;(def make-rat cons) ;won't work because cons needs a list as the 2nd arg
;(def numer first)
;(def denom rest)

(defn make-rat [n d] (cons n (cons d '())))
(defn numer [x] (first x))
(defn denom [x] (first (rest x)))

(defn add-rat [x y]
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn sub-rat [x y]
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn mul-rat [x y]
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))

(defn div-rat [x y]
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))

(defn equal-rat? [x y]
  (= (* (numer x) (denom y))
     (* (numer y) (denom x))))

(defn print-rat [x]
  ;(newline)
  (print (numer x))
  (print "/")
  (println (denom x)))

(def one-half (make-rat 1 2))
(print-rat one-half)
; 1/2
; => nil

(def one-third (make-rat 1 3))
(print-rat (add-rat one-half one-third))
; 5/6
; => nil

(print-rat (mul-rat one-half one-third))
; 1/6
; => nil

(print-rat (add-rat one-third one-third))
; 6/9
; => nil

(defn gcd [a b]
  (if (= b 0)
    a
    (recur b (rem a b))))

(defn make-rat [n d]
  (let [g (gcd n d)]
    (cons (/ n g) (cons (/ d g) '()))))

(print-rat (add-rat one-third one-third))
; 2/3
; => nil

(defn make-rat [n d] (cons n (cons d '())))

(defn numer [x]
  (let [g (gcd (first x) (first (rest x)))]
    (/ (first x) g)))

(defn denom [x]
  (let [g (gcd (first x) (first (rest x)))]
    (/ (first (rest x)) g)))

(print-rat (add-rat one-third one-third))

(defn fn-cons [x y]
  (defn dispatch [m]
    (cond (= m 0) x
          (= m 1) y
          :else (throw (IllegalArgumentException. (str "Argument not 0 or 1 -- CONS " m)))))
  dispatch)

(defn fn-car [z] (z 0))

(defn fn-cdr [z] (z 1))

(fn-car (fn-cons 1 2))
; => 1

(fn-cdr (fn-cons 1 2))
;=> 2

(fn-car (fn-cons 1 (fn-cons 2 3)))
;=> 1

(fn-cdr (fn-cons 1 (fn-cons 2 3)))
;=> #'exercises.chapter2.text/dispatch

(fn-car (fn-cdr (fn-cons 1 (fn-cons 2 3))))
; => 2

;((cons 1 2) 999)
;Syntax error (IllegalArgumentException)
;Argument not 0 or 1 -- CONS 999

(cons 1
      (cons 2
            (cons 3
                  (cons 4 nil))))
; => (1 2 3 4)

(def one-through-four (list 1 2 3 4))

one-through-four
; => (1 2 3 4)

(first one-through-four)
; => 1

(rest one-through-four)
; => (2 3 4)

(first (rest one-through-four))
; => 2

(cons 10 one-through-four)
; => (10 1 2 3 4)

(cons 5 one-through-four)
; => (5 1 2 3 4)

(defn list-ref [items n]
  (if (= n 0)
    (first items)
    (recur (rest items) (- n 1))))

(def squares (list 1 4 9 16 25))

(list-ref squares 3)
; => 16

(nil? 1)
; => false

(nil? nil)
; => true

(nil? '())
; => false

(empty? '())
; => true

(empty? nil)
; => true

(rest (list 1))
; => ()

(next (list 1))
; => nil

(defn length [items]
  (if (empty? items)
    0
    (inc (length (rest items)))))

(def odds (list 1 3 5 7))

(length odds)
; => 4

(defn length [items]
  (loop [items items count 0]
    (if (empty? items)
      count
      (recur (next items) (inc count)))))

(length odds)
; => 4

(length '())
; => 0

(length '(1))
; => 1

(defn append [list1 list2]
  (if (empty? list1)
    list2
    (cons (first list1)
          (append (rest list1) list2))))

(append squares odds)
; => (1 4 9 16 25 1 3 5 7)

(append odds squares)
; => (1 3 5 7 1 4 9 16 25)

(defn append-iter [list1 list2]
  (if (empty? list1)
    list2
    (recur (rest list1) (cons (first list1) list2) )))

(append-iter squares odds)
; => (25 16 9 4 1 1 3 5 7)
; INCORRET! The first list is inverted.
; cons adds to the head

(defn append-iter-with-conj [list1 list2]
  (if (empty? list2)
    list1
    (recur (conj list1 (first list2)) (rest list2) )))

(def squares-vector [1 4 9 16 25])
(def odds-vector [1 3 5 7])

(append-iter-with-conj squares-vector odds-vector)
; => [1 4 9 16 25 1 3 5 7]

(append-iter-with-conj odds-vector squares-vector)
; => [1 3 5 7 1 4 9 16 25]
