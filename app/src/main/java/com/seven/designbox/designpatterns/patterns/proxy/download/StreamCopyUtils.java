package com.seven.designbox.designpatterns.patterns.proxy.download; /*
 * Copyright 2016 Seven_Tang <yihongyuelan@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.CountingOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamCopyUtils {
    private static final byte[] BUFFER_SIZE = new byte[65536]; //1024bytes * 64 = 64k

    public interface Listener {
        void onBytesCopied(long len);
    }

    public static void copy(InputStream from, OutputStream to, final Listener listener) throws IOException {
        if (listener == null) {
            throw new IllegalArgumentException("listener should not be null, if you don't want listener, use @copy(in,out) one");
        }
        IOUtils.copyLarge(from, new CountingOutputStream(to) {
            @Override
            protected void afterWrite(int n) throws IOException {
                super.afterWrite(n);
                listener.onBytesCopied(getByteCount());
            }
        }, BUFFER_SIZE);
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        IOUtils.copy(in, out);
    }
}
