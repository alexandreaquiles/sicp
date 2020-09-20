(ns exercises.chapter2.exercise2_13
  (:use exercises.chapter2.exercise2_7
        exercises.chapter2.exercise2_8
        exercises.chapter2.exercise2_10
        exercises.chapter2.exercise2_11
        exercises.chapter2.exercise2_12))

;Exercise 2.13:
; Show that under the assumption of small percentage tolerances
;   there is a simple formula for the approximate percentage
;   tolerance of the product of two intervals in terms of the
;   tolerances of the factors.
; You may simplify the problem by assuming that all numbers are
;   positive.

; l == lower bound
; h == higher bound
; c == center
; y == tolerance in percent

; Al = Ac - Ac*At
; Ah = Ac + Ac*At

; Bl = Bc - Bc*Bt
; Bh = Bc + Bc*Bt

; (A*B)l = (Ac - Ac*At) * (Bc - Bc*Bt)
;        = (Ac * Bc) + (Ac * -Bc*Bt) + (-Ac*At * Bc) + (-Ac*At * -Bc*Bt)
;        = AcBc - AcBcBt - AcBcAt + AcBcAtBt
;        = AcBc*(1 + AtBt) - AcBc*(At + Bt)

; (A*B)h = (Ac + Ac*At) * (Bc + Bc*Bt)
;        = (Ac * Bc) + (Ac * Bc*Bt) + (Ac*At * Bc) + (Ac*At * Bc*Bt)
;        = AcBc + AcBcBt + AcBcAt + AcBcAtBt
;        = AcBc*(1 + AtBt) + AcBc*(At + Bt)

; A*B = AcBc * (1 + AtBt)  +/- AcBc * (At + Bt)

; A*B center = AcBc * (1 + AtBt)
; A*B tolerance =  AcBc * (At + Bt)
; A*B tolerance in % =  AcBc * (At + Bt) / AcBc (1 + AtBt)

; tolerances 0 <= t < 1
; for small tolerances At and Bt,
;   At * Bt is orders of magnitude smaller
; example:
;   for At = Bt = 10^-3
;       At * Bt = 10^-6

; if we ignore AtBt, we get:

;   A*B = AcBc +/- AcBc * (At + Bt)

; A*B center = AcBc
; A*B tolerance =  AcBc * (At + Bt)
; A*B tolerance in % =  AcBc * (At + Bt) / AcBc = At + Bt

(def resistor-a (make-center-percent 15 0.1))
(def resistor-b (make-center-percent 12 0.01))

(def resistor-a-mul-b (mul-interval resistor-a resistor-b))

(center resistor-a-mul-b)
;=> 180.000018

(percent resistor-a-mul-b)
;=> 0.10999998900001315

; AcBc
(* 15 12)
; => 180

; AcBc * (At + Bt)
(* (* 15 12) (+ 0.001 0.0001))
; => 0.198

; AcBc * (At + Bt) / AcBc
(/ (* (* 15 12) (+ 0.001 0.0001)) (* 15 12))
; => 0.0011

; in percent
(* 0.0011 100)
; => 0.11
; ≃ (percent resistor-a-mul-b) => 0.10999998900001315
