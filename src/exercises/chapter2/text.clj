(ns exercises.chapter2.text
  (:use exercises.math)
  (:use exercises.picture))

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

;(draw wave)
;(draw wave frame1)
;(draw wave frame2)

(def wave2 (beside wave (flip-vert wave)))
;(draw wave2)

(def wave4 (below wave2 wave2))
;(draw wave4)

;; same as wave4
(defn flipped-pairs [painter]
  (let [painter2 (beside painter (flip-vert painter))]
    (below painter2 painter2)))
;(draw (flipped-pairs wave))

(def wave4 (flipped-pairs wave))
;(draw wave4)

(defn right-split [p n]
  (if (= n 0)
    p
    (let [smaller (right-split p (dec n))]
      (beside p (below smaller smaller)))))

;(draw (right-split wave 4))

(defn up-split [p n]
  (if (= n 0)
    p
    (let [smaller (up-split p (dec n))]
      (below p (beside smaller smaller)))))

;(draw (up-split wave 4))

(defn corner-split [p n]
  (if (= n 0)
    p
    (let [up (up-split p (dec n))
          right (right-split p (dec n))
          top-left (beside up up)
          bottom-right (below right right)
          top-right (corner-split p (dec n))]
      (beside (below p top-left)
              (below bottom-right top-right)))))

;(draw (corner-split wave 4))

(defn square-limit [p n]
  (let [quarter (corner-split p n)
        half (beside (flip-horiz quarter) quarter)]
    (below (flip-vert half) half)))

;(draw (square-limit wave 4))

(defn square-of-four [tl tr bl br]
  (fn [p]
    (let [top (beside (tl p) (tr p))
          bottom (beside (bl p) (br p))]
      (below bottom top))))

(defn flipped-pairs [painter]
  (let [combine4 (square-of-four identity flip-vert
                                 identity flip-vert)]
    (combine4 painter)))

;(draw (flipped-pairs wave))

(defn flipped-pairs [painter]
  ((square-of-four identity flip-vert identity flip-vert) painter))

;(draw (flipped-pairs wave))

(def flipped-pairs (square-of-four identity flip-vert identity flip-vert))

;(draw (flipped-pairs wave))

(defn square-limit [painter n]
  (let [quarter (corner-split painter n)]
    ((square-of-four flip-horiz
                     identity
                     (fn [p] (flip-vert (flip-horiz p)))
                     flip-vert) quarter)))

;(draw (square-limit wave 4))

(defn square-limit [painter n]
  (let [combine4 (square-of-four flip-horiz identity
                                 rotate180 flip-vert)]
    (combine4 (corner-split painter n))))

;(draw (square-limit wave 4))

(defn square-limit [painter n]
  (let [combine4 (square-of-four flip-horiz identity (comp flip-vert flip-horiz) flip-vert)]
    (combine4 (corner-split painter n))))

;(draw (square-limit wave 4))

(defn make-frame [origin edge1 edge2] (list origin edge1 edge2))
(defn origin-frame [frame] (first frame))
(defn edge1-frame [frame] (first (rest frame)))
(defn edge2-frame [frame] (first (rest (rest frame))))

(defn make-vect [x y] (list x y))
(def xcor-vect first)
(def ycor-vect second)

(defn add-vect [vect1 vect2]
  (make-vect (+ (xcor-vect vect1) (xcor-vect vect2))
             (+ (ycor-vect vect1) (ycor-vect vect2))))

(defn sub-vect [vect1 vect2]
  (make-vect (- (xcor-vect vect1) (xcor-vect vect2))
             (- (ycor-vect vect1) (ycor-vect vect2))))

(defn scale-vect [s vect]
  (make-vect (* s (xcor-vect vect)) (* s (ycor-vect vect))))

(defn frame-coordinates-map [frame]
  (fn [v]
    (add-vect
      (origin-frame frame)
      (add-vect (scale-vect (xcor-vect v) (edge1-frame frame))
                (scale-vect (ycor-vect v) (edge2-frame frame))))))

; from exercise 2.23
(defn for-each [proc items]
  (if (empty? items)
    true
    (do
      (proc (first items))
      (for-each proc (rest items)))))

(defn draw-line-from-vectors [start-vector end-vector]
  (let [x1 (xcor-vect start-vector)
        y1 (ycor-vect start-vector)
        x2 (xcor-vect end-vector)
        y2 (ycor-vect end-vector)]
    (draw-line [x1 y1] [x2 y2])))

(defn make-segment [start end] (list start end))
(defn start-segment [segment] (first segment))
(defn end-segment [segment] (second segment))

(defn segments->painter [segment-list]
  (fn [frame]
    (for-each (fn [segment] (draw-line-from-vectors
                              ((frame-coordinates-map frame) (start-segment segment))
                              ((frame-coordinates-map frame) (end-segment segment))))
              segment-list)))

(def a-whole-window-frame (make-frame (make-vect 0 0)
                                      (make-vect width 0)
                                      (make-vect 0 height)))

(def tetrimino-z
  (segments->painter (list (make-segment (make-vect 0.00 0.25) (make-vect 0.00 0.50))
                           (make-segment (make-vect 0.00 0.50) (make-vect 0.25 0.50))
                           (make-segment (make-vect 0.25 0.50) (make-vect 0.25 0.75))
                           (make-segment (make-vect 0.25 0.75) (make-vect 0.50 0.75))
                           (make-segment (make-vect 0.50 0.75) (make-vect 0.75 0.75))
                           (make-segment (make-vect 0.75 0.75) (make-vect 0.75 0.50))
                           (make-segment (make-vect 0.75 0.50) (make-vect 0.50 0.50))
                           (make-segment (make-vect 0.50 0.50) (make-vect 0.50 0.25))
                           (make-segment (make-vect 0.50 0.25) (make-vect 0.25 0.25))
                           (make-segment (make-vect 0.25 0.25) (make-vect 0.00 0.25)))))

;(draw tetrimino-z a-whole-window-frame)

(defn transform-painter [painter origin corner1 corner2]
  (fn [frame]
    (let [m (frame-coordinates-map frame)
          new-origin (m origin)]
      (painter (make-frame new-origin
                           (sub-vect (m corner1) new-origin)
                           (sub-vect (m corner2) new-origin))))))

(defn flip-vertically [painter]
  (transform-painter painter
                     (make-vect 0.0 1.0)
                     (make-vect 1.0 1.0)
                     (make-vect 0.0 0.0)))

;(draw (flip-vertically tetrimino-z) a-whole-window-frame)

(defn shrink-to-upper-right [painter]
  (transform-painter painter
                     (make-vect 0.5 0.5)
                     (make-vect 1.0 0.5)
                     (make-vect 0.5 1.0)))

;(draw (shrink-to-upper-right tetrimino-z) a-whole-window-frame)

(defn rotates-90 [painter]
  (transform-painter painter
                     (make-vect 1.0 0.0)
                     (make-vect 1.0 1.0)
                     (make-vect 0.0 0.0)))

;(draw (rotates-90 tetrimino-z) a-whole-window-frame)

(defn squash-inwards [painter]
  (transform-painter painter
                     (make-vect 0.0 0.0)
                     (make-vect 0.65 0.35)
                     (make-vect 0.35 0.65)))

;(draw (squash-inwards tetrimino-z) a-whole-window-frame)

(defn besides [painter1 painter2]
  (let [split-point (make-vect 0.5 0.0)
        paint-left (transform-painter painter1
                                      (make-vect 0.0 0.0)
                                      split-point
                                      (make-vect 0.0 1.0))
        paint-right (transform-painter painter2
                                       split-point
                                       (make-vect 1.0 0.0)
                                       (make-vect 0.5 1.0))]
    (fn [frame]
      (paint-left frame)
      (paint-right frame))))

;(draw (besides tetrimino-z (rotates-90 tetrimino-z)) a-whole-window-frame)

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

(element-of-set? 3 '(3 1 2 4))
; => true

(element-of-set? 9 '(3 1 2 4))
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

(defn element-of-set? [x set]
  (cond (empty? set) false
        (= x (first set)) true
        (< x (first set)) false
        :else (element-of-set? x (rest set))))

(element-of-set? 3 '(1 2 3 4))
; => true

(element-of-set? 9 '(1 2 3 4))
; => false

(defn intersection-set [set1 set2]
        (if (or (empty? set1) (empty? set2))
          '()
          (let [x1 (first set1) x2 (first set2)]
            (cond (= x1 x2) (cons x1 (intersection-set (rest set1) (rest set2)))
                  (< x1 x2) (intersection-set (rest set1) set2)
                  (< x2 x1) (intersection-set set1 (rest set2))))))

(intersection-set '(1 2) '(3 4))
; => ()

(intersection-set '(1 2) '(2 3))
; => (2)

(defn entry [tree] (first tree))
(defn left-branch [tree] (second tree))
(defn right-branch [tree] (first (rest (rest tree))))
(defn make-tree [entry left right]
        (list entry left right))

(defn element-of-set? [x set]
  (cond (empty? set) false
        (= x (entry set)) true
        (< x (entry set)) (element-of-set? x (left-branch set))
        (> x (entry set)) (element-of-set? x (right-branch set))))

(def an-example-tree
  (make-tree 7
             (make-tree 3
                        (make-tree 1 nil nil)
                        (make-tree 5 nil nil))

             (make-tree 9
                        nil
                        (make-tree 11 nil nil))))

(element-of-set? 0 an-example-tree)
; => false
(element-of-set? 1 an-example-tree)
; => true
(element-of-set? 6 an-example-tree)
; => false
(element-of-set? 9 an-example-tree)
; => true
(element-of-set? 11 an-example-tree)
; => true
(element-of-set? 13 an-example-tree)
; => false

(defn adjoin-set [x set]
  (cond (empty? set) (make-tree x nil nil)
        (= x (entry set)) set
        (< x (entry set)) (make-tree (entry set)
                                     (adjoin-set x (left-branch set))
                                     (right-branch set))
        (> x (entry set)) (make-tree (entry set)
                                     (left-branch set)
                                     (adjoin-set x (right-branch set)))))

(adjoin-set 6 an-example-tree)
; => (7
;       (3
;          (1 nil nil)
;          (5 nil
;             (6 nil nil)))
;       (9 nil
;          (11 nil nil)))


(adjoin-set 7
  (adjoin-set 6
    (adjoin-set 5
      (adjoin-set 4
        (adjoin-set 3
          (adjoin-set 2
            (adjoin-set 1 nil)))))))
; => (1 nil (2 nil (3 nil (4 nil (5 nil (6 nil (7 nil nil)))))))
