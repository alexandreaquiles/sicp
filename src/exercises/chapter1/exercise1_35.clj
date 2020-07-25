(ns exercises.chapter1.exercise1_35)

; Exercise 1.35
; Show that the golden ratio φ (Section 1.2.2) is a fixed point of the transformation x → 1 + 1/x , and
;   use this fact to compute φ by means of the fixed-point procedure.

(defn abs [x]
  (if (< x 0)
    (- x)
    x))

(def tolerance 0.00001)
(defn fixed-point [f first-guess]
  (defn close-enough? [v1 v2]
    (< (abs (- v1 v2)) tolerance))
  (defn try* [guess]
    (let [next (f guess)]
      (if (close-enough? guess next)
        next
        (try* next))))
  (try* first-guess))

(defn fixed-point-φ []
  (fixed-point (fn [x] (+ 1 (/ 1 x))) 1.0))

(fixed-point-φ)
; => 1.6180327868852458