package io.openliberty.websockets;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;

public class JSONTextDecoder implements Decoder.Text<JsonObject> {
    @Override
    public JsonObject decode(String s) throws DecodeException {
        try (JsonReader jsonReader = Json.createReader(new StringReader(s));) {
            return jsonReader.readObject();
        }
    }

    @Override
    public boolean willDecode() {
        
    }
}
