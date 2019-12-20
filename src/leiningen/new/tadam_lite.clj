(ns leiningen.new.tadam-lite
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "tadam-lite"))

(defn tadam-lite
  "Main"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' tadam-lite-template project.")
    (->files data
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             ["lein-env-example" "lein-env-example"]
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/{{sanitized}}/urls.clj" (render "urls.clj" data)]
             ["src/{{sanitized}}/views/public.clj" (render "views/public.clj" data)]
             ["resources/public/css/main.css" "resources/public/css/main.css"]
             ["resources/public/js/main.js" "resources/public/js/main.js"]
             ["resources/public/img/rabbit.svg" "resources/public/img/rabbit.svg"]
             ["resources/templates/layouts/base.html" "resources/templates/layouts/base.html"]
             ["resources/templates/public/welcome.html" "resources/templates/public/welcome.html"]
             ["resources/templates/public/404.html" "resources/templates/public/404.html"])))
