(ns exercises.chapter1.exercise1_22)

;Exercise 1.22:
;
; Most Lisp implementations include a prim-
;itive called runtime that returns an integer that specifies
;the amount of time the system has been running (mea-
;sured, for example, in microseconds). The following timed-
;prime-test procedure, when called with an integer n, prints
;n and checks to see if n is prime. If n is prime, the procedure
;prints three asterisks followed by the amount of time used
;in performing the test.

; (define (timed-prime-test n)
;   (newline)
;   (display n)
;   (start-prime-test n (runtime)))
; (define (start-prime-test n start-time)
;   (if (prime? n)
;       (report-prime (- (runtime) start-time))))
; (define (report-prime elapsed-time)
;   (display " *** ")
;   (display elapsed-time))
;
; Using this procedure, write a procedure search-for-primes
;that checks the primality of consecutive odd integers in a
;specified range. Use your procedure to find the three small-
;est primes larger than 1000; larger than 10,000; larger than
;100,000; larger than 1,000,000. Note the time needed to test
;each prime. Since the testing algorithm has order of growth
;of Θ √n), you should expect that testing for primes around
;10,000 should take about √10 times as long as testing for
;primes around 1000. Do your timing data bear this out?
;How well do the data for 100,000 and 1,000,000 support the
;Θ(√n) prediction? Is your result compatible with the notion
;that programs on your machine run in time proportional to
;the number of steps required for the computation?

(defn square [x] (* x x))

(defn divides? [a b] (= (rem b a) 0))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (inc test-divisor))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn report-prime [elapsed-time]
  (print " *** ")
  (println (/ elapsed-time 1000.0) "μs")
  true)
(defn start-prime-test [n start-time]
  (if (prime? n)
    (report-prime (- (System/nanoTime) start-time))))
(defn timed-prime-test [n]
  (newline)
  (print n)
  (start-prime-test n (System/nanoTime)))

;(timed-prime-test 1999)
;=> 1999 *** 541.765 μs

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
;10007 *** 32.638 μs
;
;10009 *** 37.965 μs
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
;10037 *** 23.473 μs

(search-for-primes 100000)
;100001
;100003 *** 90.359 μs
;
;100005
;100007
;100009
;100011
;100013
;100015
;100017
;100019 *** 125.558 μs
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
;100043 *** 186.961 μs

(search-for-primes 1000000)
;1000001
;1000003 *** 358.816 μs
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
;1000033 *** 386.534 μs
;
;1000035
;1000037 *** 367.456 μs

; t₁₀₀₀₀   = (/ (+ 32.638 37.965 23.473) 3)    = 31.358666666666668
; t₁₀₀₀₀₀  = (/ (+ 90.359 125.558 186.961) 3)  = 134.2926666666667
; t₁₀₀₀₀₀₀ = (/ (+ 358.816 386.534 367.456) 3) = 370.93533333333335

;√10 = 3.16227766017

; t₁₀₀₀₀₀/t₁₀₀₀₀   = (/ 134.2926666666667 31.358666666666668) = 4.282473744632     ~= √18
; t₁₀₀₀₀₀₀/t₁₀₀₀₀₀ = (/ 370.93533333333335 134.2926666666667) = 2.7621413926796694 ~= √8
