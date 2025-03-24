package POJO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Hello {
    /**
     * class Hello returns the name associated with a playertag
     *
     * IT ALSO SERVES AS AN EXAMPLE TO HOW JACKSON WORKS (will save up to 3 hours of depression (based off true story))
     *

     *
     * PLEASE PLEASE double triple quadro check the nesting lists/arrays of the JSON files, there may be overlooked nesting lists that,
     * if you don't use the right POJO, will result in everything being null in the POJOs.
     *
     *
     * All Jackson package deserialization requires either:
     * 1) a constructor with the @JsonCreator annotation
     * 2) all the setters with the right @JsonProperty name
     * Constructors entirely depends on the getters
     * -> please name your getters/setters/parameter names with the same name as the JSON variable, or there will be misunderstanding and lots of tears.
     *
     * as long as you have either @JsonProperty or the right variable name(s) that calls on one of the variable names in the JSON file,
     *  it will be able to retrieve the data, missing both will result in NULL
     *
     *
     */

    String name;

    //please note: @JsonProperty IS THE GOAT, you must use it; it serves as a replacement for getVariable (Jackson needs to know what it's deserializing)
    @JsonCreator
    public Hello(@JsonProperty("name") String name){
        this.name = name;
    }

    /**
     * The getter is always necessary for Jackson to serialize the object to be sent to Javascript as a JSON, the name here will determine what the JSON var is named
     *
     */
    public String getName(){
        return name;
    }
}
