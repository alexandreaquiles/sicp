(ns exercises.chapter2.text
  (:use exercises.math))

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
    (recur (rest list1) (cons (first list1) list2))))

(append-iter squares odds)
; => (25 16 9 4 1 1 3 5 7)
; INCORRET! The first list is inverted.
; cons adds to the head

(defn append-iter-with-conj [list1 list2]
  (if (empty? list2)
    list1
    (recur (conj list1 (first list2)) (rest list2))))

(def squares-vector [1 4 9 16 25])
(def odds-vector [1 3 5 7])

(append-iter-with-conj squares-vector odds-vector)
; => [1 4 9 16 25 1 3 5 7]

(append-iter-with-conj odds-vector squares-vector)
; => [1 3 5 7 1 4 9 16 25]

(defn scale-list [items factor]
  (if (empty? items)
    nil
    (cons (* (first items) factor)
          (scale-list (rest items)
                      factor))))

(scale-list (list 1 2 3 4 5) 10)
; => (10 20 30 40 50)

(defn map [proc items]
  (if (empty? items)
    nil
    (cons (proc (first items))
          (map proc (rest items)))))

(map abs (list -10 2.5 -11.6 17))
; => (10 2.5 11.6 17)

(map (fn [x] (* x x)) (list 1 2 3 4))
; => (1 4 9 16)

(defn scale-list [items factor]
  (map (fn [x] (* x factor))
       items))

(scale-list (list 1 2 3 4 5) 10)
; => (10 20 30 40 50)

(cons (list 1 2) (list 3 4))
; => ((1 2) 3 4)

(def x (cons (list 1 2) (list 3 4)))
(length x)
; => 3

(defn count-leaves [x]
  (cond (not (seq? x)) 1
        (empty? x) 0
        :else (+ (count-leaves (first x))
                 (count-leaves (rest x)))))

; if I put (empty?) before (not (seq? )) I get
;     IllegalArgumentException
;     Don't know how to create ISeq from: java.lang.Long
; empty? calls (not (seq coll)), so it expects a sequence

(count-leaves x)
; => 4

(list x x)
; => (((1 2) 3 4) ((1 2) 3 4))

(length (list x x))
; => 2

(count-leaves (list x x))
; => 8

(defn scale-tree [tree factor]
  (cond (not (seq? tree)) (* tree factor)
        (empty? tree) nil
        :else (cons (scale-tree (first tree) factor)
                    (scale-tree (rest tree) factor))))

(scale-tree (list 1 (list 2 (list 3 4) 5) (list 6 7)) 10)
; => (10 (20 (30 40) 50) (60 70))

(defn scale-tree [tree factor]
  (map (fn [sub-tree]
         (if (seq? sub-tree)
           (scale-tree sub-tree factor)
           (* sub-tree factor)))
       tree))

(scale-tree (list 1 (list 2 (list 3 4) 5) (list 6 7)) 10)
; => (10 (20 (30 40) 50) (60 70))

(defn sum-odd-squares [tree]
  (cond (not (seq? tree)) (if (odd? tree) (square tree) 0)
        (empty? tree) 0
        :else (+ (sum-odd-squares (first tree))
                 (sum-odd-squares (rest tree)))))

(sum-odd-squares (list 1 2 3 4 5 6))
; => 35

(defn fib [n]
  (defn fib-iter [a b count]
    (if (= count 0)
      b
      (recur (+ a b) a (- count 1))))
  (fib-iter 1 0 n))

(defn even-fibs [n]
  (defn next [k]
    (if (> k n)
      nil
      (let [f (fib k)]
        (if (even? f)
          (cons f (next (+ k 1)))
          (next (+ k 1))))))
  (next 0))

(map fib (range 11))
; => (0 1 1 2 3 5 8 13 21 34 55)

(even-fibs 10)
; => (0 2 8 34)

(map square (list 1 2 3 4 5))
; => (1 4 9 16 25)

(defn filter [predicate sequence]
  (cond (empty? sequence) nil
        (predicate (first sequence)) (cons (first sequence) (filter predicate (rest sequence)))
        :else (filter predicate (rest sequence))))

(filter odd? (list 1 2 3 4 5))
; => (1 3 5)

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

(accumulate + 0 (list 1 2 3 4 5))
; => 15

(accumulate * 1 (list 1 2 3 4 5))
; => 120

(accumulate cons nil (list 1 2 3 4 5))
; => (1 2 3 4 5)

(defn enumerate-interval [low high]
  (if (> low high)
    nil
    (cons low (enumerate-interval (inc low) high))))

(enumerate-interval 2 7)
; => (2 3 4 5 6 7)

(defn enumerate-tree [tree]
  (cond (not (seq? tree)) (list tree)
        (empty? tree) nil
        :else (append (enumerate-tree (first tree))
                      (enumerate-tree (rest tree)))))

(enumerate-tree (list 1 (list 2 (list 3 4)) 5))
; => (1 2 3 4 5)

(defn sum-odd-squares [tree]
  (accumulate
    + 0 (map square (filter odd? (enumerate-tree tree)))))

(sum-odd-squares (list 1 2 3 4 5 6))
; => 35

(enumerate-tree (list 1 (list 2 (list 3 4)) 5))
; => (1 2 3 4 5)

(filter odd? (list 1 2 3 4 5 6))
; => (1 3 5)

(map square (list 1 3 5))
; => (1 9 25)

(accumulate + 0 (list 1 9 25))
; => 35

(defn even-fibs [n]
  (accumulate
    cons
    nil
    (filter even? (map fib (enumerate-interval 0 n)))))

(even-fibs 10)
; => (0 2 8 34)

(enumerate-interval 0 10)
; => (0 1 2 3 4 5 6 7 8 9 10)

(map fib (list 0 1 2 3 4 5 6 7 8 9 10))
; => (0 1 1 2 3 5 8 13 21 34 55)

(filter even? (list 0 1 1 2 3 5 8 13 21 34 55))
; => (0 2 8 34)

(accumulate cons nil (list 0 2 8 34))
; => (0 2 8 34)

(defn list-fib-squares [n]
  (accumulate
    cons
    nil
    (map square (map fib (enumerate-interval 0 n)))))

(list-fib-squares 10)
; => (0 1 1 4 9 25 64 169 441 1156 3025)

(defn product-of-squares-of-odd-elements [sequence]
  (accumulate * 1 (map square (filter odd? sequence))))

(product-of-squares-of-odd-elements (list 1 2 3 4 5))
; => 225

(def records (list {:profession :programmer :salary 10000}
                   {:profession :programmer :salary 5000}
                   {:profession :cto :salary 50000}))

(defn programmer? [record]
  (= (:profession record) :programmer))

(defn salary [record]
  (:salary record))

(defn salary-of-highest-paid-programmer [records]
  (accumulate max 0 (map salary (filter programmer? records))))

(salary-of-highest-paid-programmer records)
; => 10000

; all ordered pairs of distinct positive integers i and j,
;   where 1 ≤ j < i ≤ n
(defn ordered-pairs [n]
  (accumulate
    append nil (map (fn [i]
                      (map (fn [j] (list i j))
                           (enumerate-interval 1 (- i 1))))
                    (enumerate-interval 1 n))))

(ordered-pairs 6)
; => ((2 1) (3 1) (3 2) (4 1) (4 2) (4 3) (5 1) (5 2) (5 3) (5 4) (6 1) (6 2) (6 3) (6 4) (6 5))

(defn flatmap [proc seq]
  (accumulate append nil (map proc seq)))

(defn prime-sum? [pair]
  (prime? (+ (first pair) (first (rest pair)))))

(defn make-pair-sum [pair]
  (list (first pair) (first (rest pair)) (+ (first pair) (first (rest pair)))))

(defn prime-sum-pairs [n]
  (map make-pair-sum
       (filter prime-sum? (flatmap
                            (fn [i]
                              (map (fn [j] (list i j))
                                   (enumerate-interval 1 (- i 1))))
                            (enumerate-interval 1 n)))))

(prime-sum-pairs 6)
; => ((2 1 3) (3 2 5) (4 1 5) (4 3 7) (5 2 7) (6 1 7) (6 5 11))

(defn remove [item sequence]
  (filter (fn [x] (not (= x item)))
          sequence))

(defn permutations [s]
  (if (empty? s)
    (list nil)
    (flatmap (fn [x]
               (map (fn [p] (cons x p))
                    (permutations (remove x s))))
             s)))

(remove 1 '(1 2 3))
; => (2 3)

(permutations '(2 3))
; => ((2 3) (3 2))

(remove 2 '(1 2 3))
; => (1 3)

(permutations '(1 3))
; => ((1 3) (3 1))

(remove 3 '(1 2 3))
; => (1 2)

(permutations '(1 2))
; => ((1 2) (2 1))

(permutations '(1 2 3))
; => ((1 2 3) (1 3 2) (2 1 3) (2 3 1) (3 1 2) (3 2 1))

(permutations '(1 2 3 4))
; =>
; ((1 2 3 4)
;  (1 2 4 3)
;  (1 3 2 4)
;  (1 3 4 2)
;  (1 4 2 3)
;  (1 4 3 2)
;  (2 1 3 4)
;  (2 1 4 3)
;  (2 3 1 4)
;  (2 3 4 1)
;  (2 4 1 3)
;  (2 4 3 1)
;  (3 1 2 4)
;  (3 1 4 2)
;  (3 2 1 4)
;  (3 2 4 1)
;  (3 4 1 2)
;  (3 4 2 1)
;  (4 1 2 3)
;  (4 1 3 2)
;  (4 2 1 3)
;  (4 2 3 1)
;  (4 3 1 2)
;  (4 3 2 1))

(def a 1)
(def b 2)

(list a b)
; => (1 2)

(list 'a 'b)
; => (a b)

(list 'a b)
; => (a 2)

(first '(a b c))
; => a

(rest '(a b c))
; => (b c)

(= 'a 'a)
; => true

(defn memq [item x]
  (cond (empty? x) false
        (= item (first x)) x
        :else (memq item (rest x))))

(memq 'apple '(pear banana prune))
; => false

(memq 'apple '(x (apple sauce) y apple pear))
; => (apple pear)

(defn variable? [x] (symbol? x))

(variable? 'x)
; => true

(variable? 1)
; => false

(defn same-variable? [v1 v2]
  (and (variable? v1) (variable? v2) (= v1 v2)))

(same-variable? 'x 'x)
; => true

(same-variable? 'x 'y)
; => false

(defn make-sum [a1 a2] (list '+ a1 a2))

(make-sum 'x 'y)
; => (+ x y)

(defn make-product [m1 m2] (list '* m1 m2))

(make-product 'x 'y)
; => (* x y)

(defn sum? [x] (and (seq? x) (= (first x) '+)))

(sum? '(+ x y))
; => true

(sum? '(* x y))
; => false

(sum? 1)
; => false

(defn addend [s] (first (rest s)))

(addend '(+ x y))
; => x

(defn augend [s] (first (rest (rest s))))

(augend '(+ x y))
; => y

(defn product? [x] (and (seq? x) (= (first x) '*)))

(product? '(* x y))
; => true

(product? '(+ x y))
; => true

(product? 1)
; => false

(defn multiplier [p] (first (rest p)))

(multiplier '(* x y))
; => x

(defn multiplicand [p] (first (rest (rest p))))

(multiplicand '(* x y))
; => y

(defn deriv [exp var]
  (cond (number? exp) 0
        (variable? exp) (if (same-variable? exp var) 1 0)
        (sum? exp) (make-sum (deriv (addend exp) var)
                             (deriv (augend exp) var))
        (product? exp) (make-sum
                         (make-product (multiplier exp)
                                       (deriv (multiplicand exp) var))
                         (make-product (deriv (multiplier exp) var)
                                       (multiplicand exp)))
        :else (throw (IllegalArgumentException. (str "unknown expression type: DERIV" exp)))))

(deriv '(+ x 3) 'x)
; (+ 1 0)

(deriv '(* x y) 'x)
; (+ (* x 0) (* 1 y))

(deriv '(* (* x y) (+ x 3)) 'x)
; (+ (* (* x y) (+ 1 0))
; (* (+ (* x 0) (* 1 y))
;   (+ x 3)) )

(defn =number? [exp num] (and (number? exp) (= exp num)))

(=number? '0 0)
; => true

(=number? '0 1)
; => false

(=number? 'x 0)
; => false

(defn make-sum [a1 a2]
  (cond (=number? a1 0) a2
        (=number? a2 0) a1
        (and (number? a1) (number? a2)) (+ a1 a2)
        :else (list '+ a1 a2)))

(defn make-product [m1 m2]
  (cond (or (=number? m1 0) (=number? m2 0)) 0
        (=number? m1 1) m2
        (=number? m2 1) m1
        (and (number? m1) (number? m2)) (* m1 m2)
        :else (list '* m1 m2)))

(make-sum (make-product 'x 0) (make-product 1 'y))
; => y

(deriv '(+ x 3) 'x)
; 1

(deriv '(* x y) 'x)
; y

(deriv '(* (* x y) (+ x 3)) 'x)
; => (+ (* x y) (* y (+ x 3)))

(defn element-of-set? [x set]
  (cond (empty? set) false
        (= x (first set)) true
        :else (element-of-set? x (rest set))))

(element-of-set? 3 '(1 2 3 4))
; => true

(element-of-set? 9 '(1 2 3 4))
; => false

(element-of-set? 'y '(x y z w))
; => true

(element-of-set? 'k '(x y z w))
; => false

(defn adjoin-set [x set]
  (if (element-of-set? x set)
    set
    (cons x set)))

(adjoin-set 1 '())
; => (1)

(adjoin-set 1 '(1))
; => (1)

(defn intersection-set [set1 set2]
  (cond (or (empty? set1) (empty? set2)) '()
        (element-of-set? (first set1) set2) (cons (first set1) (intersection-set (rest set1) set2))
        :else (intersection-set (rest set1) set2)))

(intersection-set '(1 2) '(3 4))
; => ()

(intersection-set '(1 2) '(2 3))
; => (2)