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

Copyright (c)      - 2020 KR Endüstriyel Bilişim Ltd. Şti.

Copyright (c) 2013 - 2020 Automatak LLC


# Usage

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

To update your `.gitignore` files at any time, simply run:

### TCP Server

```
<dnp-tcp-server>
    <name>Channel3</name>
    <host>127.0.0.1</host>
    <port>20000</port>
    <slave>
    </slave>
    <master>
    </master>
</dnp-tcp-server>
```
### TCP Client

```
<dnp-tcp-client>
    <name>Channel1</name>
    <host>127.0.0.1</host>
    <port>20000</port>
    <adapter>0.0.0.0</adapter>
    <reconnect>
        <maxRetryDelay>PT1M</maxRetryDelay>
        <minRetryDelay>PT1S</minRetryDelay>
        <reconnectDelay>PT0S</reconnectDelay>
    </reconnect>
    <slave>
    </slave>
    <master>
    </master>
</dnp-tcp-client>
```
### Serial

```
<dnp-serial>
    <name>Channel1</name>
    <port>/dev/random</port>
    <baudRate>9600</baudRate>
    <dataBits>8</dataBits>
    <parity>None</parity>
    <stopBits>1</stopBits>
    <flowControl>None</flowControl>
    <reconnect>
        <maxRetryDelay>PT1M</maxRetryDelay>
        <minRetryDelay>PT1S</minRetryDelay>
        <reconnectDelay>PT0S</reconnectDelay>
    </reconnect>
    <slave>
    </slave>
    <master>
    </master>
</dnp-serial>
```
### Masters

```
    <master>
        <name>Master1</name>
        <zoneOffset>0</zoneOffset>
        <supportColdStart>false</supportColdStart>
        <supportWarmStart>false</supportWarmStart>
        <link>
            <localAddress>4</localAddress>
            <remoteAddress>3</remoteAddress>
            <master>true</master>
            <useconfirms>false</useconfirms>
            <keepAliveTimeout>PT1M</keepAliveTimeout>
            <responseTimeout>PT1S</responseTimeout>
            <numRetry>0</numRetry>
        </link>
        <controlQualifierMode>always_two_bytes</controlQualifierMode>
        <disableUnsolOnStartup>true</disableUnsolOnStartup>
        <eventScanOnEventsAvailableClassMask>
            <class0>false</class0>
            <class1>false</class1>
            <class2>false</class2>
            <class3>false</class3>
        </eventScanOnEventsAvailableClassMask>
        <ignoreRestartIIN>false</ignoreRestartIIN>
        <integrityOnEventOverflowIIN>true</integrityOnEventOverflowIIN>
        <maxRxFragSize>2048</maxRxFragSize>
        <maxTxFragSize>2048</maxTxFragSize>
        <responseTimeout>PT5S</responseTimeout>
        <startupIntegrityClassMask>
            <pointClass>Class0</pointClass>
            <pointClass>Class1</pointClass>
            <pointClass>Class2</pointClass>
            <pointClass>Class3</pointClass>
            <class0>true</class0>
            <class1>true</class1>
            <class2>true</class2>
            <class3>true</class3>
        </startupIntegrityClassMask>
        <taskRetryPeriod>PT5S</taskRetryPeriod>
        <taskStartTimeout>PT10S</taskStartTimeout>
        <timeSyncMode>None</timeSyncMode>
        <unsolClassMask>
            <pointClass>Class1</pointClass>
            <pointClass>Class2</pointClass>
            <pointClass>Class3</pointClass>
            <class0>false</class0>
            <class1>true</class1>
            <class2>true</class2>
            <class3>true</class3>
        </unsolClassMask>
        <memoryUnit xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="analogInputs">
        </memoryUnit>
    </master>
```
### Slaves(Outstations)

```
    <slave>
        <name>Outstation4</name>
        <zoneOffset>0</zoneOffset>
        <supportColdStart>false</supportColdStart>
        <supportWarmStart>false</supportWarmStart>
        <link>
            <localAddress>4</localAddress>
            <remoteAddress>3</remoteAddress>
            <master>false</master>
            <useconfirms>false</useconfirms>
            <keepAliveTimeout>PT1M</keepAliveTimeout>
            <responseTimeout>PT1S</responseTimeout>
            <numRetry>0</numRetry>
        </link>
        <allowUnsolicited>false</allowUnsolicited>
        <indexMode>Contiguous</indexMode>
        <maxControlsPerRequest>16</maxControlsPerRequest>
        <maxRxFragSize>2048</maxRxFragSize>
        <maxTxFragSize>2048</maxTxFragSize>
        <selectTimeout>PT10S</selectTimeout>
        <solicitedConfirmTimeout>PT5S</solicitedConfirmTimeout>
        <unsolicitedConfirmTimeout>PT5S</unsolicitedConfirmTimeout>
        <memoryUnit xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="analogInputs">
        </memoryUnit>
    </slave>

```
### MemoryUnits and Variables

Memory Units are homogeneous areas which keeps variables. Variables stands for primitive data that is relative to protocol. 

### Analog Inputs
```
        <memoryUnit xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="analogInputs">
            <eventBufferSize>0</eventBufferSize>
            <analogInput>
            </analogInput>
        </memoryUnit>
```
### Analog Outputs
```
    <memoryUnit xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="analogOutputs">
            <eventBufferSize>0</eventBufferSize>
            <analogOutput>
                <index>0</index>
                <pointClass>Class1</pointClass>
                <commandConfig>
                    <dataType>FLOAT</dataType>
                    <operateType>DirectOperate</operateType>
                </commandConfig>
                <deadband>0.0</deadband>
                <eventVariation>Group42Var1</eventVariation>
                <selectionRequired>false</selectionRequired>
                <staticVariation>Group40Var1</staticVariation>
            </analogOutput>
    </memoryUnit>
```
### Digital Inputs
```
    <memoryUnit xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="digitalInputs">
            <eventBufferSize>0</eventBufferSize>
            <digitalInput>
                <index>0</index>
                <pointClass>Class1</pointClass>
                <eventVariation>Group2Var1</eventVariation>
                <staticVariation>Group1Var2</staticVariation>
            </digitalInput>
    </memoryUnit>
```
### Digital Outputs
```
    <memoryUnit xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="digitalOutputs">
            <eventBufferSize>0</eventBufferSize>
            <digitalOutput>
                <index>0</index>
                <pointClass>Class1</pointClass>
                <commandConfig>
                    <controlCode>PULSE_ON</controlCode>
                    <count>0</count>
                    <offTime>PT0.5S</offTime>
                    <onTime>PT0.5S</onTime>
                    <operateType>DirectOperate</operateType>
                </commandConfig>
                <eventVariation>Group11Var1</eventVariation>
                <selectionRequired>false</selectionRequired>
                <staticVariation>Group10Var2</staticVariation>
            </digitalOutput>
    </memoryUnit>
```
### Counters
```
    <memoryUnit xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="counters">
            <eventBufferSize>0</eventBufferSize>
            <counter>
                <index>0</index>
                <pointClass>Class1</pointClass>
                <deadband>0</deadband>
                <eventVariation>Group22Var1</eventVariation>
                <staticVariation>Group20Var1</staticVariation>
            </counter>
    </memoryUnit>
```
### Frozen Counters
```
    <memoryUnit xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="frozenCounters">
            <eventBufferSize>0</eventBufferSize>
            <frozenCounter>
                <index>20</index>
                <pointClass>Class1</pointClass>
                <deadband>0</deadband>
                <eventVariation>Group23Var1</eventVariation>
                <staticVariation>Group21Var1</staticVariation>
            </frozenCounter>
    </memoryUnit>
```
### Double Digitals
```
    <memoryUnit xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="doubleDigitals">
            <eventBufferSize>0</eventBufferSize>
            <doubleDigital>
                <index>0</index>
                <pointClass>Class1</pointClass>
                <eventVariation>Group4Var1</eventVariation>
                <staticVariation>Group3Var2</staticVariation>
            </doubleDigital>
    </memoryUnit>
```
