(ns exercises.chapter2.exercise2_2)

; Exercise 2.2:
; Consider the problem of representing line segments in a plane.
; Each segment is represented as a pair of points: a starting point and an ending point.
; Define a constructor 'make-segment' and selectors 'start-segment' and 'end-segment'
;   that define the representation of segments in terms of points.
; Furthermore, a point can be represented as a pair of numbers:
;   the x coordinate and the y coordinate.
; Accordingly, specify a constructor 'make-point' and selectors 'x-point' and 'y-point'
;   that define this representation.
; Finally, using your selectors and constructors, define a procedure 'midpoint-segment'
;   that takes a line segment as argument and returns its midpoint
;   (the point whose coordinates are the average of the coordinates of the endpoints).
; To try your procedures, you’ll need a way to print points:
;
;     (define (print-point p)
;       (newline)
;       (display "(")
;       (display (x-point p))
;       (display ",")
;       (display (y-point p))
;       (display ")"))

(defn average [x y]
  (/ (+ x y) 2))

(defn make-point [x y]
  (cons x (cons y '())))

(defn x-point [p]
  (first p))

(defn y-point [p]
  (first (rest p)))

(defn midpoint [p1 p2]
  (make-point
    (average (x-point p1) (x-point p2))
    (average (y-point p1) (y-point p2))))

(defn print-point [p]
  (println "(" (x-point p) "," (y-point p) ")"))

(defn make-segment [starting-point ending-point]
  (cons starting-point (cons ending-point '())))

(defn start-segment [segment]
  (first segment))

(defn end-segment [segment]
  (first (rest segment)))

(defn midpoint-segment [segment]
  (midpoint (start-segment segment) (end-segment segment)))

(def a (make-point 3 10))
(print-point a)

(def b (make-point 3 15))
(print-point b)

(def s (make-segment a b))
(print-point (start-segment s))
(print-point (end-segment s))

(def m (midpoint-segment s))
(print-point m)