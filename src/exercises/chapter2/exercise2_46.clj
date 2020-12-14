(ns exercises.chapter2.exercise2_46)

; Exercise 2.46: A two-dimensional vector v running from the origin to a point
;   can be represented as a pair consisting of an x-coordinate and a y-coordinate.
; Implement a data abstraction for vectors by giving a constructor 'make-vect'
;   and corresponding selectors 'xcor-vect' and 'ycor-vect'.
; In terms of your selectors and constructor, implement procedures 'add-vect',
;   'sub-vect', and 'scale-vect' that perform the operations vector addition,
;   vector subtraction, and multiplying a vector by a scalar:
;
;   (x 1 , y 1 ) + (x 2 , y 2 ) = (x 1 + x 2 , y 1 + y 2 ),
;   (x 1 , y 1 ) − (x 2 , y 2 ) = (x 1 − x 2 , y 1 − y 2 ),
;                   s · (x , y) = (sx , sy).

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

