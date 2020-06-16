(ns exercises.chapter1.exercise1_23)

;Exercise 1.23:
; The smallest-divisor procedure shown at the start of this section does lots of needless testing:
;   After it checks to see if the number is divisible by 2 there is no point in checking to see if
;   it is divisible by any larger even numbers.
; This suggests that the values used for test-divisor should not be 2, 3, 4, 5, 6, . . .,
;   but rather 2, 3, 5, 7, 9, . . ..
; To implement this change, define a procedure next that returns 3 if its input is equal to 2
;   and otherwise returns its input plus 2. Modify the smallest-divisor procedure to use
;   (next test-divisor) instead of (+ test-divisor 1) .
; With timed-prime-test incorporating this modified version of smallest-divisor,
;   run the test for each of the 12 primes found in Exercise 1.22.
; Since this modification halves the number of test steps, you should expect it to run about
;   twice as fast. Is this expectation confirmed? If not, what is the observed ratio of the
;   speeds of the two algorithms, and how do you explain the fact that it is different from 2?

(defn square [x] (* x x))

(defn divides? [a b] (= (rem b a) 0))

(defn next [n]
  (if (= n 2 )
    3
    (+ n 2)))

(assert (= 3 (next 2)))
(assert (= 5 (next 3)))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (next test-divisor))))

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
;=> 1999 *** 161.063 μs

(defn search-for-primes [min]
  (loop [counter 1 min min]
    (if (<= counter 3)
      (let [odd-min (if (odd? min) min (inc min))
            was-prime (timed-prime-test odd-min)
            new-counter (if was-prime (inc counter) counter)]
        (recur new-counter (inc odd-min))))))

(search-for-primes 10000)
;(search-for-primes 10000)
;
;10001
;10003
;10005
;10007 *** 34.983 μs
;
;10009 *** 37.568 μs
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
;10037 *** 15.594 μs

(search-for-primes 100000)
;100001
;100003 *** 84.908 μs
;
;100005
;100007
;100009
;100011
;100013
;100015
;100017
;100019 *** 132.047 μs
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
;100043 *** 88.271 μs

(search-for-primes 1000000)
;1000001
;1000003 *** 220.525 μs
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
;1000033 *** 213.469 μs
;
;1000035
;1000037 *** 289.625 μs

; t₁₀₀₀₀   = (/ (/ (+ 34.983 37.568 15.594) 3) = 29.38166666666666
; t₁₀₀₀₀₀  = (/ (+ 84.908 132.047 88.271) 3)   = 101.742
; t₁₀₀₀₀₀₀ = (/ (+ 220.525 213.469 289.625) 3) = 241.20633333333333

;√10 = 3.16227766017

; t₁₀₀₀₀₀/t₁₀₀₀₀   = (/ 101.742 29.38166666666666) = 3.4627715695728636 ~= √12
; t₁₀₀₀₀₀₀/t₁₀₀₀₀₀ = (/ 241.20633333333333 101.742) = 2.370764613761606 ~= √6

;t₁₀₀₀₀inc/t₁₀₀₀₀next     = (/ 31.358666666666668 29.38166666666666)  = 1.0672868568835445
;t₁₀₀₀₀₀inc/t₁₀₀₀₀₀next   = (/ 134.2926666666667 101.742)             = 1.3199334263791422
;t₁₀₀₀₀₀₀inc/t₁₀₀₀₀₀₀next = (/ 370.93533333333335 241.20633333333333) = 1.5378341364723702

; The ratios for the increment and next version are not exactly 2.
; The computations are extremely fast in modern computers.
; Probably, the greater the number to be tested, closer to 2.
