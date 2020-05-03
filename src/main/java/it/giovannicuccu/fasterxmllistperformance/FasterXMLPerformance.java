package it.giovannicuccu.fasterxmllistperformance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import it.giovannicuccu.fasterxmllistperformance.BenchmarkConfig;
import it.giovannicuccu.fasterxmllistperformance.model.Persona;
import it.giovannicuccu.fasterxmllistperformance.model.PersoneForBenchmark;
import org.jeasy.random.EasyRandom;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class FasterXMLPerformance extends PerformanceBaseClass {

    private ObjectMapper objectMapper;
    private ObjectReader objectReaderPersone;
    private ObjectWriter objectWriterPersone;
    private ObjectReader objectReaderPersoneList;
    private ObjectWriter objectWriterPersoneList;


    private CollectionType typeReference =
            TypeFactory.defaultInstance().constructCollectionType(List.class, Persona.class);

    public FasterXMLPerformance() {
        objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectReaderPersone=objectMapper.readerFor(PersoneForBenchmark.class);
        objectWriterPersone=objectMapper.writerFor(PersoneForBenchmark.class);
        objectReaderPersoneList=objectMapper.readerFor(Persona.class);
        objectWriterPersoneList=objectMapper.writerFor(Persona.class);
    }

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void fasterXMLStandard(BenchmarkConfig config, Blackhole bh) throws Exception {
        testMapper(config.persone, bh, objectMapper);
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void fasterXMLStandardRead(BenchmarkConfig config, Blackhole bh) throws Exception {
        testMapperRead(config.personeStr, bh, objectMapper);
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void fasterXMLStandardWrite(BenchmarkConfig config, Blackhole bh) throws Exception {
        testMapperWrite(config.persone, bh, objectMapper);
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void fasterXMLStandardValues(BenchmarkConfig config, Blackhole bh) throws Exception {
        testMapperPersoneReaderWriterValues(config.persone, bh, objectReaderPersoneList, objectWriterPersoneList);
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void fasterXMLStandardHolder(BenchmarkConfig config, Blackhole bh) throws Exception {
        testMapperHolder(config.personeHolderForBenchmark, bh, objectMapper);
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void fasterXMLStandardPersone(BenchmarkConfig config, Blackhole bh) throws Exception {
        testMapperPersone(config.personeForBenchmark, bh, objectMapper);
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void fasterXMLStandardPersoneRefreshData(BenchmarkConfig config, Blackhole bh) throws Exception {
        EasyRandom easyRandom = new EasyRandom();
        PersoneForBenchmark personeForBenchmark=new PersoneForBenchmark();
        personeForBenchmark.setPersona0(easyRandom.nextObject(Persona.class));
        personeForBenchmark.setPersona1(easyRandom.nextObject(Persona.class));
        personeForBenchmark.setPersona2(easyRandom.nextObject(Persona.class));
        personeForBenchmark.setPersona3(easyRandom.nextObject(Persona.class));
        personeForBenchmark.setPersona4(easyRandom.nextObject(Persona.class));
        personeForBenchmark.setPersona5(easyRandom.nextObject(Persona.class));
        personeForBenchmark.setPersona6(easyRandom.nextObject(Persona.class));
        personeForBenchmark.setPersona7(easyRandom.nextObject(Persona.class));
        personeForBenchmark.setPersona8(easyRandom.nextObject(Persona.class));
        personeForBenchmark.setPersona9(easyRandom.nextObject(Persona.class));
        testMapperPersone(personeForBenchmark, bh, objectMapper);
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void fasterXMLStandardTypeReference(BenchmarkConfig config, Blackhole bh) throws Exception {
        testMapperWithTypeReference(config.persone, bh, objectMapper);
    }

/*    @Threads(6)
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void fasterXMLStandardPersoneMultiThreadReaderWriter(BenchmarkConfig config, Blackhole bh) throws Exception {
        testMapperPersoneReaderWriter(config.personeForBenchmark, bh, objectReaderPersone, objectWriterPersone);
    }

    @Threads(6)
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void fasterXMLStandardPersoneMultiThread(BenchmarkConfig config, Blackhole bh) throws Exception {
        testMapperPersone(config.personeForBenchmark, bh, objectMapper);
    }*/


}
