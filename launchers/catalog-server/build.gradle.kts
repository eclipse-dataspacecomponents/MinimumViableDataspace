/*
*  Copyright (c) 2023 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
*
*  This program and the accompanying materials are made available under the
*  terms of the Apache License, Version 2.0 which is available at
*  https://www.apache.org/licenses/LICENSE-2.0
*
*  SPDX-License-Identifier: Apache-2.0
*
*  Contributors:
*       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - Initial API and Implementation
*
*/

plugins {
    `java-library`
    id("application")
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(project(":extensions:common-mocks"))
    implementation(project(":extensions:dcp-connector"))

    runtimeOnly(libs.bundles.connector) // base runtime
    runtimeOnly(libs.edc.api.management)
    runtimeOnly(libs.edc.api.management.config)
    runtimeOnly(libs.edc.controlplane.core) //default store impls, etc.
    runtimeOnly(libs.edc.controlplane.services) // aggregate services
    runtimeOnly(libs.edc.dsp) // protocol webhook
    runtimeOnly(libs.bundles.dcp) // DCP protocol impl
    runtimeOnly(libs.edc.api.dsp.config) // json-ld expansion

}

application {
    mainClass.set("org.eclipse.edc.boot.system.runtime.BaseRuntime")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    exclude("**/pom.properties", "**/pom.xm")
    mergeServiceFiles()
    archiveFileName.set("catalog-server.jar")
}

edcBuild {
    publish.set(false)
}
