(ns clojure-noob.core-test
  (:require [clojure.test :refer :all]
            [clojure-noob.core :refer :all]))

(deftest test-main
  (testing "main"
    (is (= (-main) 1))))

(deftest simple
  (is true))

(deftest pairs-of-values
  (let [args ["--server" "localhost"
              "--port" "8080"
              "--environment" "production"]]
    (is (=
         {:server "localhost"
            :port "8080"
            :environment "production"}
           (parse-args args)))))

