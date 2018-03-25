package site.iway.javahelpers;

import com.google.gson.*;
import com.google.gson.annotations.Expose;

public class GsonHelper {

    public static final int TYPE_ALL_FIELDS = 0;
    public static final int TYPE_ONLY_EXPOSE_BASED_FIELDS = 1;
    public static final int TYPE_NORMAL_AND_EXPOSE_BASED_FIELDS = 2;

    public static Gson create(int fields) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        switch (fields) {
            case TYPE_ALL_FIELDS:
                return gsonBuilder.create();
            case TYPE_ONLY_EXPOSE_BASED_FIELDS:
                gsonBuilder.excludeFieldsWithoutExposeAnnotation();
                return gsonBuilder.create();
            case TYPE_NORMAL_AND_EXPOSE_BASED_FIELDS:
                gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        Expose expose = f.getAnnotation(Expose.class);
                        return expose != null && !expose.serialize();
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                });
                gsonBuilder.addDeserializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        Expose expose = f.getAnnotation(Expose.class);
                        return expose != null && !expose.deserialize();
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                });
                return gsonBuilder.create();
            default:
                throw new RuntimeException("Invalid param fields=" + fields);
        }
    }

    public interface JsonElementTransformer<T> {
        public T transform(JsonElement jsonElement);
    }

    public static JsonElement toElement(String json) {
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(json);
    }

    public static JsonObject toObject(String json) {
        return toElement(json).getAsJsonObject();
    }

    public static JsonElement toElement(Object object, int fields) {
        Gson gson = create(fields);
        return gson.toJsonTree(object);
    }

    public static JsonObject toObject(Object object, int fields) {
        return toElement(object, fields).getAsJsonObject();
    }

    public static JsonElement innerElement(JsonElement jsonElement, String... names) {
        try {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            for (int i = 0; i < names.length - 1; i++) {
                jsonObject = jsonObject.getAsJsonObject(names[i]);
            }
            return jsonObject.get(names[names.length - 1]);
        } catch (Exception e) {
            return null;
        }
    }

    public static JsonObject innerObject(JsonElement jsonElement, String... names) {
        JsonElement innerElement = innerElement(jsonElement, names);
        return innerElement == null ? null : innerElement.getAsJsonObject();
    }

    public static JsonArray innerArray(JsonElement jsonElement, String... names) {
        JsonElement innerElement = innerElement(jsonElement, names);
        return innerElement == null ? null : innerElement.getAsJsonArray();
    }

    private static JsonElementTransformer<String> sStringJsonElementTransformer = new JsonElementTransformer<String>() {
        @Override
        public String transform(JsonElement jsonElement) {
            return jsonElement == null ? null : jsonElement.getAsString();
        }
    };

    public static String innerString(JsonElement jsonElement, String... names) {
        JsonElement innerElement = innerElement(jsonElement, names);
        return sStringJsonElementTransformer.transform(innerElement);
    }

    public static String[] innerStrings(JsonElement jsonElement, String... names) {
        JsonArray innerArray = innerArray(jsonElement, names);
        String[] stringArray;
        if (innerArray == null) {
            stringArray = new String[0];
        } else {
            stringArray = new String[innerArray.size()];
            transformArray(innerArray, stringArray, sStringJsonElementTransformer);
        }
        return stringArray;
    }

    public static <T> void transformArray(JsonArray src, T[] dst, JsonElementTransformer<T> transformer) {
        int size = src.size();
        for (int i = 0; i < size; i++) {
            dst[i] = transformer.transform(src.get(i));
        }
    }

}
