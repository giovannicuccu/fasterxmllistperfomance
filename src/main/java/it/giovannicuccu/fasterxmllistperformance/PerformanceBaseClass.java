package it.giovannicuccu.fasterxmllistperformance;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import it.giovannicuccu.fasterxmllistperformance.model.Persona;
import it.giovannicuccu.fasterxmllistperformance.model.PersoneForBenchmark;
import it.giovannicuccu.fasterxmllistperformance.model.PersoneHolderForBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;


@State(Scope.Benchmark)
public class PerformanceBaseClass {


    private CollectionType typeReference =
            TypeFactory.defaultInstance().constructCollectionType(List.class, Persona.class);


    protected void testMapperWithTypeReference(List<Persona> persone, Blackhole bh, ObjectMapper jsonMapper) throws Exception {
        String personeAsStr=jsonMapper.writeValueAsString(persone);
        bh.consume(personeAsStr);
        List<Persona> personeDeser=jsonMapper.readValue(personeAsStr,typeReference);
        bh.consume(personeDeser);
    }

    protected void testMapper(List<Persona> persone, Blackhole bh,ObjectMapper jsonMapper) throws Exception {
        String personeAsStr=jsonMapper.writeValueAsString(persone);
        bh.consume(personeAsStr);
        List<Persona> personeDeser=jsonMapper.readValue(personeAsStr,List.class);
        bh.consume(personeDeser);
    }

    protected void testMapperRead(String personeAsStr, Blackhole bh,ObjectMapper jsonMapper) throws Exception {
        List<Persona> personeDeser=jsonMapper.readValue(personeAsStr,List.class);
        bh.consume(personeDeser);
    }

    protected void testMapperWrite(List<Persona> persone, Blackhole bh,ObjectMapper jsonMapper) throws Exception {
        String personeAsStr=jsonMapper.writeValueAsString(persone);
        bh.consume(personeAsStr);
    }

    protected void testMapperHolder(PersoneHolderForBenchmark persone, Blackhole bh, ObjectMapper jsonMapper) throws Exception {
        String personeAsStr=jsonMapper.writeValueAsString(persone);
        bh.consume(personeAsStr);
        PersoneHolderForBenchmark personeDeser=jsonMapper.readValue(personeAsStr,PersoneHolderForBenchmark.class);
        bh.consume(personeDeser);
    }

    protected void testMapperPersone(PersoneForBenchmark personeForBenchmark, Blackhole bh,ObjectMapper jsonMapper) throws Exception {
        String personeAsStr=jsonMapper.writeValueAsString(personeForBenchmark);
        bh.consume(personeAsStr);
        PersoneForBenchmark personeDeser=jsonMapper.readValue(personeAsStr,PersoneForBenchmark.class);
        bh.consume(personeDeser);
    }

    protected void testMapperPersoneReaderWriter(PersoneForBenchmark personeForBenchmark, Blackhole bh, ObjectReader jsonReader, ObjectWriter jsonWriter) throws Exception {
        String personeAsStr=jsonWriter.writeValueAsString(personeForBenchmark);
        bh.consume(personeAsStr);
        PersoneForBenchmark personeDeser=jsonReader.readValue(personeAsStr,PersoneForBenchmark.class);
        bh.consume(personeDeser);
    }

    protected void testMapperPersoneReaderWriterValues(List<Persona> persone, Blackhole bh, ObjectReader jsonReader, ObjectWriter jsonWriter) throws Exception {
        StringWriter stringWriter=new StringWriter();
        SequenceWriter sequenceWriter =jsonWriter.writeValues(stringWriter);
        sequenceWriter.writeAll(persone);
        bh.consume(stringWriter.toString());
        MappingIterator<Persona> mappingIterator= jsonReader.readValues(new StringReader(stringWriter.toString()));
        List<Persona> personeDeser=mappingIterator.readAll();
        bh.consume(personeDeser);
    }


}
