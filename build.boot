(require '[boot.core :refer :all]
         '[boot.task.built-in :refer :all])

(task-options!
  pom {:project 'hoplon-ui-reload-example
       :version "1.0"})

(set-env!
  :dependencies
  `[[boot/core ~*boot-version* :scope "compile"]
    [darongmean/boot-lein-generate "0.1.1" :scope "test"]
    [adzerk/bootlaces "0.1.13" :scope "test"]

    [org.clojure/clojure "1.8.0" :scope "compile"]
    [org.clojure/clojurescript "1.9.225" :scope "compile"]

    [hoplon/hoplon "6.0.0-alpha16" :scope "compile"]
    [adzerk/boot-cljs "1.7.228-1" :scope "compile"]
    [adzerk/boot-reload "0.4.12" :scope "compile"]
    [hoplon/boot-hoplon "0.2.4" :scope "compile"]
    [hoplon/ui "0.0.1-SNAPSHOT" :scope "compile"]
    [tailrecursion/boot-static "0.0.1-SNAPSHOT"]
    [binaryage/devtools "0.8.1"]]
  :source-paths #{"src"}
  :asset-paths #{"assets"})

(require '[adzerk.bootlaces :refer :all])
(bootlaces! (:version (meta pom)))

(require '[darongmean.boot-lein-generate :refer [lein-generate]])
(lein-generate)

(require
  '[tailrecursion.boot-static :refer [serve]]
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-reload :refer [reload]]
  '[hoplon.boot-hoplon :refer [hoplon prerender]])

(deftask dev
  []
  (task-options!
    repl {:server true}
    reload {:on-jsload 'app.main/on-jsload}
    cljs {:optimizations    :none
          :compiler-options {:parallel-build true}})
  (comp
    (watch)
    (speak)
    ;(checkout :dependencies '[[hoplon/ui "0.0.1-SNAPSHOT"]])
    (hoplon)
    (reload)
    (cljs)
    (serve :port 8081)))
