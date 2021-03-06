(defproject cljs-http-cli "0.1.0-SNAPSHOT"
  :description "FIXME"
  :url "https://github.com/rinx/cljs-http-cli"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :clean-targets ["build" :target-path]

  :dependencies [[org.clojure/clojure "1.10.0-alpha4"]
                 [org.clojure/clojurescript "1.10.312"]
                 [org.clojure/spec.alpha "0.1.143"]
                 [org.clojure/core.async "0.4.490"]
                 [io.nervous/cljs-nodejs-externs "0.2.0"]
                 [org.clojure/tools.cli "0.4.1"]
                 [cljs-http "0.1.45"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-npm "0.6.2"]
            [org.bodil/lein-noderepl "0.1.11"]]

  :npm {:dependencies [[source-map-support "0.4.0"]
                       [xhr2 "latest"]]
        :devDependencies [[pkg "4.3.4"]]
        :package {:scripts {:pkg "pkg -t node10-linux-x64 -c package-lock.json build/main.js"
                            :pkg-win "pkg -t node10-win-x64 -c package-lock.json build/main.js"
                            :pkg-mac "pkg -t node10-macos-x64 -c package-lock.json build/main.js"}}}

  :aliases {"build" ["cljsbuild" "once" "main"]
            "build-auto" ["cljsbuild" "auto" "main"]
            "pkg" ["do"
                   ["npm" "install"]
                   "build"
                   ["npm" "run" "pkg"]]}

  :cljsbuild {:builds [{:id "main"
                        :source-paths ["src"]
                        :compiler {:output-to "build/main.js"
                                   :output-dir "build/js"
                                   :optimizations :advanced
                                   :target :nodejs
                                   :source-map "build/main.js.map"}}]})
