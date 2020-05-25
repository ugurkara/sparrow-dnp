/* 
 * Copyright 2020 KR ENDÜSTRİYEL BİLİŞİM LTD. ŞTİ..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sparrow.dnp;

import com.automatak.dnp3.AnalogOutputDouble64;
import com.automatak.dnp3.AnalogOutputFloat32;
import com.automatak.dnp3.AnalogOutputInt16;
import com.automatak.dnp3.AnalogOutputInt32;
import com.automatak.dnp3.CommandHandler;
import com.automatak.dnp3.ControlRelayOutputBlock;
import com.automatak.dnp3.OutstationStackConfig;
import com.automatak.dnp3.enums.CommandStatus;
import com.automatak.dnp3.enums.OperateType;
import com.sparrow.dnp.config.VariableConfig;
import com.sparrow.dnp.config.VariableUnitConfig;
import com.sparrow.dnp.config.SlaveDeviceConfig;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 *
 * @author ugurkara
 *
 */
public class OutstationDevice extends BaseDevice {

    private final OutstationStackConfig outstationStackConfig;
    private final BaseOutstationApplication baseOutstationApplication = new BaseOutstationApplication();

    public BaseOutstationApplication getOutstationApplication() {
        return baseOutstationApplication;
    }

    public LocalDateTime getLocalDateTime() {
        return baseOutstationApplication.getLocalDateTime();
    }

    private OutstationDevice(String name, OutstationStackConfig outstationStackConfig, ZoneId zoneId) {
        super(name);
        this.outstationStackConfig = outstationStackConfig;
    }

    public OutstationStackConfig getOutstationStackConfig() {
        return outstationStackConfig;
    }

    public static OutstationDevice newDevice(SlaveDeviceConfig config) {
        OutstationDevice monitor = new OutstationDevice(config.getName(), config.build(), ZoneId.ofOffset("", ZoneOffset.ofHours(config.getZoneOffset())));
        config.getMemoryUnits().forEach((VariableUnitConfig pointUnitConfigBuilder) -> {

            switch (pointUnitConfigBuilder.getType()) {
                case AnalogInput:
                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.AnalogInput analogInput = DnpVariable.AnalogInput.valueOf((VariableConfig.AnalogInputConfig) t);
                        monitor.getDatabase().getAnalogInputs().getItems().put(analogInput.getIndex(), analogInput);
                    });
                    break;

                case AnalogOutput:
                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.AnalogOutput analogOutput = DnpVariable.AnalogOutput.valueOf((VariableConfig.AnalogOutputConfig) t);
                        monitor.getDatabase().getAnalogOutputs().getItems().put(analogOutput.getIndex(), analogOutput);
                    });
                    break;

                case Counter:
                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.Counter counter = DnpVariable.Counter.valueOf((VariableConfig.CounterConfig) t);
                        monitor.getDatabase().getCounters().getItems().put(counter.getIndex(), counter);
                    });
                    break;

                case DigitalInput:
                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.DigitalInput digitalInput = DnpVariable.DigitalInput.valueOf((VariableConfig.DigitalInputConfig) t);
                        monitor.getDatabase().getDigitalInputs().getItems().put(digitalInput.getIndex(), digitalInput);
                    });

                    break;

                case DigitalOutput:
                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.DigitalOutput digitalOutput = DnpVariable.DigitalOutput.valueOf((VariableConfig.DigitalOutputConfig) t);
                        monitor.getDatabase().getDigitalOutputs().getItems().put(digitalOutput.getIndex(), digitalOutput);
                    });

                    break;

                case DoubleDigital:
                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.DoubleDigital doubleDigital = DnpVariable.DoubleDigital.valueOf((VariableConfig.DoubleDigitalConfig) t);
                        monitor.getDatabase().getDoubleDigitals().getItems().put(doubleDigital.getIndex(), doubleDigital);

                    });

                    break;

                case FrozenCounter:

                    pointUnitConfigBuilder.getItems().forEach((Object t) -> {
                        DnpVariable.FrozenCounter frozenCounter = DnpVariable.FrozenCounter.valueOf((VariableConfig.FrozenCounterConfig) t);
                        monitor.getDatabase().getFrozenCounters().getItems().put(frozenCounter.getIndex(), frozenCounter);
                    });

                    break;

            }

        });

        return monitor;

    }

    private final DefaultCommandHandler defaultCommandHandler = new DefaultCommandHandler();

    DefaultCommandHandler getDefaultCommandHandler() {
        return defaultCommandHandler;
    }

    class DefaultCommandHandler implements CommandHandler {

        @Override
        public void start() {

        }

        @Override
        public void end() {

        }

        @Override
        public CommandStatus selectCROB(ControlRelayOutputBlock command, int index) {

            boolean containsKey = getDatabase().getDigitalOutputs().getItems().containsKey(index);

            if (containsKey) {
                DnpVariable.DigitalOutput digitalOutput = getDatabase().getDigitalOutputs().getItems().get(index);
                digitalOutput.select();
                return CommandStatus.SUCCESS;
            }

            return CommandStatus.OUT_OF_RANGE;

        }

        @Override
        public CommandStatus selectAOI32(AnalogOutputInt32 command, int index) {

            boolean containsKey = getDatabase().getAnalogOutputs().getItems().containsKey(index);

            if (containsKey) {
                DnpVariable.AnalogOutput analogOutput = getDatabase().getAnalogOutputs().getItems().get(index);
                analogOutput.select();
                return CommandStatus.SUCCESS;
            }

            return CommandStatus.OUT_OF_RANGE;
        }

        @Override
        public CommandStatus selectAOI16(AnalogOutputInt16 command, int index) {

            boolean containsKey = getDatabase().getAnalogOutputs().getItems().containsKey(index);

            if (containsKey) {
                DnpVariable.AnalogOutput analogOutput = getDatabase().getAnalogOutputs().getItems().get(index);
                analogOutput.select();
                return CommandStatus.SUCCESS;
            }

            return CommandStatus.OUT_OF_RANGE;

        }

        @Override
        public CommandStatus selectAOF32(AnalogOutputFloat32 command, int index) {

            boolean containsKey = getDatabase().getAnalogOutputs().getItems().containsKey(index);

            if (containsKey) {
                DnpVariable.AnalogOutput analogOutput = getDatabase().getAnalogOutputs().getItems().get(index);
                analogOutput.select();
                return CommandStatus.SUCCESS;

            }

            return CommandStatus.OUT_OF_RANGE;

        }

        @Override
        public CommandStatus selectAOD64(AnalogOutputDouble64 command, int index) {

            boolean containsKey = getDatabase().getAnalogOutputs().getItems().containsKey(index);

            if (containsKey) {
                DnpVariable.AnalogOutput analogOutput = getDatabase().getAnalogOutputs().getItems().get(index);
                analogOutput.select();
                return CommandStatus.SUCCESS;

            }

            return CommandStatus.OUT_OF_RANGE;

        }

        @Override
        public CommandStatus operateCROB(ControlRelayOutputBlock command, int index, OperateType opType) {

            boolean containsKey = getDatabase().getDigitalOutputs().getItems().containsKey(index);

            if (containsKey) {
                DnpVariable.DigitalOutput digitalOutput = getDatabase().getDigitalOutputs().getItems().get(index);

                switch (command.function) {

                    case LATCH_OFF:
                        digitalOutput.setValue(false);
                        break;

                    case LATCH_ON:
                        digitalOutput.setValue(true);
                        break;

                    case PULSE_ON:
                        digitalOutput.setValue(true);
                        break;

                    case PULSE_OFF:
                        digitalOutput.setValue(false);
                        break;

                }

                return CommandStatus.SUCCESS;

            }

            return CommandStatus.OUT_OF_RANGE;

        }

        @Override
        public CommandStatus operateAOI32(AnalogOutputInt32 command, int index, OperateType opType) {

            boolean containsKey = getDatabase().getAnalogOutputs().getItems().containsKey(index);

            if (containsKey) {
                DnpVariable.AnalogOutput analogOutput = getDatabase().getAnalogOutputs().getItems().get(index);
                analogOutput.setValue((double) command.value);
                return CommandStatus.SUCCESS;

            }

            return CommandStatus.OUT_OF_RANGE;

        }

        @Override
        public CommandStatus operateAOI16(AnalogOutputInt16 command, int index, OperateType opType) {

            boolean containsKey = getDatabase().getAnalogOutputs().getItems().containsKey(index);

            if (containsKey) {
                DnpVariable.AnalogOutput analogOutput = getDatabase().getAnalogOutputs().getItems().get(index);
                analogOutput.setValue((double) command.value);
                return CommandStatus.SUCCESS;

            }

            return CommandStatus.OUT_OF_RANGE;

        }

        @Override
        public CommandStatus operateAOF32(AnalogOutputFloat32 command, int index, OperateType opType) {

            boolean containsKey = getDatabase().getAnalogOutputs().getItems().containsKey(index);

            if (containsKey) {
                DnpVariable.AnalogOutput analogOutput = getDatabase().getAnalogOutputs().getItems().get(index);
                analogOutput.setValue((double) command.value);
                return CommandStatus.SUCCESS;

            }

            return CommandStatus.OUT_OF_RANGE;

        }

        @Override
        public CommandStatus operateAOD64(AnalogOutputDouble64 command, int index, OperateType opType) {

            boolean containsKey = getDatabase().getAnalogOutputs().getItems().containsKey(index);

            if (containsKey) {
                DnpVariable.AnalogOutput analogOutput = getDatabase().getAnalogOutputs().getItems().get(index);
                analogOutput.setValue(command.value);
                return CommandStatus.SUCCESS;

            }

            return CommandStatus.OUT_OF_RANGE;

        }

    }

}
