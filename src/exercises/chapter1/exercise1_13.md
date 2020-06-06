# Exercise 1.13.
Prove that Fib(n) is the closest integer to φ<sup>n</sup>/√5, where φ = (1 + √5)/2.

Hint: Let  ψ = (1 - √5)/2

Use induction and the definition of the Fibonacci numbers (see section 1.2.2) to prove that

Fib(n) = (φ<sup>n</sup> - ψ<sup>n</sup>)/√5.

___

Fib (n) = Fib (n-1) + Fib(n-2)

(φ<sup>n</sup> - ψ<sup>n</sup>)/√5 = (φ<sup>n-1</sup> - ψ<sup>n-1</sup>)/√5 + (φ<sup>n-2</sup> - ψ<sup>n-2</sup>)/√5

(φ<sup>n</sup> - ψ<sup>n</sup>)/√5.√5 = { (φ<sup>n-1</sup> - ψ<sup>n-1</sup>)/√5 + (φ<sup>n-2</sup> - ψ<sup>n-2</sup>)/√5 } . √5

(φ<sup>n</sup> - ψ<sup>n</sup>)/√5.√5 = (φ<sup>n-1</sup> - ψ<sup>n-1</sup>).√5/√5 + (φ<sup>n-2</sup> - ψ<sup>n-2</sup>).√5/√5

(φ<sup>n</sup> - ψ<sup>n</sup>) = (φ<sup>n-1</sup> - ψ<sup>n-1</sup>) + (φ<sup>n-2</sup> - ψ<sup>n-2</sup>)

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n-1</sup> - ψ<sup>n-1</sup> + φ<sup>n-2</sup> - ψ<sup>n-2</sup>

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n-1</sup>.φ/φ - ψ<sup>n-1</sup>.ψ/ψ + φ<sup>n-2</sup>.φ<sup>2</sup>/φ<sup>2</sup> - ψ<sup>n-2</sup>.ψ<sup>2</sup>/ψ<sup>2</sup>

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>/φ - ψ<sup>n</sup>/ψ + φ<sup>n</sup>/φ<sup>2</sup> - ψ<sup>n</sup>/ψ<sup>2</sup>

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>/φ + φ<sup>n</sup>/φ<sup>2</sup> - ψ<sup>n</sup>/ψ - ψ<sup>n</sup>/ψ<sup>2</sup>

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>.(1/φ + 1/φ<sup>2</sup>) - ψ<sup>n</sup>.(1/ψ + 1/ψ<sup>2</sup>)

φ = (1 + √5)/2 and ψ = (1 - √5)/2

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>.{1/(1 + √5)/2 + 1/\[(1 + √5)/2\]<sup>2</sup>} - ψ<sup>n</sup>.{1/(1 - √5)/2 + 1/\[(1 - √5)/2\]<sup>2</sup>}

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>.{1.2/2.(1 + √5)/2 + 1.4/4.\[(1 + √5)/2\]<sup>2</sup>} - ψ<sup>n</sup>.{1.2/2.(1 - √5)/2 + 1.4/4.\[(1 - √5)/2\]<sup>2</sup>}

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>.{1.2/2.(1 + √5)/2 + 1.4/\[2.(1 + √5)/2\]<sup>2</sup>} - ψ<sup>n</sup>.{1.2/2.(1 - √5)/2 + 1.4/\[2.(1 - √5)/2\]<sup>2</sup>}

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>.{2/(1 + √5) + 4/(1 + √5)<sup>2</sup>} - ψ<sup>n</sup>.{2/(1 - √5) + 4/(1 - √5)<sup>2</sup>}

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>.{2.(1 + √5)/(1 + √5).(1 + √5) + 4/(1 + √5)<sup>2</sup>} - ψ<sup>n</sup>.{2.(1 - √5)/(1 - √5).(1 - √5) + 4/(1 - √5)<sup>2</sup>}

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>.{2.(1 + √5)/(1 + √5)<sup>2</sup> + 4/(1 + √5)<sup>2</sup>} - ψ<sup>n</sup>.{2.(1 - √5)/(1 - √5)<sup>2</sup> + 4/(1 - √5)<sup>2</sup>}

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>.{\[2.(1 + √5) + 4\]/(1 + √5)<sup>2</sup>} - ψ<sup>n</sup>.{\[2.(1 - √5) + 4\]/(1 - √5)<sup>2</sup>}

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>.{(2 + 2√5 + 4)/(1 + √5)<sup>2</sup>} - ψ<sup>n</sup>.{(2 - 2√5 + 4)/(1 - √5)<sup>2</sup>}

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>.{(6 + 2√5)/(1 + √5)<sup>2</sup>} - ψ<sup>n</sup>.{(6 - 2√5)/(1 - √5)<sup>2</sup>}

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>.{(6 + 2√5)/(1<sup>2</sup> + 2√5 + √5<sup>2</sup>)} - ψ<sup>n</sup>.{(6 - 2√5)/(1<sup>2</sup> - 2√5 + √5<sup>2</sup>)}

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>.{(6 + 2√5)/(1 + 2√5 + 5)} - ψ<sup>n</sup>.{(6 - 2√5)/(1 - 2√5 + 5)}

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup>.{(6 + 2√5)/(6 + 2√5)} - ψ<sup>n</sup>.{(6 - 2√5)/(6 - 2√5)}

φ<sup>n</sup> - ψ<sup>n</sup> = φ<sup>n</sup> - ψ<sup>n</sup>

---


φ = (1 + √5)/2 ~=  1.6180 golden ratio which satisfies the equation φ<sup>2</sup> = φ + 1

ψ = (1 - √5)/2 ~= -0.6180

√5 ~= 2.2361

0 < -ψ < 1 < √5

0 < -ψ<sup>n</sup> < -ψ < 1 < √5

0 < -ψ<sup>n</sup>/√5 < -ψ<sup>n</sup> < -ψ < 1 < √5

0 < -ψ<sup>n</sup>/√5 < 1

Fib(n) = (φ<sup>n</sup> - ψ<sup>n</sup>)/√5

Fib(n) = φ<sup>n</sup>/√5 - ψ<sup>n</sup>/√5

Fib(n) - φ<sup>n</sup>/√5 = -ψ<sup>n</sup>/√5

-ψ<sup>n</sup>/√5 = Fib(n) - φ<sup>n</sup>/√5

Fib(n) + 0 < Fib(n) + (- ψ<sup>n</sup>/√5) < Fib(n) + 1

Fib(n) < Fib(n) -ψ<sup>n</sup>/√5 < Fib(n) + 1

Fib(n) < Fib(n) -(Fib(n) - φ<sup>n</sup>/√5)  < Fib(n) + 1

Fib(n) < Fib(n) - Fib(n) + φ<sup>n</sup>/√5 < Fib(n) + 1

Fib(n) < φ<sup>n</sup>/√5 < Fib(n) + 1

Reference: https://codology.net/post/sicp-solution-exercise-1-13/
(In this link there's an error: it states that 0 < ψ < 1, which is false.)
