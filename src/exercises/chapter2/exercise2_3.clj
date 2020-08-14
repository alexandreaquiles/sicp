(ns exercises.chapter2.exercise2_3
  (:use exercises.math
        exercises.chapter2.exercise2_2))

;Exercise 2.3:
; Implement a representation for rectangles in a plane.
; (Hint: You may want to make use of Exercise 2.2.)
; In  terms of your constructors and selectors, create procedures that compute the perimeter and the area of a given
;   rectangle.
; Now implement a different representation for rectangles.
; Can you design your system with suitable abstraction barriers, so that the same perimeter and area procedures will
;   work using either representation?

(defn make-rectangle [horizontal-segment vertical-segment]
  (cons horizontal-segment (cons vertical-segment '())))

(defn horizontal-rectangle-segment [rectangle]
  (first rectangle))

(defn vertical-rectangle-segment [rectangle]
  (first (rest rectangle)))

(defn euclidean-distance [point-a point-b]
  (let [xa (x-point point-a)
        xb (x-point point-b)
        ya (y-point point-a)
        yb (y-point point-b)]
    (assert (and (>= xb xa) (>= yb ya)))
    (sqrt (+ (square (- xb xa))
             (square (- yb ya))))))

(def i (make-point 3 10))
(def j (make-point 3 15))
(def k (make-point 5 15))

(euclidean-distance i j)
; => 5.000023178253949

(euclidean-distance j k)
; => 2.0000000929222947

(defn segment-length [segment]
  (euclidean-distance (start-segment segment) (end-segment segment)))

(segment-length (make-segment i j))
; => 5.000023178253949

(segment-length (make-segment j k))
; => 2.0000000929222947

(defn rectangle-perimeter [rectangle]
  (+ (segment-length (horizontal-rectangle-segment rectangle))
     (segment-length (vertical-rectangle-segment rectangle))))

(defn rectangle-area [rectangle]
  (* (segment-length (horizontal-rectangle-segment rectangle))
     (segment-length (vertical-rectangle-segment rectangle))))

(def hs (make-point 3 3))
(def he (make-point 15 3))
(def h (make-segment hs he))

(def vs (make-point 3 3))
(def ve (make-point 3 5))
(def v (make-segment vs ve))

(def r (make-rectangle h v))

(println (rectangle-perimeter r))
; a = 12, b=2 then p = 14
; => 14.000000105330983

(println (rectangle-area r))
; a = 12, b=2 then a = 24
; => 24.000001139884912

(defn make-rectangle-with-points [horizontal-start-point horizontal-end-point vertical-start-point vertical-end-point]
  (cons horizontal-start-point (cons horizontal-end-point (cons vertical-start-point (cons vertical-end-point '())))))

(defn horizontal-rectangle-segment [rectangle]
  (let [hs (first rectangle)
        he (first (rest rectangle))]
    (make-segment hs he)))

(defn vertical-rectangle-segment [rectangle]
  (let [vs (first (rest (rest rectangle)))
        ve (first (rest (rest (rest rectangle))))]
    (make-segment vs ve)))

(def hs (make-point 3 3))
(def he (make-point 15 3))

(def vs (make-point 3 3))
(def ve (make-point 3 5))

(def r (make-rectangle-with-points hs he vs ve))

(println (rectangle-perimeter r))
; a = 12, b=2 then p = 14
; => 14.000000105330983

(println (rectangle-area r))
; a = 12, b=2 then a = 24
; => 24.000001139884912