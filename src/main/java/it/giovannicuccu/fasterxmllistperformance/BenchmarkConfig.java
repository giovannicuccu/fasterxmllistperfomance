package it.giovannicuccu.fasterxmllistperformance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import it.giovannicuccu.fasterxmllistperformance.model.Persona;
import it.giovannicuccu.fasterxmllistperformance.model.PersoneForBenchmark;
import it.giovannicuccu.fasterxmllistperformance.model.PersoneHolderForBenchmark;
import org.jeasy.random.EasyRandom;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;

@State(Scope.Benchmark)
public class BenchmarkConfig {
    public List<Persona> persone=new ArrayList<>();
    public PersoneForBenchmark personeForBenchmark=new PersoneForBenchmark();
    public PersoneHolderForBenchmark personeHolderForBenchmark=new PersoneHolderForBenchmark();
    public String personeStr;


    //@Param({ "10" })
    public int numPersone=10;


    @Setup(Level.Invocation)
    public void setUp() throws JsonProcessingException {
        EasyRandom easyRandom = new EasyRandom();
        for (int i=0;i<=numPersone;i++) {
            persone.add(easyRandom.nextObject(Persona.class));
        }
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

        personeHolderForBenchmark.setPersone(persone);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        personeStr=objectMapper.writeValueAsString(persone);
    }
}
