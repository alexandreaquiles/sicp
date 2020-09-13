(ns exercises.chapter2.exercise2_11
  (:use exercises.chapter2.exercise2_7
        exercises.chapter2.exercise2_8
        exercises.chapter2.exercise2_10))

; Exercise 2.11:
; In passing, Ben also cryptically comments:
; “By testing the signs of the endpoints of the intervals, it is possible to break
;   'mul-interval' into nine cases, only one of which requires more than two multiplications.”
; Rewrite this procedure using Ben’s suggestion.

; rjk
; nine combinations of legal interval signs (eg, an interval cannot be +- because any positive number is greater than any negative.
; patt |  min  |  max
; ++++ | al bl | ah bh
; ++-+ | ah bl | ah bh
; ++-- | ah bl | al bh
; -+++ | al bh | ah bh
; -+-+ | trouble case
; -+-- | ah bl | al bl
; --++ | al bh | ah bl
; ---+ | al bh | al bl
; ---- | ah bh | al bl
; http://community.schemewiki.org/?sicp-ex-2.11

(defn pos? [n] (>= n 0))
(defn neg? [n] (< n 0))

(intern 'exercises.chapter2.exercise2_7
        'mul-interval
        (fn [x y]
            (let [lbx (lower-bound x)
                  ubx (upper-bound x)
                  lby (lower-bound y)
                  uby (upper-bound y)]
              (cond
                (and (pos? lbx) (pos? ubx) (pos? lby) (pos? uby)) (make-interval (* lbx lby) (* ubx uby))
                (and (pos? lbx) (pos? ubx) (neg? lby) (pos? uby)) (make-interval (* ubx lby) (* ubx uby))
                (and (pos? lbx) (pos? ubx) (neg? lby) (neg? uby)) (make-interval (* ubx lby) (* lbx uby))
                (and (neg? lbx) (pos? ubx) (pos? lby) (pos? uby)) (make-interval (* lbx uby) (* ubx uby))
                (and (neg? lbx) (pos? ubx) (neg? lby) (neg? uby)) (make-interval (* ubx lby) (* lbx lby))
                (and (neg? lbx) (neg? ubx) (pos? lby) (pos? uby)) (make-interval (* lbx uby) (* ubx lby))
                (and (neg? lbx) (neg? ubx) (neg? lby) (pos? uby)) (make-interval (* lbx uby) (* lbx lby))
                (and (neg? lbx) (neg? ubx) (neg? lby) (neg? uby)) (make-interval (* ubx uby) (* lbx lby))
                (and (neg? lbx) (pos? ubx) (neg? lby) (pos? uby))   (let [p1 (* (lower-bound  x) (lower-bound y))
                                                                          p2 (* (lower-bound  x) (upper-bound y))
                                                                          p3 (* (upper-bound x) (lower-bound y))
                                                                          p4 (* (upper-bound x) (upper-bound y))]
                                                                      (make-interval (min p1 p2 p3 p4)
                                                                                     (max p1 p2 p3 p4)))
                :else (throw (IllegalArgumentException. "invalid combination of intervals"))))))

(assert (= '(27.3258 36.9138)
           (mul-interval resistor-1 resistor-2)))

(mul-interval (make-interval 1 2) (make-interval 3 4))
;=> (3 8)

(mul-interval (make-interval 1 2) (make-interval -3 4))
;=> (-6 8)

(mul-interval (make-interval 1 2) (make-interval -4 -3))
;=> (-8 -3)

(mul-interval (make-interval -1 2) (make-interval 3 4))
;=> (-4 8)

(mul-interval (make-interval -1 2) (make-interval -4 -3))
;=> (-8 4)

(mul-interval (make-interval -2 -1) (make-interval 3 4))
;=> (-8 -3)

(mul-interval (make-interval -2 -1) (make-interval -3 4))
;=> (-8 6)

(mul-interval (make-interval -2 -1) (make-interval -4 3))
;=> (-6 8)

(mul-interval (make-interval -1 2) (make-interval -3 4))
;=> (-6 8)
