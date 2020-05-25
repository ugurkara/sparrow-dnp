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

import com.automatak.dnp3.StackStatistics;
import com.sparrow.dnp.BaseDevice.LinkLayerStatistics.TransportStatistics;


/**
 *
 * @author KR INDUSTRIAL IT
 */

public class BaseDevice {


    private final Database database = new Database();
    private final Statistics statistics = new Statistics();
    private long timeMillis = System.currentTimeMillis();
    
    public Statistics getStatistics() {
        return statistics;
    }

    public Long getTimeMillis() {
        return timeMillis;
    }

    private final String name;

    public BaseDevice(String name) {
        this.name = name;
    }

    public Database getDatabase() {
        return database;
    }

    public String getName() {
        return name;
    }

    public class Statistics {

        private final LinkLayerStatistics linkLayerStatistics = new LinkLayerStatistics();
        private final TransportStatistics transportStatistics = new TransportStatistics();

        public LinkLayerStatistics getLinkLayerStatistics() {
            return linkLayerStatistics;
        }

        public TransportStatistics getTransportStatistics() {
            return transportStatistics;

        }

        protected void update(StackStatistics stackStatistics) {
            getLinkLayerStatistics().numBadMasterBit = stackStatistics.link.numBadMasterBit;
            getLinkLayerStatistics().numUnexpectedFrame = stackStatistics.link.numUnexpectedFrame;
            getLinkLayerStatistics().numUnknownDestination = stackStatistics.link.numUnknownDestination;
            getLinkLayerStatistics().numUnknownSource = stackStatistics.link.numUnknownSource;

            getTransportStatistics().numTransportBufferOverflow = stackStatistics.transport.numTransportBufferOverflow;
            getTransportStatistics().numTransportDiscard = stackStatistics.transport.numTransportDiscard;
            getTransportStatistics().numTransportErrorRx = stackStatistics.transport.numTransportErrorRx;
            getTransportStatistics().numTransportIgnore = stackStatistics.transport.numTransportIgnore;
            getTransportStatistics().numTransportRx = stackStatistics.transport.numTransportRx;
            getTransportStatistics().numTransportTx = stackStatistics.transport.numTransportTx;
        }

    }


    public static class LinkLayerStatistics {

        private long numBadMasterBit = 0;
        private long numUnexpectedFrame = 0;
        private long numUnknownDestination = 0;
        private long numUnknownSource = 0;

        public long getNumBadMasterBit() {
            return numBadMasterBit;
        }

        public long getNumUnexpectedFrame() {
            return numUnexpectedFrame;
        }


        public long getNumUnknownDestination() {
            return numUnknownDestination;
        }


        public long getNumUnknownSource() {
            return numUnknownSource;
        }


        public static class TransportStatistics {
            
            private long numTransportBufferOverflow = 0;
            private long numTransportDiscard = 0;
            private long numTransportErrorRx = 0;
            private long numTransportIgnore = 0;
            private long numTransportRx = 0;
            private long numTransportTx = 0;

            public long getNumTransportBufferOverflow() {
                return numTransportBufferOverflow;
            }

            public long getNumTransportDiscard() {
                return numTransportDiscard;
            }

            public long getNumTransportErrorRx() {
                return numTransportErrorRx;

            }

            public long getNumTransportIgnore() {
                return numTransportIgnore;
            }

            public long getNumTransportRx() {
                return numTransportRx;
            }


            public long getNumTransportTx() {
                return numTransportTx;
            }
            
        }
    }

}