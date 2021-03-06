(ns leiningen.new.tadam-lite
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "tadam-lite"))

(defn tadam-lite
  "Main"
  [name]
  (let [data {:name      name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' tadam-lite-template project.")
    (->files data
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             ["config.yaml" (render "resources/config.yaml" data)]
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/{{sanitized}}/urls.clj" (render "urls.clj" data)]
             ["src/{{sanitized}}/config.clj" (render "config.clj" data)]
             ["src/{{sanitized}}/views/public.clj" (render "views/public.clj" data)]
             ["resources/public/css/main.css" (render "resources/public/css/main.css" data)]
             ["resources/public/js/main.js" (render "resources/public/js/main.js" data)]
             ["resources/public/img/tadam.svg" (render "resources/public/img/tadam.svg" data)]
             ["resources/templates/layouts/base.html" (render "resources/templates/layouts/base.html" data)]
             ["resources/templates/public/welcome.html" (render "resources/templates/public/welcome.html" data)]
             ["resources/templates/public/404.html" (render "resources/templates/public/404.html" data)])))
