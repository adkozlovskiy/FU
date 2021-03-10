package sample.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import sample.models.Person;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

public class PersonsLoader {

    private static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public PersonsLoader() {
    }

    public List<Person> getPersons() throws Exception {
        URL url = new URL("http://localhost:8080/persons");

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Person>>() {
        }.getType();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        if (response.body() != null) {
            return gson.fromJson(response.body().string(), listType);
        }

        return null;
    }

    public void postPerson(Person person) throws Exception {
        URL url = new URL("http://localhost:8080/persons");

        Gson gson = new Gson();
        RequestBody body = RequestBody.create(JSON, gson.toJson(person));
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        if (response.body() != null) {
            response.body().string();

        }

    }

    public void putPerson(Person person, String id) throws Exception {
        URL url = new URL("http://localhost:8080/persons/" + id);

        Gson gson = new Gson();
        RequestBody body = RequestBody.create(JSON, gson.toJson(person));
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response = client.newCall(request).execute();
        if (response.body() != null) {
            response.body().string();

        }

    }

    public void deletePerson(String id) throws Exception {
        URL url = new URL("http://localhost:8080/persons/" + id);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        client.newCall(request).execute();
    }
}
