package com.saliim.absensimobile.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.saliim.absensimobile.model.absensi.AbsenKeluar;
import com.saliim.absensimobile.model.absensi.DataAbsen;
import com.saliim.absensimobile.model.absensi.IsiAbsen;
import com.saliim.absensimobile.model.loginUser.LoginUser;
import com.saliim.absensimobile.model.lokasi.DataLokasi;
import com.saliim.absensimobile.model.lokasi.InsertLokasi;
import com.saliim.absensimobile.model.registerUser.RegisterAdmin;
import com.saliim.absensimobile.model.registerUser.RegisterUser;
import com.saliim.absensimobile.model.uploadGambar.BaseResponse;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public class API {

    static Retrofit retrofit;
    public static String baseURL = "http://192.168.18.56/";

    public static Retrofit getInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();

            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL + "absensi_mobile/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }

    public static Call<LoginUser> loginUsers(String vsusername, String vspassword) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.loginUsers(vsusername, vspassword);
    }

    public static Call<RegisterUser> registerUsers(String nama, String vsusername, String vspassword) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.registerUsers(nama, vsusername, vspassword);
    }

    public static Call<RegisterAdmin> registerAdmins(String nama, String vsusername, String vspassword) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.registerAdmins(nama, vsusername, vspassword);
    }

    public static Call<InsertLokasi> addLokasi(String lokasi, String latitude, String longitude, String radius) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.addLokasi(lokasi, latitude, longitude, radius);
    }

    public static Call<ResponseBody> addAbsen(String id, String nama, String lokasi, String status_absen, String gambar) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.addAbsen(id, nama, lokasi, status_absen, gambar);
    }

    public static Call<AbsenKeluar> delAbsen(String id) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.delAbsen(id);
    }

    public static Call<ArrayList<DataLokasi>> dataLokasi() {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.dataLokasi();
    }

    public static Call<ArrayList<DataAbsen>> dataAbsensi() {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.dataAbsensi();
    }

    public static Call<BaseResponse> uploadPhotoBase64(String action, String photo){
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.uploadPhotoBase64(action, photo);
    }

    public interface AbsensiService {


        @FormUrlEncoded
        @POST("login_user.php")
        Call<LoginUser> loginUsers(
                @Field("vsusername") String vsusername,
                @Field("vspassword") String vspassword);

        @FormUrlEncoded
        @POST("register_user.php")
        Call<RegisterUser> registerUsers(
                @Field("nama") String nama,
                @Field("vsusername") String vsusername,
                @Field("vspassword") String vspassword);

        @FormUrlEncoded
        @POST("register_user.php")
        Call<RegisterAdmin> registerAdmins(
                @Field("nama") String nama,
                @Field("vsusername") String vsusername,
                @Field("vspassword") String vspassword);

        @FormUrlEncoded
        @POST("insert_lokasi.php")
        Call<InsertLokasi> addLokasi(
                @Field("lokasi") String lokasi,
                @Field("latitude") String latitude,
                @Field("longitude") String longitude,
                @Field("radius") String radius);

        @FormUrlEncoded
        @POST("absen_user.php")
        Call<ResponseBody> addAbsen(
                @Field("id") String id,
                @Field("nama") String nama,
                @Field("lokasi") String lokasi,
                @Field("status_absen") String status_absen,
                @Field("gambar") String gambar);

        @DELETE("absen_keluar.php")
        Call<AbsenKeluar> delAbsen(
                @Query("id") String id);

        @GET("get_lokasi.php")
        Call<ArrayList<DataLokasi>> dataLokasi();

        @GET("get_absen_user.php")
        Call<ArrayList<DataAbsen>> dataAbsensi();

        @FormUrlEncoded
        @POST("upload.php")
        Call<BaseResponse> uploadPhotoBase64(
                @Field("action") String action,
                @Field("photo") String photo);
    }
}
