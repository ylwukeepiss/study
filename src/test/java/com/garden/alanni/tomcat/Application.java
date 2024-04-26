package com.garden.alanni.tomcat;
import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Application {

    private static final String sample =
            "Data is sent in a series of chunks. The Content-Length header is omitted in this case and at the beginning of each chunk you need to add the length of the current chunk in hexadecimal format, followed by '\\r\\n' and then the chunk itself, followed by another '\\r\\n'. The terminating chunk is a regular chunk, with the exception that its length is zero. It is followed by the trailer, which consists of a (possibly empty) sequence of entity header fields.\n"
                    + "compress\n"
                    + "A format using the Lempel-Ziv-Welch (LZW) algorithm. The value name was taken from the UNIX compress program, which implemented this algorithm.\n"
                    + "Like the compress program, which has disappeared from most UNIX distributions, this content-encoding is used by almost no browsers today, partly because of a patent issue (which expired in 2003).\n"
                    + "deflate\n"
                    + "Using the zlib structure (defined in RFC 1950), with the deflate compression algorithm (defined in RFC 1951).\n"
                    + "gzip\n"
                    + "A format using the Lempel-Ziv coding (LZ77), with a 32-bit CRC. This is originally the format of the UNIX gzip program. The HTTP/1.1 standard also recommends that the servers supporting this content-encoding should recognize x-gzip as an alias, for compatibility purposes.\n"
                    + "identity\n"
                    + "Indicates the identity function (i.e. no compression, nor modification). This token, except if explicitly specified, is always deemed acceptable.\n";
    public static final String CRLF = "\r\n";

    public static void main(String[] args) throws IOException, InterruptedException {
        int block = Integer.parseInt(args[0]);
        int blockCount = Integer.parseInt(args[1]);
        int sleep = Integer.parseInt(args[2]);
        int threads = Integer.parseInt(args[3]);
        int total = Integer.parseInt(args[4]);
        String url = args[5];

        AtomicInteger remainder = new AtomicInteger(total);
        AtomicInteger finished = new AtomicInteger(0);

        for (int i = 0; i < threads; i++) {
            new Thread(() -> {
                while (remainder.getAndDecrement() > 0) {
                    long start = System.currentTimeMillis();
                    try {
                        sendWithUrlConnection(block, sleep, blockCount, url);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        long cost = System.currentTimeMillis() - start;
                        System.out.println("task " + (finished.incrementAndGet()) + " finished, [total " + total + "], cost " + cost);
                    }
                }
            }).start();
        }
    }

    private static void sendWithUrlConnection(int chunkSize, int sleep, int chunkCount, String url)
            throws IOException, InterruptedException {
        URI uri = URI.create(url);

        final Socket socket = new Socket(uri.getHost(), uri.getPort());

        final OutputStream outputStream = socket.getOutputStream();

        PrintStream printWriter = new PrintStream(outputStream);
        printWriter.print("POST " + uri.getPath() + " HTTP/1.1" + CRLF);
        printWriter.print("Host: " + uri.getHost() + (uri.getPort() != -1 ? ":" + uri.getPort() : "") + CRLF);
        printWriter.print("Content-Type: text/plain" + CRLF);
        printWriter.print("Transfer-Encoding: chunked" + CRLF);
        printWriter.print(CRLF);

        printWriter.flush();

        int transferredBlockCount = 0;
        while (transferredBlockCount < chunkCount) {
            writeChunk(chunkSize, printWriter);
            transferredBlockCount++;
            Thread.sleep(sleep);
        }

        printWriter.print("0" + CRLF);
        printWriter.print(CRLF);
        printWriter.flush();

        byte[] buf = new byte[100];
        try (InputStream inputStream = socket.getInputStream()) {
            while (inputStream.read(buf) == 0) {
                // READ Until the first return data is received
            }
            final Thread thread = new Thread(() -> {
                try {
                    while ((inputStream.read(buf)) != -1) {
                        // JUST consume the stream
                    }
                } catch (IOException ignored) {
                } finally {
                    closeQuietly(printWriter);
                    closeQuietly(socket);
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException ignored) {
            // e.printStackTrace();
        }
    }

    private static void writeChunk(int blockSize, PrintStream printWriter) {
        printWriter.printf("%x" + CRLF, blockSize);
        for (int remainder = blockSize; remainder > 0;) {
            int size = IntStream.of(sample.length(), blockSize, remainder).min().orElse(0);
            printWriter.print(sample.substring(0, size));
            remainder -= size;
        }
        printWriter.print(CRLF);
        printWriter.flush();
    }

}

