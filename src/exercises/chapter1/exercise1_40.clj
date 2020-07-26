(ns exercises.chapter1.exercise1_40)

; Exercise 1.40:
; Define a procedure cubic that can be used together with the newtons-method procedure in expressions of the form
;
;   (newtons-method (cubic a b c) 1)
;
; to approximate zeros of the cubic x³ + ax² + bx + c.

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

(def dx 0.00001)

(defn deriv [g]
  (fn [x] (/ (- (g (+ x dx)) (g x))
             dx)))

(defn newton-transform [g]
  (fn [x] (- x (/ (g x) ((deriv g) x)))))

(defn newtons-method [g guess]
  (fixed-point (newton-transform g) guess))

(defn square [x]
  (* x x))

(defn cube [x]
  (* x x x))

(defn cubic [a b c]
  (fn [x] (+ (cube x)
             (* a (square x))
             (* b x)
             c)))

(newtons-method (cubic 1 2 3) 1.0)
; => -1.2756822036498454