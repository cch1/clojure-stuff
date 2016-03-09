(ns sicp.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn cube
  [x]
  (* x x x))

(defn sum-integers
  [x y]
  (loop [a x
         b y
         s 0
         ]
    (if (> a b)
      s
      (recur (inc a) b (+ s a)))))

(defn sum-cubes
  [x y]
  (loop [a x
         b y
         s 0]
    (if (> a b)
      s
      (recur (inc a) b (+ s (cube a))))))

(defn pi-sum
  [x y]
  (loop [a x
         b y
         s 0]
    (if (> a b)
      s
      (recur (+ a 4) b (+ s (/ 1.0 (* a (+ a 2)))))
      )))
