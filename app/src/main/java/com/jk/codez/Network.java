package com.jk.codez;

import androidx.annotation.NonNull;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONObject;

public class Network {
private static final String CODEZ_URL = "http://api.joeknowles.com/codez";

    public static void getCodes(final TextHttpResponseHandler handler) {
        new AsyncHttpClient().get(CODEZ_URL + "/get", handler);
    }

    public static void addCode(@NonNull final Item item, final TextHttpResponseHandler handler) {
        new AsyncHttpClient().post(CODEZ_URL + "/post", createParams(item), handler);
    }

    public static void editCode(@NonNull final Item item, final TextHttpResponseHandler handler) {
        new AsyncHttpClient().put(CODEZ_URL + "/put", createParams(item), handler);
    }

    public static void deleteCode(String cid, final TextHttpResponseHandler handler) {
        new AsyncHttpClient().delete(CODEZ_URL + "/delete/" + cid, handler);
    }

    @NonNull
    private static RequestParams createParams(@NonNull Item item) {
        RequestParams params = new RequestParams("cid", item._id);
        params.put("number", item.getNumber());
        params.put("street", item.getStreet());
        params.put("codes", item.getCodesString());
        params.put("notes", item.getNotes());
        params.put("lat", item.getLat());
        params.put("lng", item.getLng());
        params.put("precise", item.getPrecise());
        return params;
    }
}

//    }
//    private static final String USER_URL = "http://api.joeknowles.com/cx/user";
// GET
// POST /cx/auth
//    public static void authenticateUser(final String username, final String password, final AsyncHttpResponseHandler handler) {
//        System.out.printf("\nUsername: %s\nPassword: %s\n", username, password);
//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams params = new RequestParams();
//        params.put("email", username);
//        params.put("password", password);
//        client.post(AUTH_URL, params, handler);
//    // PUT /cx/auth
//    public static void modifyToken(String token, final TextHttpResponseHandler handler) {

//        new AsyncHttpClient().delete(AUTH_URL, new RequestParams("token", token), handler);

//    }

//    // GET /cx/user
//    public static void getAllUsers(final TextHttpResponseHandler handler) {
//        new AsyncHttpClient().get(USER_URL, handler);
//    }
//
//    // GET /cx/user
//    public static void searchUsers(RequestParams params, final TextHttpResponseHandler handler) {
//        new AsyncHttpClient().get(USER_URL, params, handler);
//    }
//
//    // POST /cx/user
//    public static void registerUser(final RequestParams formParams, final TextHttpResponseHandler handler) {
//        new AsyncHttpClient().post(USER_URL, formParams, handler);
//    }
//
//    // DELETE /cx/user
//    public static void deleteUser(@NotNull final String e, final TextHttpResponseHandler handler) {
//        new AsyncHttpClient().delete(USER_URL, new RequestParams("email", e), handler);
//    }
//
//    public static void updateUser(final RequestParams params, final TextHttpResponseHandler handler) {
//        new AsyncHttpClient().put(USER_URL, params, handler);
//    }


