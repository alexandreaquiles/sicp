(ns exercises.chapter1.exercise1_24)

;Exercise 1.24:
; Modify the timed-prime-test procedure of Exercise 1.22 to use fast-prime? (the Fermat method), and
;   test each of the 12 primes you found in that exercise.
; Since the Fermat test has Θ(log n) growth, how would you expect the time to test primes near 1,000,000
;   to compare with the time needed to test primes near 1000?
; Do your data bear this out? Can you explain any discrepancy you find?

(defn square [x] (* x x))

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (rem (square (expmod base (/ exp 2) m)) m)
        :else (rem (* base (expmod base (- exp 1) m)) m)))

(defn fermat-test [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (try-it (+ 1 (rand-int (- n 1)))))

(defn fast-prime? [n times]
  (cond (= times 0) true
        (fermat-test n) (fast-prime? n (- times 1))
        :else false))

(defn report-prime [elapsed-time]
  (print " *** ")
  (println (/ elapsed-time 1000.0) "μs")
  true)
(defn start-prime-test [n start-time]
  (if (fast-prime? n 100)
    (report-prime (- (System/nanoTime) start-time))))
(defn timed-prime-test [n]
  (newline)
  (print n)
  (start-prime-test n (System/nanoTime)))

;(timed-prime-test 1999)
;=> 1999 *** 161.063 μs

(defn search-for-primes [min]
  (loop [counter 1 min min]
    (if (<= counter 3)
      (let [odd-min (if (odd? min) min (inc min))
            was-prime (timed-prime-test odd-min)
            new-counter (if was-prime (inc counter) counter)]
        (recur new-counter (inc odd-min))))))

(search-for-primes 10000)
;10001
;10003
;10005
;10007 *** 709.914 μs
;
;10009 *** 1249.324 μs
;
;10011
;10013
;10015
;10017
;10019
;10021
;10023
;10025
;10027
;10029
;10031
;10033
;10035
;10037 *** 741.086 μs

(search-for-primes 100000)
;100001
;100003 *** 10898.551 μs
;
;100005
;100007
;100009
;100011
;100013
;100015
;100017
;100019 *** 2039.578 μs
;
;100021
;100023
;100025
;100027
;100029
;100031
;100033
;100035
;100037
;100039
;100041
;100043 *** 1636.642 μs

(search-for-primes 1000000)
;1000001
;1000003 *** 389.705 μs
;
;1000005
;1000007
;1000009
;1000011
;1000013
;1000015
;1000017
;1000019
;1000021
;1000023
;1000025
;1000027
;1000029
;1000031
;1000033 *** 395.491 μs
;
;1000035
;1000037 *** 389.834 μs

t₁₀₀₀₀   ~= 900.1080000000001
t₁₀₀₀₀₀  ~= 4858.257
t₁₀₀₀₀₀₀ ~= 391.6766666666667

; O(log 1000) x ~= number of digits ~= 3 x
; But t₁₀₀₀₀₀₀ is 0.43514407900681545 of t₁₀₀₀₀
; ???
