(ns fpwd.core)

(def filename "suspects.csv")

(def headers->keywords {"Name" :name
                        "Glitter Index" :glitter-index})

(defn str->int
  [str]
  (Integer. str))

(def conversions
  {:name identity :glitter-index str->int})

(defn parse
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))
