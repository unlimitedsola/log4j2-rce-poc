task("runPayloadServer") {
    group = "log4j2-rce-poc"
    dependsOn(":payload-server:run")
}

task("runVictimApp") {
    group = "log4j2-rce-poc"
    dependsOn(":victim-app:bootRun")
}
