(ns cljs-http-cli.core
  (:require [cljs.nodejs :as nodejs]
            [clojure.tools.cli :refer [parse-opts]]
            [cljs.core.async :refer [chan <! >!] :as async]
            [cljs-http.client :as http])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(nodejs/enable-util-print!)

(set! js/XMLHttpRequest (nodejs/require "xhr2"))

(def options-spec
  [["-h" "--help"]])

(defn req [url]
  (let [outch (chan)]
    (go
      (let [response (<! (http/get url
                                   {:with-credentials? false
                                    :query-params {}}))
            body (get response :body)]
        (>! outch body)))
    outch))

(defn run [args]
  (parse-opts args options-spec))

(defn -main []
  (go
    (let [body (<! (req "http://www.google.co.jp"))]
      (println body))))

(set! *main-cli-fn* -main)
