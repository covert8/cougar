/*
 * Copyright 2013, The Sporting Exchange Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.betfair.cougar.netutil.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.betfair.cougar.netutil.nio.message.ResponseMessage;
import org.apache.mina.common.IoSession;


public class ByteArrayOutputStreamWithIoSession extends ByteArrayOutputStream {
	
	private final IoSession ioSession;
    private long correlationId;
	
	public ByteArrayOutputStreamWithIoSession (IoSession ioSession, long correlationId) {
		this.ioSession = ioSession;
        this.correlationId = correlationId;
    }
	
	
	@Override
	public void flush() throws IOException {
        ioSession.write(new ResponseMessage(correlationId, toByteArray()));
	}		
}