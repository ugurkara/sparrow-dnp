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
  * JAXB
  
  Include
-------
[Opendnp3](https://github.com/dnp3/opendnp3) is not published to the Central Repository. We need to install it into our Maven Local repository in order to use it as a project dependencies. You can include it in your projects with the following dependency

        <dependency>
            <groupId>com.automatak.dnp3</groupId>
            <artifactId>opendnp3-bindings</artifactId>
            <version>2.4.1-SNAPSHOT</version>
        </dependency>


If you are using Java 9 or newer

        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.0</version>
            <type>jar</type>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.bind</groupId>
            <artifactId>jaxb-core</artifactId>
            <version>2.3.0.1</version>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.bind</groupId>
            <artifactId>jaxb-impl</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>javax.activation</groupId>
            <artifactId>activation</artifactId>
            <version>1.1.1</version>
        </dependency>

Build
-----
You can build Sparrow-DNP from sources using Maven:

    mvn clean install

  
License
=============

Licensed under the terms of the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.html).


## Usage

### XML Configuration:

```
ls | list       list all available files
u | update      update all available gitignore files
g | generate    generate gitignore files
```

### Loading Configuration

```bash
$ joe g java    # outputs .gitignore file for java to stdout
```

To update your `.gitignore` files at any time, simply run:

```bash
$ joe u
```
### Connections

