package com.example.dsljsontesting;

import androidx.annotation.Nullable;

import com.dslplatform.json.DslJson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;


import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class DslConverterFactory extends Converter.Factory {

    private final DslJson<Object> mJson;

    private DslConverterFactory() {
        mJson = DslJsonFactory.create();
    }

    public static DslConverterFactory create() {
        return new DslConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        //noinspection unchecked
        return new DslResponseBodyConverter(mJson, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
//        Gson gson = new Gson();
//        //noinspection unchecked
//        return new GsonRequestBodyConverter<>(gson, gson.getAdapter(TypeToken.get(type)));
        return new DslRequestBodyConverter<>(mJson);
    }

    static class DslResponseBodyConverter<T> implements Converter<ResponseBody, T> {

        private final DslJson<Object> mJson;
        private final Type mType;

        DslResponseBodyConverter(DslJson<Object> json, Type type) {
            mJson = json;
            mType = type;
        }

        @Override
        public T convert(ResponseBody responseBody) throws IOException {
            //noinspection unchecked
            return (T) mJson.deserialize(mType, responseBody.byteStream());
        }
    }

    static class DslRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

        private final DslJson<Object> mJson;

        DslRequestBodyConverter(DslJson<Object> json) {
            mJson = json;
        }

        @Override
        public RequestBody convert(Object value) throws IOException {
            Buffer buffer = new Buffer();
            mJson.serialize(value, buffer.outputStream());
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        }
    }

//    static class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
//        private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
//        private static final Charset UTF_8 = Charset.forName("UTF-8");
//
//        private final Gson gson;
//        private final TypeAdapter<T> adapter;
//
//        GsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
//            this.gson = gson;
//            this.adapter = adapter;
//        }
//
//        @Override
//        public RequestBody convert(T value) throws IOException {
//            Buffer buffer = new Buffer();
//            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
//            JsonWriter jsonWriter = gson.newJsonWriter(writer);
//            adapter.write(jsonWriter, value);
//            jsonWriter.close();
//            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
//        }
//    }
}
