package com.saliim.absensimobile.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.saliim.absensimobile.model.absensi.DataAbsenPerIdAdmin;
import com.saliim.absensimobile.model.absensi.DataAbsenPerUser;
import com.saliim.absensimobile.model.absensi.UpdateAbsen;
import com.saliim.absensimobile.model.users.DataSubAdmin;
import com.saliim.absensimobile.model.absensi.DataAbsen;
import com.saliim.absensimobile.model.loginUser.LoginUser;
import com.saliim.absensimobile.model.lokasi.DataLokasi;
import com.saliim.absensimobile.model.lokasi.InsertLokasi;
import com.saliim.absensimobile.model.registerUser.RegisterAdmin;
import com.saliim.absensimobile.model.registerUser.RegisterUser;
import com.saliim.absensimobile.model.uploadGambar.BaseResponse;
import com.saliim.absensimobile.model.users.DataUsersBySubAdmin;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    public static Call<ArrayList<DataLokasi>> dataLokasi() {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.dataLokasi();
    }

    public static Call<ArrayList<DataSubAdmin>> dataSubAdmin() {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.dataSubAdmin();
    }

    public static Call<ArrayList<DataUsersBySubAdmin>> dataUserPerAdminId(String idAdmin) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.dataUserPerAdminId(idAdmin);
    }

    public static Call<ArrayList<DataAbsen>> dataAbsensi() {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.dataAbsensi();
    }

    public static Call<ArrayList<DataAbsenPerUser>> dataAbsenPerUser(String idUser) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.dataAbsenPerUser(idUser);
    }

    public static Call<ArrayList<DataAbsenPerIdAdmin>> dataAbsenPerAdminId(String idAdmin, String idUser, String jamMasuk) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.dataAbsenPerAdminId(idAdmin, idUser, jamMasuk);
    }

    public static Call<UpdateAbsen> updateAbsen(String idAbsen) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.updateAbsen(idAbsen);
    }

    public static Call<LoginUser> loginUsers(String vsusername, String vspassword) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.loginUsers(vsusername, vspassword);
    }

    public static Call<RegisterUser> registerUsers(String nama, String vsusername, String vspassword, String idAdmin) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.registerUsers(nama, vsusername, vspassword, idAdmin);
    }

    public static Call<RegisterAdmin> registerAdmins(String nama, String vsusername, String vspassword) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.registerAdmins(nama, vsusername, vspassword);
    }

    public static Call<InsertLokasi> addLokasi(String lokasi, String latitude, String longitude, String radius) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.addLokasi(lokasi, latitude, longitude, radius);
    }

    public static Call<ResponseBody> addAbsen(String nama, String lokasi, String status_absen, String id_user, String gambar) {
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.addAbsen(nama, lokasi, status_absen, id_user, gambar);
    }

    public static Call<BaseResponse> uploadPhotoBase64(String action, String photo){
        AbsensiService service = getInstance().create(AbsensiService.class);
        return service.uploadPhotoBase64(action, photo);
    }

    public interface AbsensiService {


        @GET("get_lokasi.php")
        Call<ArrayList<DataLokasi>> dataLokasi();

        @GET("get_sub_admin.php")
        Call<ArrayList<DataSubAdmin>> dataSubAdmin();

        @GET("get_user_by_admin_id.php")
        Call<ArrayList<DataUsersBySubAdmin>> dataUserPerAdminId(
                @Query("id_admin") String idAdmin);

        @GET("get_absen_user.php")
        Call<ArrayList<DataAbsen>> dataAbsensi();

        @GET("get_absen_user_by_id.php")
        Call<ArrayList<DataAbsenPerUser>> dataAbsenPerUser(
                @Query("id_user") String idUser);

        @GET("get_absen_user_by_admin_id.php")
        Call<ArrayList<DataAbsenPerIdAdmin>> dataAbsenPerAdminId(
                @Query("id_admin") String idAdmin,
                @Query("id_user") String idUser,
                @Query("jam_masuk") String jamMasuk);

        @PUT("update_absen_user.php")
        Call<UpdateAbsen> updateAbsen(
                @Query("id_absen") String idAbsen);

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
                @Field("vspassword") String vspassword,
                @Field("id_admin") String idAdmin);

        @FormUrlEncoded
        @POST("register_admin.php")
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
                @Field("nama") String nama,
                @Field("lokasi") String lokasi,
                @Field("status_absen") String status_absen,
                @Field("id_user") String id_user,
                @Field("gambar") String gambar);

        @FormUrlEncoded
        @POST("upload.php")
        Call<BaseResponse> uploadPhotoBase64(
                @Field("action") String action,
                @Field("photo") String photo);
    }
}
