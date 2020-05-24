<!--

    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

-->

# Sparrow DNP

Sparrow-DNP provides wrapper and adapter classes for the java library of the [Opendnp3](https://github.com/dnp3/opendnp3) in order to configure a DNP communication channel by XML format.

### Requirements

  * [Opendnp3](https://github.com/dnp3/opendnp3)
  
  Include
-------
JFreePDF is published to the Central Repository. You can include it in your projects with the following dependency:

    <dependency>
        <groupId>org.jfree</groupId>
        <artifactId>org.jfree.pdf</artifactId>
        <version>2.0</version>
    </dependency>

JFreePDF defines the module name `org.jfree.pdf`.

If you are using Java 8, an earlier version of this library (OrsonPDF) can be used instead:

    <!-- https://mvnrepository.com/artifact/com.orsonpdf/orsonpdf -->
    <dependency>
        <groupId>com.orsonpdf</groupId>
        <artifactId>orsonpdf</artifactId>
        <version>1.9</version>
    </dependency>

Build
-----
You can build JFreePDF from sources using Maven:

    mvn clean install

  
License
=============

Licensed under the terms of the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.html).
