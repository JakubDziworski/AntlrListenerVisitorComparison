##Description

Antlr4 provides two ways of traversing syntax tree:
 * Listener (default)
 * Visitor

This project implements simple parser (takes input as a String (code) and returns Class object).
It uses both implementations (listener and visitor) where:
  * The language rules are the same (```SomeLanguage.g4```) for both implementations.
  * The parser output is the same for both implementations (```Test.java``` contains test covering this case)

You can compare two implementations to see the differences:
  * The Visitor implementation : ```VisitorOrientedParser.java```.
  * The Listener implementation : ```ListenerOrientedParser.java```.

The project is also described in [this blog post - Antlr 4 - Listener vs Visitor](http://jakubdziworski.github.io/java/2016/04/01/antlr_visitor_vs_listener.html)


### Building
```mvn package```

### Run sample parsing
```mvn package```
```java -jar parser/target/parser-1.0-SNAPSHOT-jar-with-dependencies.jar```
