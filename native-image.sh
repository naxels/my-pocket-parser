# GraalVM 21.0.2
$GRAALVM_HOME/bin/native-image -jar target/my_pocket_parser-*.jar --no-fallback --enable-https --features=clj_easy.graal_build_time.InitClojureClasses
